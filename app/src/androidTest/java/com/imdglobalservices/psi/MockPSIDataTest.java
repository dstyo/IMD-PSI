package com.imdglobalservices.psi;

import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.imdglobalservices.psi.network.api.APIDomain;
import com.imdglobalservices.psi.network.api.RestClient;
import com.imdglobalservices.psi.network.base.RequestPSIDate;
import com.imdglobalservices.psi.utils.PSIRequestConstant;

import junit.framework.Assert;

import java.lang.annotation.Annotation;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.15.11
 */
public class MockPSIDataTest extends InstrumentationTestCase {
    private MockRetrofit mockRetrofit;
    private Retrofit retrofit;
    private String DATE = "2017-11-15T06:00:00+08:00";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        retrofit = new Retrofit.Builder().baseUrl(APIDomain.getApiBaseURL())
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkBehavior behavior = NetworkBehavior.create();

        mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
    }

    private RestClient initMockRestClient(boolean isSuccess) {
        BehaviorDelegate<RestClient> delegate = mockRetrofit.create(RestClient.class);
        if (isSuccess) {
            return new MockPSIWithSuccessDataResponseTest(delegate);
        } else {
            return new MockPSIWithFailedDataResponseTest(delegate);
        }
    }

    private Response<RequestPSIDate.Response> psiSuccessCallBack() throws Exception {
        Call<RequestPSIDate.Response> psi = initMockRestClient(true).getPSIDataByDate(DATE, PSIRequestConstant.API_KEY);
        return psi.execute();
    }

    private Response<RequestPSIDate.Response> psiFailedCallBack() throws Exception {
        Call<RequestPSIDate.Response> psi = initMockRestClient(false).getPSIDataByDate(DATE, PSIRequestConstant.API_KEY);
        return psi.execute();
    }

    @SmallTest
    public void testPSIRetrieve() throws Exception {
        Assert.assertTrue(psiSuccessCallBack().isSuccessful());
        Assert.assertNotNull(psiSuccessCallBack().body().getRegions());
        Assert.assertNotNull(psiSuccessCallBack().body().getTimeStamps());
        Assert.assertNotNull(psiSuccessCallBack().body().getTimeStamps().get(0).getTimestamp());
        Assert.assertEquals(1, psiSuccessCallBack().body().getTimeStamps().size());
        Assert.assertEquals(DATE, psiSuccessCallBack().body().getTimeStamps().get(0).getTimestamp());
    }

    @SmallTest
    public void testPSIFailedRetrieve() throws Exception {
        Assert.assertFalse(psiFailedCallBack().isSuccessful());
        Converter<ResponseBody, RequestPSIDate.Response> errorConverter = retrofit.responseBodyConverter(RequestPSIDate.Response.class, new Annotation[0]);
        RequestPSIDate.Response error = errorConverter.convert(psiFailedCallBack().errorBody());
        Assert.assertEquals(400, psiFailedCallBack().code());
        Assert.assertEquals("invalid datetime format", error.getMessage());
    }
}
