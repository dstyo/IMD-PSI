package com.imdglobalservices.psi

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.imdglobalservices.psi.utils.PSIPreference
import com.imdglobalservices.psi.utils.PSIRequestConstant
import java.util.*

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.14.11
 */
class PSIApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = applicationContext
        initPreference()
    }

    private fun initPreference() {
        var pInfo: PackageInfo? = null
        try {
            pInfo = packageManager.getPackageInfo(packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        var version: String? = null
        var number: String? = null
        when {
            pInfo != null -> {
                version = pInfo.versionName
                number = Integer.toString(pInfo.versionCode)
            }
        }

        PSIPreference.setPref(PSIPreference.PreferenceName.LANG, Locale.getDefault().toString())
        PSIPreference.setPref(PSIPreference.PreferenceName.VERSION, "android-$number-$version")
        PSIPreference.setPref(PSIPreference.PreferenceName.ENV, PSIRequestConstant.API_PRODUCTION)

    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        @SuppressLint("StaticFieldLeak")
        var instance: PSIApplication? = null
            private set
    }
}
