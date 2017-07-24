package com.future.association.personal.entity;

import android.util.Log;

import com.future.baselib.entity.BaseResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by javakam on 2017/7/24 0024.
 */
public class MyTiezi extends BaseResponse {

    public MyTiezis myInfos;

    @Override
    public void parseInfo(String content) throws JSONException {
        Log.w("123", "MyTiezis 内容 --- " + content);
        JSONObject object = new JSONObject(content);
        myInfos = new MyTiezis();
        myInfos.parse(object);
    }

    public static class MyTiezis {
        /**
         {
         "error": 0,
         "info": [
         {
         "id": "14",
         "title": "是是是",
         "create_time": "2017-07-16 11:08:14",
         "name": "公共板块",
         "huifu_num": 0
         },
         {
         "id": "13",
         "title": "测试",
         "create_time": "2017-07-16 10:44:55",
         "name": "公共板块",
         "huifu_num": 1
         },
         {
         "id": "12",
         "title": "nihao",
         "create_time": "2017-07-16 10:31:42",
         "name": "板块2",
         "huifu_num": 0
         }
         ]
         }


         */
        public String id;
        public String title;
        public String create_time;
        public String name;
        public String huifu_num;


        public void parse(JSONObject object) {
            id = object.optString("id");
            title = object.optString("title");
            create_time = object.optString("create_time");
            name = object.optString("name");
            huifu_num = object.optString("huifu_num");
        }
    }
}
