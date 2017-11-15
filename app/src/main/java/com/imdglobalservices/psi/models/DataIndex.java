package com.imdglobalservices.psi.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.14.11
 */
public class DataIndex implements Parcelable {

    @SerializedName("o3_sub_index")
    @Expose
    private O3SubIndex o3SubIndex;
    @SerializedName("pm10_twenty_four_hourly")
    @Expose
    private PM10TwentyFourHourly pm10TwentyFourHourly;
    @SerializedName("pm10_sub_index")
    @Expose
    private PM10SubIndex pm10SubIndex;
    @SerializedName("co_sub_index")
    @Expose
    private COSubIndex coSubIndex;
    @SerializedName("pm25_twenty_four_hourly")
    @Expose
    private PM25TwentyFourHourly pm25TwentyFourHourly;
    @SerializedName("so2_sub_index")
    @Expose
    private SO2SubIndex so2SubIndex;
    @SerializedName("co_eight_hour_max")
    @Expose
    private COEightHourMax coEightHourMax;
    @SerializedName("no2_one_hour_max")
    @Expose
    private NO2OneHourMax no2OneHourMax;
    @SerializedName("so2_twenty_four_hourly")
    @Expose
    private SO2TwentyFourHourly so2TwentyFourHourly;
    @SerializedName("pm25_sub_index")
    @Expose
    private PM25SubIndex pm25SubIndex;
    @SerializedName("psi_twenty_four_hourly")
    @Expose
    private PSITwentyFourHourly psiTwentyFourHourly;
    @SerializedName("o3_eight_hour_max")
    @Expose
    private O3EightHourMax o3EightHourMax;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.o3SubIndex, flags);
        dest.writeParcelable(this.pm10TwentyFourHourly, flags);
        dest.writeParcelable(this.pm10SubIndex, flags);
        dest.writeParcelable(this.coSubIndex, flags);
        dest.writeParcelable(this.pm25TwentyFourHourly, flags);
        dest.writeParcelable(this.so2SubIndex, flags);
        dest.writeParcelable(this.coEightHourMax, flags);
        dest.writeParcelable(this.no2OneHourMax, flags);
        dest.writeParcelable(this.so2TwentyFourHourly, flags);
        dest.writeParcelable(this.pm25SubIndex, flags);
        dest.writeParcelable(this.psiTwentyFourHourly, flags);
        dest.writeParcelable(this.o3EightHourMax, flags);
    }

    public DataIndex() {
    }

    protected DataIndex(Parcel in) {
        this.o3SubIndex = in.readParcelable(O3SubIndex.class.getClassLoader());
        this.pm10TwentyFourHourly = in.readParcelable(PM10TwentyFourHourly.class.getClassLoader());
        this.pm10SubIndex = in.readParcelable(PM10SubIndex.class.getClassLoader());
        this.coSubIndex = in.readParcelable(COSubIndex.class.getClassLoader());
        this.pm25TwentyFourHourly = in.readParcelable(PM25TwentyFourHourly.class.getClassLoader());
        this.so2SubIndex = in.readParcelable(SO2SubIndex.class.getClassLoader());
        this.coEightHourMax = in.readParcelable(COEightHourMax.class.getClassLoader());
        this.no2OneHourMax = in.readParcelable(NO2OneHourMax.class.getClassLoader());
        this.so2TwentyFourHourly = in.readParcelable(SO2TwentyFourHourly.class.getClassLoader());
        this.pm25SubIndex = in.readParcelable(PM25SubIndex.class.getClassLoader());
        this.psiTwentyFourHourly = in.readParcelable(PSITwentyFourHourly.class.getClassLoader());
        this.o3EightHourMax = in.readParcelable(O3EightHourMax.class.getClassLoader());
    }

    public static final Creator<DataIndex> CREATOR = new Creator<DataIndex>() {
        @Override
        public DataIndex createFromParcel(Parcel source) {
            return new DataIndex(source);
        }

        @Override
        public DataIndex[] newArray(int size) {
            return new DataIndex[size];
        }
    };

    public O3SubIndex getO3SubIndex() {
        return o3SubIndex;
    }

    public void setO3SubIndex(O3SubIndex o3SubIndex) {
        this.o3SubIndex = o3SubIndex;
    }

    public PM10TwentyFourHourly getPm10TwentyFourHourly() {
        return pm10TwentyFourHourly;
    }

    public void setPm10TwentyFourHourly(PM10TwentyFourHourly pm10TwentyFourHourly) {
        this.pm10TwentyFourHourly = pm10TwentyFourHourly;
    }

    public PM10SubIndex getPm10SubIndex() {
        return pm10SubIndex;
    }

    public void setPm10SubIndex(PM10SubIndex pm10SubIndex) {
        this.pm10SubIndex = pm10SubIndex;
    }

    public COSubIndex getCoSubIndex() {
        return coSubIndex;
    }

    public void setCoSubIndex(COSubIndex coSubIndex) {
        this.coSubIndex = coSubIndex;
    }

    public PM25TwentyFourHourly getPm25TwentyFourHourly() {
        return pm25TwentyFourHourly;
    }

    public void setPm25TwentyFourHourly(PM25TwentyFourHourly pm25TwentyFourHourly) {
        this.pm25TwentyFourHourly = pm25TwentyFourHourly;
    }

    public SO2SubIndex getSo2SubIndex() {
        return so2SubIndex;
    }

    public void setSo2SubIndex(SO2SubIndex so2SubIndex) {
        this.so2SubIndex = so2SubIndex;
    }

    public COEightHourMax getCoEightHourMax() {
        return coEightHourMax;
    }

    public void setCoEightHourMax(COEightHourMax coEightHourMax) {
        this.coEightHourMax = coEightHourMax;
    }

    public NO2OneHourMax getNo2OneHourMax() {
        return no2OneHourMax;
    }

    public void setNo2OneHourMax(NO2OneHourMax no2OneHourMax) {
        this.no2OneHourMax = no2OneHourMax;
    }

    public SO2TwentyFourHourly getSo2TwentyFourHourly() {
        return so2TwentyFourHourly;
    }

    public void setSo2TwentyFourHourly(SO2TwentyFourHourly so2TwentyFourHourly) {
        this.so2TwentyFourHourly = so2TwentyFourHourly;
    }

    public PM25SubIndex getPm25SubIndex() {
        return pm25SubIndex;
    }

    public void setPm25SubIndex(PM25SubIndex pm25SubIndex) {
        this.pm25SubIndex = pm25SubIndex;
    }

    public PSITwentyFourHourly getPsiTwentyFourHourly() {
        return psiTwentyFourHourly;
    }

    public void setPsiTwentyFourHourly(PSITwentyFourHourly psiTwentyFourHourly) {
        this.psiTwentyFourHourly = psiTwentyFourHourly;
    }

    public O3EightHourMax getO3EightHourMax() {
        return o3EightHourMax;
    }

    public void setO3EightHourMax(O3EightHourMax o3EightHourMax) {
        this.o3EightHourMax = o3EightHourMax;
    }
}
