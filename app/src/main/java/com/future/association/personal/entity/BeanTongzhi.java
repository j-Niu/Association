package com.future.association.personal.entity;

/**
 * Created by javakam on 2017/7/11 0011.
 */
public class BeanTongzhi {
    private String title;
    private String time;

    public BeanTongzhi() {
    }

    public BeanTongzhi(String title, String time) {
        this.title = title;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
