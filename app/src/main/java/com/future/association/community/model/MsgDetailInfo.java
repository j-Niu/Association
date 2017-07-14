package com.future.association.community.model;

/**
 * Created by HX·罗 on 2017/7/4.
 */

public class MsgDetailInfo {
    private String id ;//消息通知id
    private String title ;//消息通知标题
    private String create_time ;//消息发布时间
    private String from ;//消息通知来源
    private String content ;//消息通知发布内容
    private String uid ;//发布人

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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
