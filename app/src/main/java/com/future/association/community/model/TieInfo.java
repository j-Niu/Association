package com.future.association.community.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HX·罗 on 2017/7/11.
 */

public class TieInfo implements Parcelable {

    private String id ;//帖子id
    private String title ;//帖子title
    private String create_time ;//发帖时间
    private String huifu_num ;//回复数量
    private String type ;//帖子类型 置顶或者普通

    public TieInfo(String id, String title, String create_time, String huifu_num, String type) {
        this.id = id;
        this.title = title;
        this.create_time = create_time;
        this.huifu_num = huifu_num;
        this.type = type;
    }

    protected TieInfo(Parcel in) {
        id = in.readString();
        title = in.readString();
        create_time = in.readString();
        huifu_num = in.readString();
        type = in.readString();
    }

    public static final Creator<TieInfo> CREATOR = new Creator<TieInfo>() {
        @Override
        public TieInfo createFromParcel(Parcel in) {
            return new TieInfo(in);
        }

        @Override
        public TieInfo[] newArray(int size) {
            return new TieInfo[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getHuifu_num() {
        return huifu_num;
    }

    public void setHuifu_num(String huifu_num) {
        this.huifu_num = huifu_num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(create_time);
        dest.writeString(huifu_num);
        dest.writeString(type);
    }

    /**
     * 拼接评论格式
     * @return
     */
    public String getReplyFormat(){
        return huifu_num+"评论" ;
    }

    /**
     * 判断是否置顶
     * @return
     */
    public boolean isTop(){
        if("1".equals(type)){
            return true ;
        }
        return false ;
    }

}
