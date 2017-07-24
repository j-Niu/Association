package com.future.association.personal.entity;

import android.util.Log;

import com.future.baselib.entity.BaseResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by javakam on 2017/7/24 0024.
 */
public class MyNotice extends BaseResponse {

    public MyNotices myInfos;

    @Override
    public void parseInfo(String content) throws JSONException {
        Log.w("123", "MyWenJuan 内容 --- " + content);
        JSONObject object = new JSONObject(content);
        myInfos = new MyNotices();
        myInfos.parse(object);
    }

    public static class MyNotices {
        /**
         {
         "error": 0,
         "info": [
         {
         "id": "16",
         "title": "测试222",
         "name": "板块2",
         "create_time": "21分钟前"
         },
         {
         "id": "15",
         "title": "你好 这是测试",
         "name": "板块2",
         "create_time": "29分钟前"
         },
         {
         "id": "14",
         "title": "是是是",
         "name": "公共板块",
         "create_time": "24分钟前"
         },
         {
         "id": "13",
         "title": "测试",
         "name": "公共板块",
         "create_time": "4小时前"
         },
         {
         "id": "12",
         "title": "nihao",
         "name": "板块2",
         "create_time": "21分钟前"
         }
         ]
         }
         */
        public String id;
        public String title;
        public String name;
        public String create_time;

        public void parse(JSONObject object) {
            id = object.optString("id");
            title = object.optString("title");
            name = object.optString("name");
            create_time = object.optString("create_time");
        }
    }
}
