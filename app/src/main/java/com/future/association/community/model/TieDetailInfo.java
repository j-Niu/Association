package com.future.association.community.model;

import android.os.Parcelable;

import com.future.association.common.MyApp;
import com.future.association.community.utils.TextUtil;

/**
 * Created by HX·罗 on 2017/7/11.
 */

public class TieDetailInfo {
    private String real_name;//发帖人
    private String avatar_url;//头像
    private String level;//发帖人等级
    private String uid;//发帖人id
    private String id;//帖子id
    private String title;//帖子标题
    private String create_time;//发帖时间
    private String address;//所属区域
    private String content;//帖子内容
    private String type;//帖子类型

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

    public String getLevel() {
        if (TextUtil.isEmpty(level)) {
            return "V0";
        } else if (!level.toLowerCase().startsWith("v")) {
            return "V" + level;
        }else{
            return level ;
        }
    }

    public void setLevel(String level) {
        this.level = level;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String typeFormat() {
        if ("1".equals(type)) {
            return "置顶帖";
        }else{
            return "普通帖";
        }
    }
}
