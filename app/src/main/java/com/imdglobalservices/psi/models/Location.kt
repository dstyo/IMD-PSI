package com.imdglobalservices.psi.models

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.14.11
 */
class Location : Parcelable {
    @SerializedName("latitude")
    @Expose
    var latitude: Double = 0.toDouble()
    @SerializedName("longitude")
    @Expose
    var longitude: Double = 0.toDouble()

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeDouble(this.latitude)
        dest.writeDouble(this.longitude)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.latitude = `in`.readDouble()
        this.longitude = `in`.readDouble()
    }

    companion object {

        val CREATOR: Parcelable.Creator<Location> = object : Parcelable.Creator<Location> {
            override fun createFromParcel(source: Parcel): Location {
                return Location(source)
            }

            override fun newArray(size: Int): Array<Location?> {
                return arrayOfNulls(size)
            }
        }
    }
}
