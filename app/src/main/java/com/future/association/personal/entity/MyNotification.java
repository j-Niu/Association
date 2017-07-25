package com.future.association.personal.entity;

import android.util.Log;

import com.future.baselib.entity.BaseResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by javakam on 2017/7/24 0024.
 */
public class MyNotification extends BaseResponse {

    public MyNotifications myInfos;

    @Override
    public void parseInfo(String content) throws JSONException {
        Log.w("123", "MyNotification 内容 --- " + content);
        JSONObject object = new JSONObject(content);
        myInfos = new MyNotifications();
        myInfos.parse(object);
    }

    public static class MyNotifications {
        /**
         * {
         "error": 0,
         "info": [
         {
         "id": "1",//消息通知id
         "title": "测试1",//消息通知标题
         "from": "阿斯达",//消息通知来源
         "comment_num": "2"//消息通知评论数
         },
         {
         "id": "2",
         "title": "测试2",
         "from": "阿萨德",
         "comment_num": "5"
         }
         ]
         }
         */
        public String id;
        public String title;
        public String from;
        public String comment_num;

        public void parse(JSONObject object) {
            id = object.optString("id");
            title = object.optString("title");
            from = object.optString("from");
            comment_num = object.optString("comment_num");
        }
    }
}
