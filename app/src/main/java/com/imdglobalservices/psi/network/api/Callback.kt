package com.imdglobalservices.psi.network.api

import com.google.gson.Gson
import com.imdglobalservices.psi.PSIApplication
import com.imdglobalservices.psi.R
import com.imdglobalservices.psi.network.base.BaseResponse

import retrofit2.Call
import retrofit2.Response

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.14.11
 */
abstract class Callback<T : BaseResponse> : retrofit2.Callback<T> {

    private val FAILED_CODE = 600

    override fun onResponse(call: Call<T>, response: Response<T>) {
        when {
            response.isSuccessful -> isSuccessful(response)
            else -> isFailed(response)
        }
    }

    private fun isSuccessful(response: Response<T>) {
        when {
            response.body() != null -> if (response.body()!!.message == null) {
                onSuccess(response.code(), response.body())
            } else {
                onFailed(response.code(), response.body()!!.message)
            }
            else -> onFailed(response.code(), PSIApplication.context.getString(R.string.error_received))
        }
    }

    private fun isFailed(response: Response<T>) {
        val gson = Gson()
        try {
            val body = gson.fromJson(response.errorBody()!!.string(), BaseResponse::class.java)
            when {
                body != null -> onFailed(response.code(), body.message)
                else -> onFailed(response.code(), PSIApplication.context.getString(R.string.error_unknown))
            }
        } catch (e: Exception) {
            onFailed(response.code(), PSIApplication.context.getString(R.string.error_failed))
            e.printStackTrace()
        }

    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailed(FAILED_CODE, t.message)
    }

    abstract fun onSuccess(code: Int, body: T?)

    abstract fun onFailed(code: Int, message: String?)

}
