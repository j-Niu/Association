package com.future.association.personal.entity;

/**
 * Created by javakam on 2017/7/11 0011.
 */
public class BeanJiandu {
    private String imgUrl;
    private String title;
    private String createTime;
    private String type;
    private String location;
    private String content;

    public BeanJiandu() {
    }

    public BeanJiandu(String imgUrl, String title, String createTime, String type, String location, String content) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.createTime = createTime;
        this.type = type;
        this.location = location;
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
