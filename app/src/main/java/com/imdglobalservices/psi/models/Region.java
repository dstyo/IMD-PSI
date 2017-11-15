package com.imdglobalservices.psi.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.14.11
 */
public class Region implements Parcelable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("label_location")
    @Expose
    private Location labelLocation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLabelLocation() {
        return labelLocation;
    }

    public void setLabelLocation(Location labelLocation) {
        this.labelLocation = labelLocation;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeParcelable(this.labelLocation, flags);
    }

    public Region() {
    }

    protected Region(Parcel in) {
        this.name = in.readString();
        this.labelLocation = in.readParcelable(Location.class.getClassLoader());
    }

    public static final Parcelable.Creator<Region> CREATOR = new Parcelable.Creator<Region>() {
        @Override
        public Region createFromParcel(Parcel source) {
            return new Region(source);
        }

        @Override
        public Region[] newArray(int size) {
            return new Region[size];
        }
    };
}
