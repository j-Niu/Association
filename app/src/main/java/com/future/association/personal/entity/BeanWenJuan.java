package com.future.association.personal.entity;

/**
 * Created by javakam on 2017/7/11 0011.
 */
public class BeanWenJuan {
    private String title;
    private String increaseCore;
    private String time;
    private int state;//0已过期  1已完成  2进行中

    public BeanWenJuan() {
    }

    public BeanWenJuan(String title, String increaseCore, String time
            , int state) {
        this.title = title;
        this.increaseCore = increaseCore;
        this.time = time;
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIncreaseCore() {
        return increaseCore;
    }

    public void setIncreaseCore(String increaseCore) {
        this.increaseCore = increaseCore;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
