package com.imdglobalservices.psi.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.14.11
 */
public class O3SubIndex implements Parcelable {
    @SerializedName("east")
    @Expose
    private double east;
    @SerializedName("central")
    @Expose
    private double central;
    @SerializedName("south")
    @Expose
    private double south;
    @SerializedName("north")
    @Expose
    private double north;
    @SerializedName("west")
    @Expose
    private double west;
    @SerializedName("national")
    @Expose
    private double national;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.east);
        dest.writeDouble(this.central);
        dest.writeDouble(this.south);
        dest.writeDouble(this.north);
        dest.writeDouble(this.west);
        dest.writeDouble(this.national);
    }

    public O3SubIndex() {
    }

    protected O3SubIndex(Parcel in) {
        this.east = in.readDouble();
        this.central = in.readDouble();
        this.south = in.readDouble();
        this.north = in.readDouble();
        this.west = in.readDouble();
        this.national = in.readDouble();
    }

    public static final Parcelable.Creator<O3SubIndex> CREATOR = new Parcelable.Creator<O3SubIndex>() {
        @Override
        public O3SubIndex createFromParcel(Parcel source) {
            return new O3SubIndex(source);
        }

        @Override
        public O3SubIndex[] newArray(int size) {
            return new O3SubIndex[size];
        }
    };


    public double getEast() {
        return east;
    }

    public void setEast(double east) {
        this.east = east;
    }

    public double getCentral() {
        return central;
    }

    public void setCentral(double central) {
        this.central = central;
    }

    public double getSouth() {
        return south;
    }

    public void setSouth(double south) {
        this.south = south;
    }

    public double getNorth() {
        return north;
    }

    public void setNorth(double north) {
        this.north = north;
    }

    public double getWest() {
        return west;
    }

    public void setWest(double west) {
        this.west = west;
    }

    public double getNational() {
        return national;
    }

    public void setNational(double national) {
        this.national = national;
    }

}
