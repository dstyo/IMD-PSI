package com.imdglobalservices.psi.network.api;

import com.imdglobalservices.psi.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.15.11
 */
public class RestClientService {
    private static RestClientService instance;
    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

    private RestClientService() {
    }

    public static synchronized RestClientService getInstance() {
        if (instance == null) {
            instance = new RestClientService();
            loggingInterceptor.setLevel(getEnvironmentLevel(BuildConfig.ENVIRONMENT));
        }

        return instance;
    }

    private static HttpLoggingInterceptor.Level getEnvironmentLevel(int env) {
        return env == 0 ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE;
    }

    /**
     * this method rest client will running without retry function
     *
     * @return RestClient
     */
    public RestClient getConnections() {
        return runningClient(null, false);
    }

    /**
     * this method rest client will running without retry function
     *
     * @return RestClient
     */
    public RestClient getConnectionsWithRetry() {
        return runningClient(null, true);
    }

    private RestClient runningClient(RestClient restClient, boolean isRetry) {
        int TIMEOUT = 180000;

        if (restClient == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .retryOnConnectionFailure(isRetry)
                    .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                    .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                    .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APIDomain.getApiBaseURL())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            restClient = retrofit.create(RestClient.class);
        }
        return restClient;
    }
}
