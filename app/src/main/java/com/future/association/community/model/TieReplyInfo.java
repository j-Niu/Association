package com.future.association.community.model;

/**
 * Created by HX·罗 on 2017/7/4.
 */

public class TieReplyInfo {
    private String name ;
    private String clazz ;
    private String address ;
    private String content ;

    public TieReplyInfo(String name, String clazz, String address, String content) {
        this.name = name;
        this.clazz = clazz;
        this.address = address;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
