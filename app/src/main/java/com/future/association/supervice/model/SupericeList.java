package com.future.association.supervice.model;

import com.future.association.common.utils.GsonUtils;
import com.future.association.community.utils.TextUtil;

import org.json.JSONException;

/**
 * Created by rain on 2017/7/14.
 */

public class SupericeList extends BaseBean<SupericeList.SupericeListInfo> {


    public static final Creator<SupericeList> CREATOR = new Creator<>(SupericeList.class);

    @Override
    public void parseInfo(String content) throws JSONException {
        if (TextUtil.isEmpty(content)) return;
        list = GsonUtils.jsonToList(content, SupericeListInfo.class);
    }

    /**
     * id : 1
     * image :
     * title : 测试1
     * time : 23分钟前
     */
    public static class SupericeListInfo {
        private String id;
        private String image;
        private String title;
        private String time;
        private String nature;
        private String type;
        private String dead_time;

        public String getNature() {
            return nature;
        }

        public void setNature(String nature) {
            this.nature = nature;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDead_time() {
            return dead_time;
        }

        public void setDead_time(String dead_time) {
            this.dead_time = dead_time;
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

}

