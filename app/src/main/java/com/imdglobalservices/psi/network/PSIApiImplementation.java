package com.imdglobalservices.psi.network;

import android.annotation.SuppressLint;
import android.content.Context;

import com.imdglobalservices.psi.network.api.RestClient;
import com.imdglobalservices.psi.network.api.RestClientService;
import com.imdglobalservices.psi.network.base.RequestPSIDate;
import com.imdglobalservices.psi.utils.PSIPreference;
import com.imdglobalservices.psi.utils.PSIRequestConstant;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.15.11
 */
public class PSIApiImplementation {

    private static PSIApiImplementation instance;
    private Context context;

    private PSIApiImplementation(Context context) {
        this.context = context;
    }

    @SuppressLint("HardwareIds")
    public static synchronized PSIApiImplementation getInstance(Context context) {
        if (instance == null) {
            instance = new PSIApiImplementation(context);
            PSIPreference.setPref(
                    PSIPreference.PreferenceName.DEV,
                    android.provider.Settings.Secure.getString(instance.context.getContentResolver(),
                            android.provider.Settings.Secure.ANDROID_ID));
        }
        return instance;
    }

    /**
     * this method used for call api psi date
     *
     * @param request
     * @param callback
     */
    public void getPsiByDates(final RequestPSIDate.Request request, final Callback<RequestPSIDate.Response> callback) {
        RestClient restClient = getRestClient(true);
        Call<RequestPSIDate.Response> call = restClient.getPSIDataByDate(
                request.date,
                PSIRequestConstant.API_KEY
        );
        call.enqueue(callback);
    }

    /**
     * this method used for call api psi date and times
     *
     * @param request
     * @param callback
     */
    public void getPsiByDateTimes(final RequestPSIDate.Request request, final Callback<RequestPSIDate.Response> callback) {
        RestClient restClient = getRestClient(true);
        Call<RequestPSIDate.Response> call = restClient.getPSIDataByDateAndTime(
                request.date,
                PSIRequestConstant.API_KEY
        );
        call.enqueue(callback);
    }



    /**
     * method to get restconnect retrofit
     *
     * @param isRetry
     * @return
     */
    private RestClient getRestClient(boolean isRetry) {
        if (isRetry)
            return RestClientService.getInstance().getConnectionsWithRetry();
        else
            return RestClientService.getInstance().getConnections();
    }
}
