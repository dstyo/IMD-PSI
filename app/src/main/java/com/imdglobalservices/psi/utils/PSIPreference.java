package com.imdglobalservices.psi.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.imdglobalservices.psi.PSIApplication;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.14.11
 */
public class PSIPreference {
    private static String PACKAGE_NAME = "com.imdglobalservices.psi.";
    public static final String TAG = PACKAGE_NAME + "preferences";

    public static class PreferenceName {
        public static String ENV = TAG + "-ENV";
        public static String LANG = TAG +"-LANG";
        public static String VERSION = TAG +"-VERSION";
    }

    /**
     * this method use for saved preference
     *
     * @param key name of preference
     * @param value of preference
     */
    public static void setPref(String key, String value) {
        SharedPreferences sharedPref = PSIApplication.getInstance().getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * this method use for get preference
     *
     * @param key for get value of preference
     * @return value of key
     */
    public static String getPref(String key) {
        SharedPreferences sharedPref = PSIApplication.getInstance().getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return sharedPref.getString(key, "");
    }

}
