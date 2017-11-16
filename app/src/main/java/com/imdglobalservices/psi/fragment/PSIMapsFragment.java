package com.imdglobalservices.psi.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.imdglobalservices.psi.R;
import com.imdglobalservices.psi.database.PSIDatabaseImplementation;
import com.imdglobalservices.psi.models.DataIndex;
import com.imdglobalservices.psi.models.PSIDate;
import com.imdglobalservices.psi.models.Region;
import com.imdglobalservices.psi.utils.CustomViewContent;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.15.11
 */
public class PSIMapsFragment extends Fragment implements OnMapReadyCallback {
    private SupportMapFragment mapFragment;
    private GoogleMap map;
    private PSIDate psiByDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps_psi, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);
    }

    private void getDataPSI(PSIDate psiByDate, Region region, LatLng latLng) {
        String infoContent = "";
        if (psiByDate.getItemsArrayList() != null) {
            infoContent += "o3_sub_index : " + getDataIndex().getO3SubIndex().getCentral() + "\n";
            infoContent += "pm10_twenty_four_hourly : " + getDataIndex().getPm10TwentyFourHourly().getCentral() + "\n";
            infoContent += "pm10_sub_index : " + getDataIndex().getPm10SubIndex().getCentral() + "\n";
            infoContent += "co_sub_index : " + getDataIndex().getCoSubIndex().getCentral() + "\n";
            infoContent += "pm25_twenty_four_hourly : " + getDataIndex().getPm25TwentyFourHourly().getCentral() + "\n";
            infoContent += "so2_sub_index : " + getDataIndex().getSo2SubIndex().getCentral() + "\n";
            infoContent += "co_eight_hour_max : " + getDataIndex().getCoEightHourMax().getCentral() + "\n";
            infoContent += "no2_one_hour_max : " + getDataIndex().getNo2OneHourMax().getCentral() + "\n";
            infoContent += "so2_twenty_four_hourly : " + getDataIndex().getSo2TwentyFourHourly().getCentral() + "\n";
            infoContent += "pm25_sub_index : " + getDataIndex().getPm25SubIndex().getCentral() + "\n";
            infoContent += "psi_twenty_four_hourly : " + getDataIndex().getPsiTwentyFourHourly().getCentral() + "\n";
            infoContent += "o3_eight_hour_max : " + getDataIndex().getO3EightHourMax().getCentral() + "\n";
        }
        MarkerOptions destinationMarker = new MarkerOptions()
                .position(latLng)
                .title("Singapore " + region.getName() + ", readings :")
                .snippet(infoContent);
        map.addMarker(destinationMarker);
    }

    private DataIndex getDataIndex() {
        return psiByDate.getItemsArrayList().get(0).getDataIndex();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        psiByDate = PSIDatabaseImplementation.getPsiDateTime();

        try {
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getContext(), R.raw.style_maps));

            if (!success) {
                Log.e("Map", "Maps Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("Map", "Can't find style. Error: ", e);
        }
        map = googleMap;
        for (Region region : psiByDate.getRegions())
            if (region.getName() != null && !region.getName().equals("region")) {
                boolean isCentral = false;
                LatLng latLng = new LatLng(region.getLabelLocation().getLatitude(), region.getLabelLocation().getLongitude());

                switch (region.getName()) {
                    case "central": {
                        isCentral = true;
                        getDataPSI(psiByDate, region, latLng);
                        break;
                    }
                    case "west": {
                        getDataPSI(psiByDate, region, latLng);
                        break;
                    }
                    case "north": {
                        getDataPSI(psiByDate, region, latLng);
                        break;
                    }
                    case "east": {
                        getDataPSI(psiByDate, region, latLng);
                        break;
                    }
                    case "south": {
                        getDataPSI(psiByDate, region, latLng);
                        break;
                    }
                }
                map.setInfoWindowAdapter(new CustomViewContent(getActivity().getLayoutInflater()));

                if (isCentral)
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10.f), 5000, null);
            }


    }
}
