package com.imdglobalservices.psi.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import com.imdglobalservices.psi.R
import java.util.*

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.15.11
 */
object Permission {

    fun permissionNeeded(activity: Activity, code: Int) {
        val permissionsNeeded = ArrayList<String>()

        val permissionsList = ArrayList<String>()
        if (!addPermission(activity, permissionsList, Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsNeeded.add(activity.getString(R.string.permission_location))
        if (!addPermission(activity, permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            permissionsNeeded.add(activity.getString(R.string.permission_storage))

        when {
            permissionsList.size > 0 -> {
                if (permissionsNeeded.size > 0) {
                    // Need Rationale
                    val message = StringBuilder(activity.getString(R.string.permission_grant) + " " + permissionsNeeded[0])
                    (1 until permissionsNeeded.size).forEach { i -> message.append(", ").append(permissionsNeeded[i]) }
                    ContentHelper.dialog(activity.applicationContext, message.toString(),
                            { dialog, which ->
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    activity.requestPermissions(permissionsList.toTypedArray(), code)
                                }
                            }
                    ) { dialog, which -> ContentHelper.dialog(activity.applicationContext, activity.getString(R.string.app_name), activity.getString(R.string.must_have_permission), false) { dialog, which -> } }
                    return
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    activity.requestPermissions(permissionsList.toTypedArray(), code)
                }
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
    private fun addPermission(activity: Activity, permissionsList: MutableList<String>, permission: String): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission)
                // Check for Rationale Option
                if (!activity.shouldShowRequestPermissionRationale(permission))
                    return false

            }
        }
        return true
    }
}
