package com.imdglobalservices.psi.network.api;

import com.imdglobalservices.psi.network.base.RequestPSIDate;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.15.11
 */
public interface  RestClient {
    @GET(APIDomain.PSI)
    Call<RequestPSIDate.Response> getPSIDataByDate(
            @Query("date") String date,
            @Header("api-key") String apiKey
    );

    @GET(APIDomain.PSI)
    Call<RequestPSIDate.Response> getPSIDataByDateAndTime(
            @Query("date_time") String date,
            @Header("api-key") String apiKey
    );
}
