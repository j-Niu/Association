package com.future.association.community.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HX·罗 on 2017/7/11.
 */

public class TieInfo implements Parcelable {
/*
*
* 名字:real_name
头像:avatar_url
角色:user_type[1.消费者2.志愿者]
阅读量:click_num
* */
    private String id ;//帖子id
    private String title ;//帖子title
    private String create_time ;//发帖时间
    private String huifu_num ;//回复数量
    private String type ;//帖子类型 置顶或者普通
    private String real_name ;//
    private String avatar_url ;//
    private String user_type ;//
    private String click_num ;//

    public TieInfo(String id, String title, String create_time, String huifu_num, String type) {
        this.id = id;
        this.title = title;
        this.create_time = create_time;
        this.huifu_num = huifu_num;
        this.type = type;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getClick_num() {
        return click_num;
    }

    public void setClick_num(String click_num) {
        this.click_num = click_num;
    }


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


    /**
     * 拼接评论格式
     * @return
     */
    public String getReplyFormat(){
        return huifu_num+"" ;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.create_time);
        dest.writeString(this.huifu_num);
        dest.writeString(this.type);
        dest.writeString(this.real_name);
        dest.writeString(this.avatar_url);
        dest.writeString(this.user_type);
        dest.writeString(this.click_num);
    }

    protected TieInfo(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.create_time = in.readString();
        this.huifu_num = in.readString();
        this.type = in.readString();
        this.real_name = in.readString();
        this.avatar_url = in.readString();
        this.user_type = in.readString();
        this.click_num = in.readString();
    }

    public static final Creator<TieInfo> CREATOR = new Creator<TieInfo>() {
        @Override
        public TieInfo createFromParcel(Parcel source) {
            return new TieInfo(source);
        }

        @Override
        public TieInfo[] newArray(int size) {
            return new TieInfo[size];
        }
    };
}
