package com.imdglobalservices.psi

import com.imdglobalservices.psi.models.*
import java.util.*

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.15.11
 */
open class PSISampleDataTest {

    val regionList = ArrayList<Region>()

    protected val o3SubIndexTest: O3SubIndex
        get() {
            val o3SubIndex = O3SubIndex()
            o3SubIndex.east = 3.0
            o3SubIndex.central = 4.0
            o3SubIndex.south = 3.0
            o3SubIndex.north = 5.0
            o3SubIndex.west = 4.0
            o3SubIndex.national = 5.0
            return o3SubIndex
        }

    protected val pM10TwentyFourHourly: PM10TwentyFourHourly
        get() {
            val pm10TwentyFourHourly = PM10TwentyFourHourly()
            pm10TwentyFourHourly.east = 14.0
            pm10TwentyFourHourly.central = 12.0
            pm10TwentyFourHourly.south = 21.0
            pm10TwentyFourHourly.north = 18.0
            pm10TwentyFourHourly.west = 17.0
            pm10TwentyFourHourly.national = 21.0
            return pm10TwentyFourHourly
        }

    protected val pM10SubIndex: PM10SubIndex
        get() {
            val pm10SubIndex = PM10SubIndex()
            pm10SubIndex.east = 14.0
            pm10SubIndex.central = 12.0
            pm10SubIndex.south = 21.0
            pm10SubIndex.north = 18.0
            pm10SubIndex.west = 17.0
            pm10SubIndex.national = 21.0
            return pm10SubIndex
        }

    protected val pM25TwentyFourHourly: PM25TwentyFourHourly
        get() {
            val pm25TwentyFourHourly = PM25TwentyFourHourly()
            pm25TwentyFourHourly.east = 8.0
            pm25TwentyFourHourly.central = 6.0
            pm25TwentyFourHourly.south = 9.0
            pm25TwentyFourHourly.north = 10.0
            pm25TwentyFourHourly.west = 7.0
            pm25TwentyFourHourly.national = 10.0
            return pm25TwentyFourHourly
        }

    protected val coSubIndex: COSubIndex
        get() {
            val coSubIndex = COSubIndex()
            coSubIndex.east = 4.0
            coSubIndex.central = 5.0
            coSubIndex.south = 2.0
            coSubIndex.north = 4.0
            coSubIndex.west = 4.0
            coSubIndex.national = 5.0
            return coSubIndex
        }

    protected val so2SubIndex: SO2SubIndex
        get() {
            val so2SubIndex = SO2SubIndex()
            so2SubIndex.east = 8.0
            so2SubIndex.central = 12.0
            so2SubIndex.south = 12.0
            so2SubIndex.north = 14.0
            so2SubIndex.west = 20.0
            so2SubIndex.national = 20.0
            return so2SubIndex
        }

    protected val coEightHourMax: COEightHourMax
        get() {
            val coEightHourMax = COEightHourMax()
            coEightHourMax.east = 0.4
            coEightHourMax.central = 0.48
            coEightHourMax.south = 0.24
            coEightHourMax.north = 0.43
            coEightHourMax.west = 0.42
            coEightHourMax.national = 0.48
            return coEightHourMax
        }

    protected val nO2OneHourMax: NO2OneHourMax
        get() {
            val no2OneHourMax = NO2OneHourMax()
            no2OneHourMax.east = 14.0
            no2OneHourMax.central = 37.0
            no2OneHourMax.south = 37.0
            no2OneHourMax.north = 30.0
            no2OneHourMax.west = 35.0
            no2OneHourMax.national = 37.0
            return no2OneHourMax
        }

    protected val sO2TwentyFourHourly: SO2TwentyFourHourly
        get() {
            val so2TwentyFourHourly = SO2TwentyFourHourly()
            so2TwentyFourHourly.east = 13.0
            so2TwentyFourHourly.central = 19.0
            so2TwentyFourHourly.south = 20.0
            so2TwentyFourHourly.north = 23.0
            so2TwentyFourHourly.west = 32.0
            so2TwentyFourHourly.national = 32.0
            return so2TwentyFourHourly
        }

    protected val pM25SubIndex: PM25SubIndex
        get() {
            val pm25SubIndex = PM25SubIndex()
            pm25SubIndex.east = 34.0
            pm25SubIndex.central = 25.0
            pm25SubIndex.south = 36.0
            pm25SubIndex.north = 43.0
            pm25SubIndex.west = 30.0
            pm25SubIndex.national = 43.0
            return pm25SubIndex
        }

    protected val psiTwentyFourHourly: PSITwentyFourHourly
        get() {
            val psiTwentyFourHourly = PSITwentyFourHourly()
            psiTwentyFourHourly.east = 34.0
            psiTwentyFourHourly.central = 25.0
            psiTwentyFourHourly.south = 36.0
            psiTwentyFourHourly.north = 43.0
            psiTwentyFourHourly.west = 30.0
            psiTwentyFourHourly.national = 43.0
            return psiTwentyFourHourly
        }

    protected val o3EightHourMax: O3EightHourMax
        get() {
            val o3EightHourMax = O3EightHourMax()
            o3EightHourMax.east = 6.0
            o3EightHourMax.central = 10.0
            o3EightHourMax.south = 8.0
            o3EightHourMax.north = 13.0
            o3EightHourMax.west = 9.0
            o3EightHourMax.national = 13.0
            return o3EightHourMax
        }

    protected fun setTestData(lat: Double, lng: Double, name: String) {
        val region = Region()
        val location = Location()
        location.latitude = lat
        location.longitude = lng
        region.name = name
        region.labelLocation = location
        regionList.add(region)
    }
}
