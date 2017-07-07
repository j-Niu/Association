package com.future.association.community.model;

/**
 * Created by HX·罗 on 2017/7/4.
 */

public class MsgDetailInfo {
    private String title ;
    private String date ;
    private String from ;
    private String content ;

    public MsgDetailInfo(String title, String date, String from, String content) {
        this.title = title;
        this.date = date;
        this.from = from;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
