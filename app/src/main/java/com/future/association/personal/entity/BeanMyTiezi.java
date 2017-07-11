package com.future.association.personal.entity;

/**
 * Created by javakam on 2017/7/11 0011.
 */
public class BeanMyTiezi {
    private String title;
    private String belongTo;
    private String reply;
    private String time;

    public BeanMyTiezi() {
    }

    public BeanMyTiezi(String title, String belongTo, String reply, String time) {

        this.title = title;
        this.belongTo = belongTo;
        this.reply = reply;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
