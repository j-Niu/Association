package com.future.association.community.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.future.association.community.base.BaseEntity;

/**
 * Created by HX·罗 on 2017/7/3.
 */

public class    MsgNotifyInfo implements Parcelable{

    private String id ;//id
    private String title ;//内容
    private String from ;//来源
    private String create_time ;//通知发布时间
    private String comment_num ;//回复数量

    protected MsgNotifyInfo(Parcel in) {
        id = in.readString();
        title = in.readString();
        from = in.readString();
        create_time = in.readString();
        comment_num = in.readString();
    }

    public static final Creator<MsgNotifyInfo> CREATOR = new Creator<MsgNotifyInfo>() {
        @Override
        public MsgNotifyInfo createFromParcel(Parcel in) {
            return new MsgNotifyInfo(in);
        }

        @Override
        public MsgNotifyInfo[] newArray(int size) {
            return new MsgNotifyInfo[size];
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getComment_num() {
        return comment_num;
    }

    public void setComment_num(String comment_num) {
        this.comment_num = comment_num;
    }

    public String getReplyFormat(){
        return comment_num+"回复" ;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(from);
        dest.writeString(create_time);
        dest.writeString(comment_num);
    }
}
