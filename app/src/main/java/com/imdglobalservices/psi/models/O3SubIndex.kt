package com.imdglobalservices.psi.models

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.14.11
 */
class O3SubIndex : Parcelable {
    @SerializedName("east")
    @Expose
    var east: Double = 0.toDouble()
    @SerializedName("central")
    @Expose
    var central: Double = 0.toDouble()
    @SerializedName("south")
    @Expose
    var south: Double = 0.toDouble()
    @SerializedName("north")
    @Expose
    var north: Double = 0.toDouble()
    @SerializedName("west")
    @Expose
    var west: Double = 0.toDouble()
    @SerializedName("national")
    @Expose
    var national: Double = 0.toDouble()

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeDouble(this.east)
        dest.writeDouble(this.central)
        dest.writeDouble(this.south)
        dest.writeDouble(this.north)
        dest.writeDouble(this.west)
        dest.writeDouble(this.national)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.east = `in`.readDouble()
        this.central = `in`.readDouble()
        this.south = `in`.readDouble()
        this.north = `in`.readDouble()
        this.west = `in`.readDouble()
        this.national = `in`.readDouble()
    }

    companion object {

        val CREATOR: Parcelable.Creator<O3SubIndex> = object : Parcelable.Creator<O3SubIndex> {
            override fun createFromParcel(source: Parcel): O3SubIndex {
                return O3SubIndex(source)
            }

            override fun newArray(size: Int): Array<O3SubIndex?> {
                return arrayOfNulls(size)
            }
        }
    }

}
