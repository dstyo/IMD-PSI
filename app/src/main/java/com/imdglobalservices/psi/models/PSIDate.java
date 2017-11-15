package com.imdglobalservices.psi.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.imdglobalservices.psi.network.base.BaseResponse;

import java.util.ArrayList;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.14.11
 */
public class PSIDate extends BaseResponse implements Parcelable{
    @SerializedName("region_metadata")
    @Expose
    private ArrayList<Region> regions = new ArrayList<>();
    @SerializedName("items")
    @Expose
    private ArrayList<Items> itemsArrayList = new ArrayList<>();

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.regions);
        dest.writeList(this.itemsArrayList);
    }

    public PSIDate() {
    }

    protected PSIDate(Parcel in) {
        this.regions = in.createTypedArrayList(Region.CREATOR);
        this.itemsArrayList = new ArrayList<Items>();
        in.readList(this.itemsArrayList, Items.class.getClassLoader());
    }

    public static final Creator<PSIDate> CREATOR = new Creator<PSIDate>() {
        @Override
        public PSIDate createFromParcel(Parcel source) {
            return new PSIDate(source);
        }

        @Override
        public PSIDate[] newArray(int size) {
            return new PSIDate[size];
        }
    };

    public ArrayList<Region> getRegions() {
        return regions;
    }

    public void setRegions(ArrayList<Region> regions) {
        this.regions = regions;
    }

    public ArrayList<Items> getItemsArrayList() {
        return itemsArrayList;
    }

    public void setItemsArrayList(ArrayList<Items> itemsArrayList) {
        this.itemsArrayList = itemsArrayList;
    }
}
