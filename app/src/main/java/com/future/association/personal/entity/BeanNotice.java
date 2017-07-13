package com.future.association.personal.entity;

/**
 * Created by javakam on 2017/7/11 0011.
 */
public class BeanNotice {
    private String title;
    private String partName;
    private String lasttime;

    public BeanNotice() {
    }

    public BeanNotice(String title, String partName, String lasttime) {
        this.title = title;
        this.partName = partName;
        this.lasttime = lasttime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }
}
