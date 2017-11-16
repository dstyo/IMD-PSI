package com.imdglobalservices.psi.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import com.imdglobalservices.psi.R
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.15.11
 */
object ContentHelper {
    /**
     * this method to show dialog info with custom click listener
     *
     * @param context  application context
     * @param title    title of message
     * @param message  message of dialog
     * @param listener listener of dialog
     */
    fun dialog(context: Context?, title: String, message: String, isCancel: Boolean, listener: (Any, Any) -> Unit) {
        if (context != null && context is Activity && !context.isFinishing) {
            AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(message)
                    .setIcon(R.mipmap.ic_launcher)
                    .setPositiveButton(context.getString(R.string.positive_button_ok), listener)
                    .setNegativeButton(null, null)
                    .setCancelable(isCancel)
                    .create()
                    .show()
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
    fun dialog(context: Context, message: String, okListener: (Any, Any) -> Unit, cancelListener: (Any, Any) -> Unit) {
        AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(R.string.positive_button_ok, okListener)
                .setNegativeButton(R.string.negative_button_cancel, cancelListener)
                .setCancelable(false)
                .create()
                .show()
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
    fun getDate(dateFormat: String, date: String, toFormat: String): String {
        var formatted = ""
        var formatter: DateFormat = SimpleDateFormat(dateFormat)
        val formatDate: Date
        try {
            val dateStr = formatter.parse(date)
            formatted = formatter.format(dateStr)
            formatDate = formatter.parse(formatted)
            formatter = SimpleDateFormat(toFormat)
            formatted = formatter.format(formatDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return formatted
    }


}
