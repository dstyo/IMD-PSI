package com.imdglobalservices.psi.models

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.14.11
 */
class DataIndex : Parcelable {

    @SerializedName("o3_sub_index")
    @Expose
    var o3SubIndex: O3SubIndex? = null
    @SerializedName("pm10_twenty_four_hourly")
    @Expose
    var pm10TwentyFourHourly: PM10TwentyFourHourly? = null
    @SerializedName("pm10_sub_index")
    @Expose
    var pm10SubIndex: PM10SubIndex? = null
    @SerializedName("co_sub_index")
    @Expose
    var coSubIndex: COSubIndex? = null
    @SerializedName("pm25_twenty_four_hourly")
    @Expose
    var pm25TwentyFourHourly: PM25TwentyFourHourly? = null
    @SerializedName("so2_sub_index")
    @Expose
    var so2SubIndex: SO2SubIndex? = null
    @SerializedName("co_eight_hour_max")
    @Expose
    var coEightHourMax: COEightHourMax? = null
    @SerializedName("no2_one_hour_max")
    @Expose
    var no2OneHourMax: NO2OneHourMax? = null
    @SerializedName("so2_twenty_four_hourly")
    @Expose
    var so2TwentyFourHourly: SO2TwentyFourHourly? = null
    @SerializedName("pm25_sub_index")
    @Expose
    var pm25SubIndex: PM25SubIndex? = null
    @SerializedName("psi_twenty_four_hourly")
    @Expose
    var psiTwentyFourHourly: PSITwentyFourHourly? = null
    @SerializedName("o3_eight_hour_max")
    @Expose
    var o3EightHourMax: O3EightHourMax? = null


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(this.o3SubIndex, flags)
        dest.writeParcelable(this.pm10TwentyFourHourly, flags)
        dest.writeParcelable(this.pm10SubIndex, flags)
        dest.writeParcelable(this.coSubIndex, flags)
        dest.writeParcelable(this.pm25TwentyFourHourly, flags)
        dest.writeParcelable(this.so2SubIndex, flags)
        dest.writeParcelable(this.coEightHourMax, flags)
        dest.writeParcelable(this.no2OneHourMax, flags)
        dest.writeParcelable(this.so2TwentyFourHourly, flags)
        dest.writeParcelable(this.pm25SubIndex, flags)
        dest.writeParcelable(this.psiTwentyFourHourly, flags)
        dest.writeParcelable(this.o3EightHourMax, flags)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.o3SubIndex = `in`.readParcelable(O3SubIndex::class.java.classLoader)
        this.pm10TwentyFourHourly = `in`.readParcelable(PM10TwentyFourHourly::class.java.classLoader)
        this.pm10SubIndex = `in`.readParcelable(PM10SubIndex::class.java.classLoader)
        this.coSubIndex = `in`.readParcelable(COSubIndex::class.java.classLoader)
        this.pm25TwentyFourHourly = `in`.readParcelable(PM25TwentyFourHourly::class.java.classLoader)
        this.so2SubIndex = `in`.readParcelable(SO2SubIndex::class.java.classLoader)
        this.coEightHourMax = `in`.readParcelable(COEightHourMax::class.java.classLoader)
        this.no2OneHourMax = `in`.readParcelable(NO2OneHourMax::class.java.classLoader)
        this.so2TwentyFourHourly = `in`.readParcelable(SO2TwentyFourHourly::class.java.classLoader)
        this.pm25SubIndex = `in`.readParcelable(PM25SubIndex::class.java.classLoader)
        this.psiTwentyFourHourly = `in`.readParcelable(PSITwentyFourHourly::class.java.classLoader)
        this.o3EightHourMax = `in`.readParcelable(O3EightHourMax::class.java.classLoader)
    }

    companion object {

        val CREATOR: Parcelable.Creator<DataIndex> = object : Parcelable.Creator<DataIndex> {
            override fun createFromParcel(source: Parcel): DataIndex {
                return DataIndex(source)
            }

            override fun newArray(size: Int): Array<DataIndex?> {
                return arrayOfNulls(size)
            }
        }
    }
}
