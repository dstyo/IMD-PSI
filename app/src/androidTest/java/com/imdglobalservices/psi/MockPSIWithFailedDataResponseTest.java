package com.imdglobalservices.psi;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.imdglobalservices.psi.network.api.RestClient;
import com.imdglobalservices.psi.network.base.BaseResponse;
import com.imdglobalservices.psi.network.base.RequestPSIDate;
import com.imdglobalservices.psi.utils.PSIRequestConstant;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.Calls;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.15.11
 */
public class MockPSIWithFailedDataResponseTest implements RestClient {
    private static final String TAG = MockPSIWithFailedDataResponseTest.class.getSimpleName();

    private final BehaviorDelegate<RestClient> delegate;

    public MockPSIWithFailedDataResponseTest(BehaviorDelegate<RestClient> service) {
        this.delegate = service;
    }

    private Call<RequestPSIDate.Response> getDelegate(boolean isByDate) {
        BaseResponse restResponseBase = new BaseResponse();
        restResponseBase.setCode(400);
        restResponseBase.setMessage("invalid datetime format");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json;
        try {
            json = ow.writeValueAsString(restResponseBase);
            Response response = Response.error(400, ResponseBody.create(MediaType.parse("application/json"), json));
            if (isByDate) {
                return delegate.returning(Calls.response(response)).getPSIDataByDate("2017-11-15", PSIRequestConstant.API_KEY);
            } else {
                return delegate.returning(Calls.response(response)).getPSIDataByDate("2017-11-15T06:00:0", PSIRequestConstant.API_KEY);
            }
        } catch (JsonProcessingException e) {
            Log.e(TAG, "JSON exception:", e);
            return Calls.failure(e);
        }
    }

    @Override
    public Call<RequestPSIDate.Response> getPSIDataByDate(@Query("date") String date, @Header("api-key") String apikey) {
        return getDelegate(true);
    }

    @Override
    public Call<RequestPSIDate.Response> getPSIDataByDateAndTime(@Query("date_time") String date, @Header("api-key") String apikey) {
        return getDelegate(false);
    }

}
