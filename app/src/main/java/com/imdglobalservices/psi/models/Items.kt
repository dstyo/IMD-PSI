package com.imdglobalservices.psi.models

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.14.11
 */
class Items : Parcelable {
    @SerializedName("timestamp")
    @Expose
    var timestamp: String? = null
    @SerializedName("update_timestamp")
    @Expose
    var updateTimestamp: String? = null
    @SerializedName("readings")
    @Expose
    var dataIndex: DataIndex? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.timestamp)
        dest.writeString(this.updateTimestamp)
        dest.writeParcelable(this.dataIndex, flags)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.timestamp = `in`.readString()
        this.updateTimestamp = `in`.readString()
        this.dataIndex = `in`.readParcelable(DataIndex::class.java.classLoader)
    }

    companion object {

        val CREATOR: Parcelable.Creator<Items> = object : Parcelable.Creator<Items> {
            override fun createFromParcel(source: Parcel): Items {
                return Items(source)
            }

            override fun newArray(size: Int): Array<Items?> {
                return arrayOfNulls(size)
            }
        }
    }

}
