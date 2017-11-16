package com.imdglobalservices.psi.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;

import com.imdglobalservices.psi.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.SENSOR_SERVICE;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.15.11
 */
public class Checked {
    /**
     * this method for check device emulator
     *
     * @return true or false
     */
    public static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }

    /**
     * this method for check device rooted
     *
     * @param context
     * @return true or false
     */
    public static boolean isRooted(Context context) {
        boolean isEmulator = !isRealDevice(context);
        String buildTags = Build.TAGS;
        if (!isEmulator && buildTags != null && buildTags.contains("test-keys")) {
            return true;
        } else {
            File file = new File("/system/app/Superuser.apk");
            if (file.exists()) {
                return true;
            } else {
                file = new File("/system/xbin/su");
                return !isEmulator && file.exists();
            }
        }
    }

    /**
     * this method for check a real device
     *
     * @param context
     * @return true or false
     */
    @SuppressLint("HardwareIds")
    public static boolean isRealDevice(Context context) {
        String androidId = android.provider.Settings.Secure.getString(context.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);
        SensorManager manager = (SensorManager) context.getSystemService(SENSOR_SERVICE);

        return (!isEmulator()
                && (androidId != null
                || !androidId.equals("")
                || !androidId.equals("null")
                || !androidId.equals("NULL"))
                && (Build.SERIAL != null
                || !Build.SERIAL.equals("")
                || !Build.SERIAL.equals(" ")
                || !Build.SERIAL.equals("null")
                || !Build.SERIAL.equals("NULL")
                || !Build.SERIAL.equals("unknown"))
                && !manager.getSensorList(Sensor.TYPE_ALL).isEmpty());
    }

    public static void permissionNeeded(final Activity activity, final int code) {
        List<String> permissionsNeeded = new ArrayList<>();

        final List<String> permissionsList = new ArrayList<>();
        if (!addPermission(activity, permissionsList, Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsNeeded.add(activity.getString(R.string.permission_location));
        if (!addPermission(activity, permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            permissionsNeeded.add(activity.getString(R.string.permission_storage));

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                StringBuilder message = new StringBuilder(activity.getString(R.string.permission_grant) + " " + permissionsNeeded.get(0));
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message.append(", ").append(permissionsNeeded.get(i));
                ContentHelper.dialog(activity.getApplicationContext(), message.toString(),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    activity.requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), code);
                                }
                            }
                        },
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ContentHelper.dialog(activity.getApplicationContext(), activity.getString(R.string.app_name), activity.getString(R.string.must_have_permission), false, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                            }
                        });
                return;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), code);
            }
        }
    }

    /**
     * this method used for add permission list android M
     *
     * @param permissionsList list of permission
     * @param permission permission of manifest
     * @return true or false
     */
    private static boolean addPermission(Activity activity, List<String> permissionsList, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission);
                // Check for Rationale Option
                if (!activity.shouldShowRequestPermissionRationale(permission))
                    return false;

            }
        }
        return true;
    }
}
