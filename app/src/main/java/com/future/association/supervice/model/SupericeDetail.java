package com.future.association.supervice.model;

import com.future.association.common.utils.GsonUtils;

import org.json.JSONException;

/**
 * Created by rain on 2017/7/14.
 */

public class SupericeDetail extends BaseBean<SupericeDetail> {
    public static final Creator<SupericeDetail> CREATOR = new Creator<>(SupericeDetail.class);


    /**
     * id : 1
     * title : 测试1
     * create_time : 2017-07-09 20:53:09
     * type : 游戏
     * address : 啊是打算打说的
     * reason : 阿斯达是打算打说啊说的撒的
     * image : http://47.91.104.37asdasdasd
     */

    private String id;
    private String title;
    private String create_time;
    private String type;
    private String nature;
    private String hangye;//和type是一样的
    private String address;
    private String reason;
    private String[] image;

    @Override
    public void parseInfo(String content) throws JSONException {
        infoBean = GsonUtils.jsonToBean(content,SupericeDetail.class);
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getHangye() {
        return hangye;
    }

    public void setHangye(String hangye) {
        this.hangye = hangye;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String[] getImage() {
        return image;
    }

    public void setImage(String[] image) {
        this.image = image;
    }


}

