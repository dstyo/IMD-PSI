package com.imdglobalservices.psi.utils;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.imdglobalservices.psi.R;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.15.11
 */
public class CustomViewContent implements GoogleMap.InfoWindowAdapter {
    private View info_window = null;
    private LayoutInflater inflater = null;

    public CustomViewContent(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return (null);
    }

    @SuppressLint("InflateParams")
    @Override
    public View getInfoContents(Marker marker) {
        if (info_window == null) {
            info_window = inflater.inflate(R.layout.info_window, null);
        }

        TextView tv = info_window.findViewById(R.id.info_title);

        tv.setText(marker.getTitle());
        tv = info_window.findViewById(R.id.info_snippet);
        tv.setText(marker.getSnippet());

        return (info_window);
    }
}
