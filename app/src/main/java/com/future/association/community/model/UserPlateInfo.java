package com.future.association.community.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.future.association.community.utils.JSONUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/27.
 */

public class UserPlateInfo implements Parcelable{

    private String jifen ;
    private ArrayList<PlateInfo> list ;

    protected UserPlateInfo(Parcel in) {
        jifen = in.readString();
        list = in.createTypedArrayList(PlateInfo.CREATOR);
    }

    public static final Creator<UserPlateInfo> CREATOR = new Creator<UserPlateInfo>() {
        @Override
        public UserPlateInfo createFromParcel(Parcel in) {
            return new UserPlateInfo(in);
        }

        @Override
        public UserPlateInfo[] newArray(int size) {
            return new UserPlateInfo[size];
        }
    };

    public String getJifen() {
        return jifen;
    }

    public ArrayList<PlateInfo> getPlateInfos(){
        return list ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(jifen);
        parcel.writeTypedList(list);
    }
}
