package com.imdglobalservices.psi

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.test.InstrumentationTestCase
import android.view.View
import com.imdglobalservices.psi.activity.MainActivity
import com.imdglobalservices.psi.network.api.APIDomain
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.16.11
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest : InstrumentationTestCase() {
    @Rule
    var mActivityRule = ActivityTestRule(MainActivity::class.java, true, false)
    private var server: MockWebServer? = null

    @Before
    @Throws(Exception::class)
    public override fun setUp() {
        super.setUp()
        server = MockWebServer()
        server?.start()
        injectInstrumentation(InstrumentationRegistry.getInstrumentation())
        APIDomain.DEVELOPMENT_DOMAIN = server?.url("/").toString()
        APIDomain.PRODUCTION_DOMAIN = server?.url("/").toString()
        APIDomain.STAGING_DOMAIN = server?.url("/").toString()
        APIDomain.VERSION = ""
        APIDomain.ENVIRONMENT = ""
    }

    @Test
    @Throws(Exception::class)
    fun testDataIsShown() {
        val fileName = "success_response.json"
        server?.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(RestClientServiceHelper.getStringFromFile(instrumentation.context, fileName)))

        val intent = Intent()
        mActivityRule.launchActivity(intent)
    }


    @Test
    @Throws(Exception::class)
    fun testRetryShowsWhenError() {
        val fileName = "failed_response.json"

        server?.enqueue(MockResponse()
                .setResponseCode(400)
                .setBody(RestClientServiceHelper.getStringFromFile(instrumentation.context, fileName)))

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        onView(withText(R.string.default_error)).inRoot(withDecorView(
                not<View>(`is`<View>(mActivityRule.activity.window.decorView)))).check(matches(isDisplayed()))
    }
}
