package com.future.association.personal.entity;

import com.future.association.common.GsonUtils;
import com.future.association.community.utils.TextUtil;
import com.future.association.supervice.model.BaseBean;

import org.json.JSONException;

/**
 * Created by javakam on 2017/7/24 0024.
 */
public class MyExit extends BaseBean<MyExit.MyExits> {

    public static final BaseBean.Creator<MyExit> CREATOR = new BaseBean.Creator<>(MyExit.class);

    @Override
    public void parseInfo(String content) throws JSONException {
        if (TextUtil.isEmpty(content)) return;
        list = GsonUtils.jsonToList(content, MyExits.class);
    }

    /**
     * id : 1
     * image :
     * title : 测试1
     * time : 23分钟前
     */
    public static class MyExits {
        /**
         * {
         * "error": 0,
         * "info": "退出成功"
         * }
         */
        private String error;
        private String info;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}