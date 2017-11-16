package com.imdglobalservices.psi.network

import android.annotation.SuppressLint
import android.content.Context
import com.imdglobalservices.psi.network.api.RestClient
import com.imdglobalservices.psi.network.api.RestClientService
import com.imdglobalservices.psi.network.base.RequestPSIDate
import com.imdglobalservices.psi.utils.PSIPreference
import com.imdglobalservices.psi.utils.PSIRequestConstant
import retrofit2.Callback

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.15.11
 */
class PSIApiImplementation private constructor(private val context: Context) {

    /**
     * this method used for call api psi date
     *
     * @param request
     * @param callback
     */
    fun getPsiByDates(request: RequestPSIDate.Request, callback: Callback<RequestPSIDate.Response>) {
        val restClient = getRestClient(true)
        val call = restClient?.getPSIDataByDate(request.date, PSIRequestConstant.API_KEY)
        call?.enqueue(callback)
    }

    /**
     * this method used for call api psi date and times
     *
     * @param request
     * @param callback
     */
    fun getPsiByDateTimes(request: RequestPSIDate.Request, callback: Callback<RequestPSIDate.Response>) {
        val restClient = getRestClient(true)
        val call = restClient?.getPSIDataByDateAndTime(request.date, PSIRequestConstant.API_KEY)
        call?.enqueue(callback)
    }


    /**
     * method to get restconnect retrofit
     *
     * @param isRetry
     * @return
     */
    private fun getRestClient(isRetry: Boolean): RestClient? {
        return if (!isRetry) RestClientService.instance?.connections else RestClientService.instance?.connectionsWithRetry
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var instance: PSIApiImplementation? = null

        @SuppressLint("HardwareIds")
        @Synchronized
        fun getInstance(context: Context?): PSIApiImplementation {
            when (instance) {
                null -> {
                    instance = context?.let { PSIApiImplementation(it) }
                    PSIPreference.setPref(
                            PSIPreference.PreferenceName.DEV,
                            android.provider.Settings.Secure.getString(instance!!.context.contentResolver,
                                    android.provider.Settings.Secure.ANDROID_ID))
                }
            }
            return instance as PSIApiImplementation
        }
    }
}
