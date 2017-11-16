package com.imdglobalservices.psi.utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.imdglobalservices.psi.R

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.15.11
 */
class CustomViewContent(inflater: LayoutInflater) : GoogleMap.InfoWindowAdapter {
    private var info_window: View? = null
    private var inflater: LayoutInflater? = null

    init {
        this.inflater = inflater
    }

    override fun getInfoWindow(marker: Marker): View? {
        return null
    }

    @SuppressLint("InflateParams")
    override fun getInfoContents(marker: Marker): View {
        when (info_window) {
            null -> info_window = inflater!!.inflate(R.layout.info_window, null)
        }

        var tv = info_window!!.findViewById<TextView>(R.id.info_title)

        tv.text = marker.title
        tv = info_window!!.findViewById(R.id.info_snippet)
        tv.text = marker.snippet

        return info_window as View
    }
}
