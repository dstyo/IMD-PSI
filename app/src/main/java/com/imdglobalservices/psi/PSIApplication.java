package com.imdglobalservices.psi;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.imdglobalservices.psi.utils.PSIPreference;
import com.imdglobalservices.psi.utils.PSIRequestConstant;

import java.util.Locale;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.14.11
 */
public class PSIApplication extends Application{

    @SuppressLint("StaticFieldLeak")
    public static Context context;
    @SuppressLint("StaticFieldLeak")
    private static PSIApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
        initPreference();
    }

    public static PSIApplication getInstance(){
        return instance;
    }

    private void initPreference(){
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = null;
        String number = null;
        if (pInfo != null) {
            version = pInfo.versionName;
            number = Integer.toString(pInfo.versionCode);
        }

        PSIPreference.setPref(PSIPreference.PreferenceName.LANG, Locale.getDefault().toString());
        PSIPreference.setPref(PSIPreference.PreferenceName.VERSION, "android-" + number + "-" + version);
        PSIPreference.setPref(PSIPreference.PreferenceName.ENV, PSIRequestConstant.API_PRODUCTION);

    }
}
