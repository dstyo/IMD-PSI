package com.imdglobalservices.psi

import com.imdglobalservices.psi.models.Items
import com.imdglobalservices.psi.network.api.RestClient
import com.imdglobalservices.psi.network.base.RequestPSIDate
import com.imdglobalservices.psi.utils.PSIRequestConstant
import retrofit2.Call
import retrofit2.http.Query
import retrofit2.mock.BehaviorDelegate
import java.util.*

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.15.11
 */
class MockPSIWithSuccessDataResponseTest(private val delegate: BehaviorDelegate<RestClient>) : PSISampleDataTest(), RestClient {

    private val timeStampsResponse: RequestPSIDate.Response
        get() {
            val response = RequestPSIDate.Response()
            setTestData(1.35735, 103.94, "east")
            setTestData(1.35735, 103.82, "central")
            setTestData(1.29587, 103.82, "south")
            setTestData(1.41803, 103.82, "north")
            setTestData(1.35735, 103.7, "west")
            setTestData(0.0, 0.0, "national")
            val timeStampsList = ArrayList<Items>()
            val time = Items()
            time.timestamp = "2017-11-15T06:00:00+08:00"
            time.updateTimestamp = "2017-11-15T06:10:18+08:00"
            timeStampsList.add(time)
            response.regions = regionList
            response.itemsArrayList = timeStampsList
            return response
        }

    override fun getPSIDataByDate(@Query("date") date: String, apiKey: String): Call<RequestPSIDate.Response> {
        return delegate.returningResponse(timeStampsResponse).getPSIDataByDate("2017-11-15", PSIRequestConstant.API_KEY)
    }

    override fun getPSIDataByDateAndTime(@Query("date_time") date: String, apiKey: String): Call<RequestPSIDate.Response> {
        return delegate.returningResponse(timeStampsResponse).getPSIDataByDate("2017-11-15T06:00:00", PSIRequestConstant.API_KEY)
    }
}
