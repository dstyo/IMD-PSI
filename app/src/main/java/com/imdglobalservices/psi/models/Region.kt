package com.imdglobalservices.psi.models

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.14.11
 */
class Region : Parcelable {
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("label_location")
    @Expose
    var labelLocation: Location? = null


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.name)
        dest.writeParcelable(this.labelLocation, flags)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.name = `in`.readString()
        this.labelLocation = `in`.readParcelable(Location::class.java.classLoader)
    }

    companion object {

        val CREATOR: Parcelable.Creator<Region> = object : Parcelable.Creator<Region> {
            override fun createFromParcel(source: Parcel): Region {
                return Region(source)
            }

            override fun newArray(size: Int): Array<Region?> {
                return arrayOfNulls(size)
            }
        }
    }
}
