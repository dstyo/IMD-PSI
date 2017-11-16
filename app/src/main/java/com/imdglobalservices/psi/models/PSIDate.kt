package com.imdglobalservices.psi.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.imdglobalservices.psi.network.base.BaseResponse
import java.util.*

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.14.11
 */
open class PSIDate : BaseResponse, Parcelable {
    @SerializedName("region_metadata")
    @Expose
    var regions = ArrayList<Region>()
    @SerializedName("items")
    @Expose
    var itemsArrayList = ArrayList<Items>()

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeTypedList(this.regions)
        dest.writeList(this.itemsArrayList)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.regions = `in`.createTypedArrayList(Region.CREATOR)
        this.itemsArrayList = ArrayList()
        `in`.readList(this.itemsArrayList, Items::class.java.classLoader)
    }

    companion object {

        val CREATOR: Parcelable.Creator<PSIDate> = object : Parcelable.Creator<PSIDate> {
            override fun createFromParcel(source: Parcel): PSIDate {
                return PSIDate(source)
            }

            override fun newArray(size: Int): Array<PSIDate?> {
                return arrayOfNulls(size)
            }
        }
    }
}
