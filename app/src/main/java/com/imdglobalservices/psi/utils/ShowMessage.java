package com.imdglobalservices.psi.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.imdglobalservices.psi.R;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.15.11
 */
public class ShowMessage {
    /**
     * method to show dialog info with custom listener
     *
     * @param context  application context
     * @param title    title of message
     * @param message  message of dialog
     * @param listener listener of dialog
     */
    public static void dialog(Context context, String title, String message, boolean isCancel, DialogInterface.OnClickListener listener) {
        if (context != null && context instanceof Activity && !((Activity) context).isFinishing()) {
            new AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(message)
                    .setIcon(R.mipmap.ic_launcher)
                    .setPositiveButton(context.getString(R.string.positive_button_ok), listener)
                    .setNegativeButton(null, null)
                    .setCancelable(isCancel)
                    .create()
                    .show();
        }
    }

    /**
     * method to show dialog
     *
     * @param context
     * @param message
     * @param okListener
     * @param cancelListener
     */
    public static void dialog(Context context, String message, DialogInterface.OnClickListener okListener, DialogInterface.OnClickListener cancelListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", cancelListener)
                .setCancelable(false)
                .create()
                .show();
    }


}
