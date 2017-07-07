package com.future.association.community.model;

/**
 * Created by HX·罗 on 2017/7/4.
 */

public class BannerInfo {

    private String title ;
    private String date ;
    private int replyNum ;

    public BannerInfo(String title, String date, int replyNum) {
        this.title = title;
        this.date = date;
        this.replyNum = replyNum;
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

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }
    public String getReplyFormat(){
        return replyNum+"评论" ;
    }
}
