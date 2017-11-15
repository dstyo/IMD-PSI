package com.imdglobalservices.psi.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

import com.imdglobalservices.psi.R;
import com.imdglobalservices.psi.utils.Checked;
import com.imdglobalservices.psi.utils.ShowMessage;

import java.util.HashMap;
import java.util.Map;

import static com.imdglobalservices.psi.utils.Checked.isRooted;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.15.11
 */
public class SplashScreenActivity extends AppCompatActivity {
    private Context context;

    private int REQ_PERMISSION_MULTIPLE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        context = this;
        checkEmulator();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * this method for check permission in Android Marshmallow
     */
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            Checked.permissionNeeded(this, REQ_PERMISSION_MULTIPLE);
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startApplication();
            }
        }, 1000);
    }


    /**
     * this method open MainActivity
     */
    public void startApplication() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * this method for check emulator and continue process data
     */
    private void checkEmulator() {
        if (!isRooted(context)) {
            checkPermission();
        } else {
            showAlertDialog();
        }
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(context)
                .setTitle(R.string.default_error)
                .setMessage(R.string.error_emulator)
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton(getString(R.string.positive_button_retry), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkEmulator();
                    }
                })
                .setNegativeButton(getString(R.string.negative_button_close), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode != REQ_PERMISSION_MULTIPLE) {
            return;
        }

        Map<String, Integer> perms = new HashMap<>();
        perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
        for (int i = 0; i < permissions.length; i++)
            perms.put(permissions[i], grantResults[i]);
        if (perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            startApplication();
        } else {
            Toast.makeText(context, getString(R.string.error_permission), Toast.LENGTH_SHORT)
                    .show();
            ShowMessage.dialog(context, getString(R.string.app_name), getString(R.string.must_have_permission), true, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        }

    }
}
