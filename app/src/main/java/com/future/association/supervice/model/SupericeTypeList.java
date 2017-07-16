package com.future.association.supervice.model;

import com.future.association.common.GsonUtils;

import org.json.JSONException;

/**
 * Created by rain on 2017/7/14.
 */

public class SupericeTypeList extends BaseBean {

    public static final Creator<SupericeTypeList> CREATOR = new Creator<>(SupericeTypeList.class);


    /**
     * id : 1
     * image :
     * title : 测试1
     * time : 23分钟前
     */

    private String id;
    private String image;
    private String title;
    private String time;

    @Override
    public void parseInfo(String content) throws JSONException {
        GsonUtils.jsonToBean(content,SupericeTypeList.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

