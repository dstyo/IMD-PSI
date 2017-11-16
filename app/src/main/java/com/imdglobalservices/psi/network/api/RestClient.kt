package com.imdglobalservices.psi.network.api

import com.imdglobalservices.psi.network.base.RequestPSIDate

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.15.11
 */
interface RestClient {
    @GET(APIDomain.PSI)
    fun getPSIDataByDate(@Query("date") date: String, @Header("api-key") apiKey: String): Call<RequestPSIDate.Response>

    @GET(APIDomain.PSI)
    fun getPSIDataByDateAndTime(@Query("date_time") date: String, @Header("api-key") apiKey: String): Call<RequestPSIDate.Response>
}
