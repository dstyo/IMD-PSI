package com.imdglobalservices.psi;

import com.imdglobalservices.psi.models.COEightHourMax;
import com.imdglobalservices.psi.models.COSubIndex;
import com.imdglobalservices.psi.models.Location;
import com.imdglobalservices.psi.models.NO2OneHourMax;
import com.imdglobalservices.psi.models.O3EightHourMax;
import com.imdglobalservices.psi.models.O3SubIndex;
import com.imdglobalservices.psi.models.PM10SubIndex;
import com.imdglobalservices.psi.models.PM10TwentyFourHourly;
import com.imdglobalservices.psi.models.PM25SubIndex;
import com.imdglobalservices.psi.models.PM25TwentyFourHourly;
import com.imdglobalservices.psi.models.PSITwentyFourHourly;
import com.imdglobalservices.psi.models.Region;
import com.imdglobalservices.psi.models.SO2SubIndex;
import com.imdglobalservices.psi.models.SO2TwentyFourHourly;

import java.util.ArrayList;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.15.11
 */
public class PSISampleDataTest {

    private ArrayList<Region> regionList = new ArrayList<>();

    protected void setTestData(double lat, double lng, String name) {
        Region region = new Region();
        Location location = new Location();
        location.setLatitude(lat);
        location.setLongitude(lng);
        region.setName(name);
        region.setLabelLocation(location);
        regionList.add(region);
    }

    protected O3SubIndex getO3SubIndexTest() {
        O3SubIndex o3SubIndex = new O3SubIndex();
        o3SubIndex.setEast(3);
        o3SubIndex.setCentral(4);
        o3SubIndex.setSouth(3);
        o3SubIndex.setNorth(5);
        o3SubIndex.setWest(4);
        o3SubIndex.setNational(5);
        return o3SubIndex;
    }

    protected PM10TwentyFourHourly getPM10TwentyFourHourly() {
        PM10TwentyFourHourly pm10TwentyFourHourly = new PM10TwentyFourHourly();
        pm10TwentyFourHourly.setEast(14);
        pm10TwentyFourHourly.setCentral(12);
        pm10TwentyFourHourly.setSouth(21);
        pm10TwentyFourHourly.setNorth(18);
        pm10TwentyFourHourly.setWest(17);
        pm10TwentyFourHourly.setNational(21);
        return pm10TwentyFourHourly;
    }

    protected PM10SubIndex getPM10SubIndex() {
        PM10SubIndex pm10SubIndex = new PM10SubIndex();
        pm10SubIndex.setEast(14);
        pm10SubIndex.setCentral(12);
        pm10SubIndex.setSouth(21);
        pm10SubIndex.setNorth(18);
        pm10SubIndex.setWest(17);
        pm10SubIndex.setNational(21);
        return pm10SubIndex;
    }

    protected PM25TwentyFourHourly getPM25TwentyFourHourly(){
        PM25TwentyFourHourly pm25TwentyFourHourly = new PM25TwentyFourHourly();
        pm25TwentyFourHourly.setEast(8);
        pm25TwentyFourHourly.setCentral(6);
        pm25TwentyFourHourly.setSouth(9);
        pm25TwentyFourHourly.setNorth(10);
        pm25TwentyFourHourly.setWest(7);
        pm25TwentyFourHourly.setNational(10);
        return pm25TwentyFourHourly;
    }

    protected COSubIndex getCOSubIndex(){
        COSubIndex coSubIndex = new COSubIndex();
        coSubIndex.setEast(4);
        coSubIndex.setCentral(5);
        coSubIndex.setSouth(2);
        coSubIndex.setNorth(4);
        coSubIndex.setWest(4);
        coSubIndex.setNational(5);
        return coSubIndex;
    }

    protected SO2SubIndex getSo2SubIndex(){
        SO2SubIndex so2SubIndex = new SO2SubIndex();
        so2SubIndex.setEast(8);
        so2SubIndex.setCentral(12);
        so2SubIndex.setSouth(12);
        so2SubIndex.setNorth(14);
        so2SubIndex.setWest(20);
        so2SubIndex.setNational(20);
        return so2SubIndex;
    }

    protected COEightHourMax getCOEightHourMax(){
        COEightHourMax coEightHourMax = new COEightHourMax();
        coEightHourMax.setEast(0.4);
        coEightHourMax.setCentral(0.48);
        coEightHourMax.setSouth(0.24);
        coEightHourMax.setNorth(0.43);
        coEightHourMax.setWest(0.42);
        coEightHourMax.setNational(0.48);
        return coEightHourMax;
    }

    protected NO2OneHourMax getNO2OneHourMax(){
        NO2OneHourMax no2OneHourMax = new NO2OneHourMax();
        no2OneHourMax.setEast(14);
        no2OneHourMax.setCentral(37);
        no2OneHourMax.setSouth(37);
        no2OneHourMax.setNorth(30);
        no2OneHourMax.setWest(35);
        no2OneHourMax.setNational(37);
        return no2OneHourMax;
    }

    protected SO2TwentyFourHourly getSO2TwentyFourHourly(){
        SO2TwentyFourHourly so2TwentyFourHourly = new SO2TwentyFourHourly();
        so2TwentyFourHourly.setEast(13);
        so2TwentyFourHourly.setCentral(19);
        so2TwentyFourHourly.setSouth(20);
        so2TwentyFourHourly.setNorth(23);
        so2TwentyFourHourly.setWest(32);
        so2TwentyFourHourly.setNational(32);
        return so2TwentyFourHourly;
    }

    protected PM25SubIndex getPM25SubIndex(){
        PM25SubIndex pm25SubIndex = new PM25SubIndex();
        pm25SubIndex.setEast(34);
        pm25SubIndex.setCentral(25);
        pm25SubIndex.setSouth(36);
        pm25SubIndex.setNorth(43);
        pm25SubIndex.setWest(30);
        pm25SubIndex.setNational(43);
        return pm25SubIndex;
    }

    protected PSITwentyFourHourly getPSITwentyFourHourly(){
        PSITwentyFourHourly psiTwentyFourHourly = new PSITwentyFourHourly();
        psiTwentyFourHourly.setEast(34);
        psiTwentyFourHourly.setCentral(25);
        psiTwentyFourHourly.setSouth(36);
        psiTwentyFourHourly.setNorth(43);
        psiTwentyFourHourly.setWest(30);
        psiTwentyFourHourly.setNational(43);
        return psiTwentyFourHourly;
    }

    protected O3EightHourMax getO3EightHourMax(){
        O3EightHourMax o3EightHourMax = new O3EightHourMax();
        o3EightHourMax.setEast(6);
        o3EightHourMax.setCentral(10);
        o3EightHourMax.setSouth(8);
        o3EightHourMax.setNorth(13);
        o3EightHourMax.setWest(9);
        o3EightHourMax.setNational(13);
        return o3EightHourMax;
    }

    public ArrayList<Region> getRegionList() {
        return regionList;
    }
}
