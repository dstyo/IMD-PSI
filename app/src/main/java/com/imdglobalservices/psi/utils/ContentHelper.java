package com.imdglobalservices.psi.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.imdglobalservices.psi.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.15.11
 */
public class ContentHelper {
    /**
     * this method to show dialog info with custom click listener
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
     * this method to show dialog
     *
     * @param context        application context
     * @param message        message of dialog
     * @param okListener     positive listener of dialog
     * @param cancelListener negative listener of dialog
     */
    public static void dialog(Context context, String message, DialogInterface.OnClickListener okListener, DialogInterface.OnClickListener cancelListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(R.string.positive_button_ok, okListener)
                .setNegativeButton(R.string.negative_button_cancel, cancelListener)
                .setCancelable(false)
                .create()
                .show();
    }


    /**
     * method to get date with new format
     *
     * @param dateFormat    format date
     * @param date
     * @param toFormat
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getDate(String dateFormat, String date, String toFormat) {
        String formatted = "";
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        Date formatDate;
        try {
            Date dateStr = formatter.parse(date);
            formatted = formatter.format(dateStr);
            formatDate = formatter.parse(formatted);
            formatter = new SimpleDateFormat(toFormat);
            formatted = formatter.format(formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatted;
    }


}
