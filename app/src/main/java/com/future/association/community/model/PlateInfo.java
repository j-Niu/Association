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
    private String audit ;
    private String locked ;
    private String fatie_jf ;
    private String huifu_jf ;
    private String fangwen_jf ;
    private String name ;
    private String image ;

    public PlateInfo(JSONObject object){

    }

    protected PlateInfo(Parcel in) {
        id = in.readString();
        audit = in.readString();
        locked = in.readString();
        fatie_jf = in.readString();
        huifu_jf = in.readString();
        fangwen_jf = in.readString();
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

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public String getFatie_jf() {
        return fatie_jf;
    }

    public void setFatie_jf(String fatie_jf) {
        this.fatie_jf = fatie_jf;
    }

    public String getHuifu_jf() {
        return huifu_jf;
    }

    public void setHuifu_jf(String huifu_jf) {
        this.huifu_jf = huifu_jf;
    }

    public String getFangwen_jf() {
        return fangwen_jf;
    }

    public void setFangwen_jf(String fangwen_jf) {
        this.fangwen_jf = fangwen_jf;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(audit);
        dest.writeString(locked);
        dest.writeString(fatie_jf);
        dest.writeString(huifu_jf);
        dest.writeString(fangwen_jf);
        dest.writeString(name);
        dest.writeString(image);
    }
}
