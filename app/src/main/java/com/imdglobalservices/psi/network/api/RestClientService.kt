package com.imdglobalservices.psi.network.api

import com.imdglobalservices.psi.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.15.11
 */
class RestClientService private constructor() {

    /**
     * this method rest client will running without retry function
     *
     * @return RestClient
     */
    val connections: RestClient?
        get() = runningClient(null, false)

    /**
     * this method rest client will running without retry function
     *
     * @return RestClient
     */
    val connectionsWithRetry: RestClient?
        get() = runningClient(null, true)

    private fun runningClient(restClient: RestClient?, isRetry: Boolean): RestClient? {
        var restClient = restClient
        val TIMEOUT = 180000

        when (restClient) {
            null -> {
                val client = OkHttpClient.Builder()
                        .addInterceptor(loggingInterceptor)
                        .retryOnConnectionFailure(isRetry)
                        .connectTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                        .readTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                        .writeTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                        .build()
                val retrofit = Retrofit.Builder()
                        .baseUrl(APIDomain.apiBaseURL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                restClient = retrofit.create(RestClient::class.java)
            }
        }
        return restClient
    }

    companion object {
        internal var instance: RestClientService? = null
        private val loggingInterceptor = HttpLoggingInterceptor()

        @Synchronized
        fun getInstance(): RestClientService? {
            if (instance == null) {
                instance = RestClientService()
                loggingInterceptor.level = getEnvironmentLevel(BuildConfig.ENVIRONMENT)
            }

            return instance
        }

        private fun getEnvironmentLevel(env: Int): HttpLoggingInterceptor.Level {
            return if (env == 0) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }
}
