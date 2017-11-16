package com.imdglobalservices.psi.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.widget.Toast
import com.imdglobalservices.psi.R
import com.imdglobalservices.psi.utils.ContentHelper
import com.imdglobalservices.psi.utils.Permission
import java.util.*

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.15.11
 */
class SplashScreenActivity : AppCompatActivity() {
    private var context: Context? = null

    private val REQ_PERMISSION_MULTIPLE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        context = this
        checkPermission()
    }

    /**
     * this method for check permission in Android Marshmallow
     */
    private fun checkPermission() {
        when {
            Build.VERSION.SDK_INT >= 23 -> Permission.permissionNeeded(this, REQ_PERMISSION_MULTIPLE)
        }

        val handler = Handler()
        handler.postDelayed({ startApplication() }, 1000)
    }


    /**
     * this method open MainActivity
     */
    private fun startApplication() {
        val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQ_PERMISSION_MULTIPLE) {
            val perms = HashMap<String, Int>()
            perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED)
            perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED)
            permissions.indices.forEach { i -> perms.put(permissions[i], grantResults[i]) }
            when {
                perms[Manifest.permission.ACCESS_FINE_LOCATION] == PackageManager.PERMISSION_GRANTED && perms[Manifest.permission.WRITE_EXTERNAL_STORAGE] == PackageManager.PERMISSION_GRANTED -> startApplication()
                else -> {
                    Toast.makeText(context, getString(R.string.error_permission), Toast.LENGTH_SHORT)
                            .show()
                    ContentHelper.dialog(context, getString(R.string.app_name), getString(R.string.must_have_permission), true) { dialog, which -> }
                }
            }
        }
    }
}
