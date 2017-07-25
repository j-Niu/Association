package com.future.association.personal.entity;

import android.util.Log;

import com.future.baselib.entity.BaseResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by javakam on 2017/7/24 0024.
 */
public class MyResponse extends BaseResponse {

    public MyResponses myInfos;

    @Override
    public void parseInfo(String content) throws JSONException {
        Log.w("123", "MyResponse 内容 --- " + content);
        JSONObject object = new JSONObject(content);
        myInfos = new MyResponses();
        myInfos.parse(object);
    }

    public static class MyResponses {
        /**
         * {
         * "error": 0,
         * "info": [
         * {
         * "id": "1",
         * "title": "测试1",//帖子标题
         * "create_time": "2017-07-15 16:28:43",//回复最新时间
         * "name": "公共板块",//所属板块
         * "tiezi_id": "1",//帖子id
         * "huifu_num": 2//
         * },
         * {
         * "id": "2",
         * "title": "测试2",
         * "create_time": "2017-07-15 16:28:41",
         * "name": "板块2",
         * "tiezi_id": "2",
         * "huifu_num": 2
         * }
         * ]
         * }
         */
        public String id;
        public String title;
        public String create_time;
        public String name;
        public String tiezi_id;
        public String huifu_num;


        public void parse(JSONObject object) {
            id = object.optString("id");
            title = object.optString("title");
            create_time = object.optString("create_time");
            name = object.optString("name");
            tiezi_id = object.optString("tiezi_id");
            huifu_num = object.optString("huifu_num");
        }
    }
}
