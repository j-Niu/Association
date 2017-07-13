package com.future.association.community.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.future.association.community.base.BaseEntity;

import org.json.JSONObject;

/**
 * Created by HX·罗 on 2017/7/10.
 */

public class PlateInfo implements Parcelable{

    private String id ;
    private String name ;
    private String image ;

    public PlateInfo(JSONObject object){

    }

    public PlateInfo(String name,String id,String image) {
        this.name = name;
        this.id = id;
        this.image = image;
    }

    protected PlateInfo(Parcel in) {
        id = in.readString();
        name = in.readString();
        image = in.readString();
    }

    public static final Creator<PlateInfo> CREATOR = new Creator<PlateInfo>() {
        @Override
        public PlateInfo createFromParcel(Parcel in) {
            return new PlateInfo(in);
        }

        @Override
        public PlateInfo[] newArray(int size) {
            return new PlateInfo[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String imgUrl) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(image);
    }
}
