package com.imdglobalservices.psi.utils

import android.content.Context
import com.imdglobalservices.psi.PSIApplication

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.14.11
 */
object PSIPreference {
    private val PACKAGE_NAME = "com.imdglobalservices.psi."
    val TAG = PACKAGE_NAME + "preferences"

    object PreferenceName {
        var ENV = TAG + "-ENV"
        var LANG = TAG + "-LANG"
        var VERSION = TAG + "-VERSION"
        var DEV = TAG + "-DEV"
    }

    /**
     * this method use for saved preference
     *
     * @param key name of preference
     * @param value of preference
     */
    fun setPref(key: String, value: String) {
        val sharedPref = PSIApplication.instance?.applicationContext?.getSharedPreferences(TAG, Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()
        editor?.putString(key, value)
        editor?.apply()
    }

    /**
     * this method use for get preference
     *
     * @param key for get value of preference
     * @return value of key
     */
    fun getPref(key: String): String {
        val sharedPref = PSIApplication.instance?.applicationContext?.getSharedPreferences(TAG, Context.MODE_PRIVATE)
        return sharedPref?.getString(key, "").toString()
    }

}
