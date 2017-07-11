package com.future.association.personal.entity;

/**
 * Created by javakam on 2017/7/11 0011.
 */
public class BeanMyResponse {
    private String title;
    private String belongTo;
    private String time;

    public BeanMyResponse() {
    }

    public BeanMyResponse(String title, String belongTo, String time) {
        this.title = title;
        this.belongTo = belongTo;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
