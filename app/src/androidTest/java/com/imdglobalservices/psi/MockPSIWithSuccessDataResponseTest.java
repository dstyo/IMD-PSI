package com.imdglobalservices.psi;

import com.imdglobalservices.psi.models.Items;
import com.imdglobalservices.psi.network.api.RestClient;
import com.imdglobalservices.psi.network.base.RequestPSIDate;
import com.imdglobalservices.psi.utils.PSIRequestConstant;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Query;
import retrofit2.mock.BehaviorDelegate;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.15.11
 */
public class MockPSIWithSuccessDataResponseTest extends PSISampleDataTest implements RestClient {

    private final BehaviorDelegate<RestClient> delegate;

    public MockPSIWithSuccessDataResponseTest(BehaviorDelegate<RestClient> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Call<RequestPSIDate.Response> getPSIDataByDate(@Query("date") String date, String apiKey) {
        return delegate.returningResponse(getTimeStampsResponse()).getPSIDataByDate("2017-11-15", PSIRequestConstant.API_KEY);
    }

    @Override
    public Call<RequestPSIDate.Response> getPSIDataByDateAndTime(@Query("date_time") String date, String apiKey) {
        return delegate.returningResponse(getTimeStampsResponse()).getPSIDataByDate("2017-11-15T06:00:00", PSIRequestConstant.API_KEY);
    }

    private RequestPSIDate.Response getTimeStampsResponse(){
        RequestPSIDate.Response response = new RequestPSIDate.Response();
        setTestData(1.35735, 103.94, "east");
        setTestData(1.35735, 103.82, "central");
        setTestData(1.29587, 103.82, "south");
        setTestData(1.41803, 103.82, "north");
        setTestData(1.35735, 103.7, "west");
        setTestData(0, 0, "national");
        ArrayList<Items> timeStampsList = new ArrayList<>();
        Items time = new Items();
        time.setTimestamp("2017-11-15T06:00:00+08:00");
        time.setUpdateTimestamp("2017-11-15T06:10:18+08:00");
        timeStampsList.add(time);
        response.setRegions(getRegionList());
        response.setItemsArrayList(timeStampsList);
        return response;
    }
}
