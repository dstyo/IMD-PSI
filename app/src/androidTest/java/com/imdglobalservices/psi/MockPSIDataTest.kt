package com.imdglobalservices.psi

import android.test.InstrumentationTestCase
import android.test.suitebuilder.annotation.SmallTest
import com.imdglobalservices.psi.network.api.APIDomain
import com.imdglobalservices.psi.network.api.RestClient
import com.imdglobalservices.psi.network.base.RequestPSIDate
import com.imdglobalservices.psi.utils.PSIRequestConstant
import junit.framework.Assert
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.15.11
 */
class MockPSIDataTest : InstrumentationTestCase() {
    private var mockRetrofit: MockRetrofit? = null
    private var retrofit: Retrofit? = null
    private val DATE = "2017-11-15T06:00:00+08:00"

    @Throws(Exception::class)
    public override fun setUp() {
        super.setUp()
        retrofit = Retrofit.Builder().baseUrl(APIDomain.apiBaseURL)
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val behavior = NetworkBehavior.create()

        mockRetrofit = MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build()
    }

    private fun initMockRestClient(isSuccess: Boolean): RestClient {
        val delegate = mockRetrofit?.create(RestClient::class.java)
        return if (isSuccess) {
            MockPSIWithSuccessDataResponseTest(delegate)
        } else {
            MockPSIWithFailedDataResponseTest(delegate)
        }
    }

    @Throws(Exception::class)
    private fun psiSuccessCallBack(): Response<RequestPSIDate.Response> {
        val psi = initMockRestClient(true).getPSIDataByDate(DATE, PSIRequestConstant.API_KEY)
        return psi.execute()
    }

    @Throws(Exception::class)
    private fun psiFailedCallBack(): Response<RequestPSIDate.Response> {
        val psi = initMockRestClient(false).getPSIDataByDate(DATE, PSIRequestConstant.API_KEY)
        return psi.execute()
    }

    @SmallTest
    @Throws(Exception::class)
    fun testPSIRetrieve() {
        Assert.assertTrue(psiSuccessCallBack().isSuccessful)
        Assert.assertNotNull(psiSuccessCallBack().body()?.regions)
        Assert.assertNotNull(psiSuccessCallBack().body()?.itemsArrayList)
        Assert.assertNotNull(psiSuccessCallBack().body()?.itemsArrayList?.get(0)?.timestamp)
        Assert.assertEquals(1, psiSuccessCallBack().body()?.itemsArrayList?.size)
        Assert.assertEquals(DATE, psiSuccessCallBack().body()?.itemsArrayList?.get(0)?.timestamp)
    }

    @SmallTest
    @Throws(Exception::class)
    fun testPSIFailedRetrieve() {
        Assert.assertFalse(psiFailedCallBack().isSuccessful)
        val errorConverter = retrofit?.responseBodyConverter<RequestPSIDate.Response>(RequestPSIDate.Response::class.java, arrayOfNulls(0))
        val error = errorConverter?.convert(psiFailedCallBack().errorBody())
        Assert.assertEquals(400, psiFailedCallBack().code())
        Assert.assertEquals("invalid datetime format", error?.message)
    }
}
