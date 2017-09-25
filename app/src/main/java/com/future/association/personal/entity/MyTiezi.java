package com.future.association.personal.entity;

import com.future.association.common.utils.GsonUtils;
import com.future.association.community.utils.TextUtil;

import org.json.JSONException;

import java.io.Serializable;

/**
 * Created by javakam on 2017/7/24 0024.
 */
public class MyTiezi extends BaseBean<MyTiezi.MyTiezis> {

    public static final BaseBean.Creator<MyTiezi> CREATOR = new BaseBean.Creator<>(MyTiezi.class);

    @Override
    public void parseInfo(String content) throws JSONException {
        if (TextUtil.isEmpty(content)) return;
        list = GsonUtils.jsonToList(content, MyTiezis.class);
    }


    public static class MyTiezis implements Serializable{
        /**
         * {
         * "error": 0,
         * "info": [
         * {
         * "id": "14",
         * "title": "是是是",
         * "create_time": "2017-07-16 11:08:14",
         * "name": "公共板块",
         * "huifu_num": 0
         * },
         * {
         * "id": "13",
         * "title": "测试",
         * "create_time": "2017-07-16 10:44:55",
         * "name": "公共板块",
         * "huifu_num": 1
         * },
         * {
         * "id": "12",
         * "title": "nihao",
         * "create_time": "2017-07-16 10:31:42",
         * "name": "板块2",
         * "huifu_num": 0
         * }
         * ]
         * }
         */
        public String id;
        public String title;
        public String create_time;
        public String name;
        public String huifu_num;

    }
}
