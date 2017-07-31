package com.future.association.personal.entity;

import com.future.association.common.GsonUtils;
import com.future.association.community.utils.TextUtil;

import org.json.JSONException;

/**
 * Created by javakam on 2017/7/30 .
 */
public class MyJianDu extends BaseBean<MyJianDu.MyJianDus> {
    public static final BaseBean.Creator<MyJianDu> CREATOR = new BaseBean.Creator<>(MyJianDu.class);

    @Override
    public void parseInfo(String content) throws JSONException {
        if (TextUtil.isEmpty(content)) return;
        list = GsonUtils.jsonToList(content, MyJianDus.class);
    }

    /**
     * id : 1
     * image :
     * title : 测试1
     * time : 23分钟前
     */
    public static class MyJianDus {
        private String id;
        private String title;
        private String create_time;
        private String type;
        private String address;
        private String reason;
        private String[] image;//String[]

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
}
