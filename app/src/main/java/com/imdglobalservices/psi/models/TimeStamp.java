package com.imdglobalservices.psi.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.14.11
 */
public class TimeStamp implements Parcelable {
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("update_timestamp")
    @Expose
    private String updateTimestamp;
    @SerializedName("readings")
    @Expose
    private DataIndex dataIndex;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.timestamp);
        dest.writeString(this.updateTimestamp);
        dest.writeParcelable(this.dataIndex, flags);
    }

    public TimeStamp() {
    }

    protected TimeStamp(Parcel in) {
        this.timestamp = in.readString();
        this.updateTimestamp = in.readString();
        this.dataIndex = in.readParcelable(DataIndex.class.getClassLoader());
    }

    public static final Creator<TimeStamp> CREATOR = new Creator<TimeStamp>() {
        @Override
        public TimeStamp createFromParcel(Parcel source) {
            return new TimeStamp(source);
        }

        @Override
        public TimeStamp[] newArray(int size) {
            return new TimeStamp[size];
        }
    };

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(String updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public DataIndex getDataIndex() {
        return dataIndex;
    }

    public void setDataIndex(DataIndex dataIndex) {
        this.dataIndex = dataIndex;
    }

}
