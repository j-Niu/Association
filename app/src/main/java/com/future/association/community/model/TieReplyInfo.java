package com.future.association.community.model;

import com.future.association.community.utils.TextUtil;

/**
 * Created by HX·罗 on 2017/7/4.
 */

public class TieReplyInfo {
    private String id ;//回复ID
    private String uid ;//回复人ID
    private String real_name ;//回复人昵称
    private String avatar_url ;//回复人头像
    private String level ;//回复人等级
    private String tiezi_id ;//帖子id
    private String content ;//回复人内容
    private String create_time ;//回复时间
    private String address ;//地址

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
        if(TextUtil.isEmpty(level)){
            return "V0" ;
        }
        return "V"+level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTiezi_id() {
        return tiezi_id;
    }

    public void setTiezi_id(String tiezi_id) {
        this.tiezi_id = tiezi_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
