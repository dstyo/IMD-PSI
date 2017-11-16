package com.imdglobalservices.psi.fragment

import android.content.res.Resources
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.imdglobalservices.psi.R
import com.imdglobalservices.psi.database.PSIDatabaseImplementation
import com.imdglobalservices.psi.models.DataIndex
import com.imdglobalservices.psi.models.PSIDate
import com.imdglobalservices.psi.models.Region
import com.imdglobalservices.psi.utils.CustomViewContent

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.15.11
 */
class PSIMapsFragment : Fragment(), OnMapReadyCallback {
    private var mapFragment: SupportMapFragment? = null
    private var map: GoogleMap? = null
    private var psiByDate: PSIDate? = null

    private val dataIndex: DataIndex?
        get() = psiByDate?.itemsArrayList?.get(0)?.dataIndex

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_maps_psi, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapFragment = childFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    private fun getDataPSI(psiByDate: PSIDate?, region: Region, latLng: LatLng?) {
        var infoContent = ""
        if (psiByDate?.itemsArrayList != null) {
            infoContent += "${getString(R.string.o3_sub_index)}${dataIndex?.o3SubIndex?.central}\n"
            infoContent += "${getString(R.string.pm10_twenty_four_hourly)}${dataIndex?.pm10TwentyFourHourly?.central}\n"
            infoContent += "${getString(R.string.pm10_sub_index)}${dataIndex?.pm10SubIndex?.central}\n"
            infoContent += "${getString(R.string.co_sub_index)}${dataIndex?.coSubIndex?.central}\n"
            infoContent += "${getString(R.string.pm25_twenty_four_hourly)}${dataIndex?.pm25TwentyFourHourly?.central}\n"
            infoContent += "${getString(R.string.so2_sub_index)}${dataIndex?.so2SubIndex?.central}\n"
            infoContent += "${getString(R.string.co_eight_hour_max)}${dataIndex?.coEightHourMax?.central}\n"
            infoContent += "${getString(R.string.no2_one_hour_max)}${dataIndex?.no2OneHourMax?.central}\n"
            infoContent += "${getString(R.string.so2_twenty_four_hourly)}${dataIndex?.so2TwentyFourHourly?.central}\n"
            infoContent += "${getString(R.string.pm25_sub_index)}${dataIndex?.pm25SubIndex?.central}\n"
            infoContent += "${getString(R.string.psi_twenty_four_hourly)}${dataIndex?.psiTwentyFourHourly?.central}\n"
            infoContent += "${getString(R.string.o3_eight_hour_max)}${dataIndex?.o3EightHourMax?.central}\n"
        }
        val destinationMarker = latLng?.let {
            MarkerOptions()
                .position(it)
                .title("Singapore " + region.name + ", readings :")
                .snippet(infoContent)
        }
        map?.addMarker(destinationMarker)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        psiByDate = PSIDatabaseImplementation.psiDateTime
        try {
            val success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            context, R.raw.style_maps))
            when {
                !success -> Log.e("Map", "Maps Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e("Map", "Can't find style. Error: ", e)
        }

        map = googleMap
        psiByDate?.regions?.forEach { region ->
            if (region.name != null && region.name != "region") {
                var isCentral = false
                val latLng = region.labelLocation?.longitude?.let { region.labelLocation?.latitude?.let { it1 -> LatLng(it1, it) } }

                when (region.name) {
                    "central" -> {
                        isCentral = true
                        getDataPSI(psiByDate, region, latLng)
                    }
                    "west" -> {
                        getDataPSI(psiByDate, region, latLng)
                    }
                    "north" -> {
                        getDataPSI(psiByDate, region, latLng)
                    }
                    "east" -> {
                        getDataPSI(psiByDate, region, latLng)
                    }
                    "south" -> {
                        getDataPSI(psiByDate, region, latLng)
                    }
                }
                map?.setInfoWindowAdapter(CustomViewContent(activity.layoutInflater))

                when {
                    isCentral -> map?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f), 5000, null)
                }
            }
        }


    }
}
