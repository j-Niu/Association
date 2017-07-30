package com.future.association.personal.entity;

import android.util.Log;

import com.future.baselib.entity.BaseResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by javakam on 2017/7/30 0030.
 */
public class MyJianDu extends BaseResponse {
    public MyJianDus myInfos;

    @Override
    public void parseInfo(String content) throws JSONException {
        Log.w("123", "MyJianDu   内容 --- " + content);
        JSONObject object = new JSONObject(content);
        myInfos = new MyJianDus();
        myInfos.parse(object);
    }

    public static class MyJianDus {
        public String id;
        public String title;
        public String create_time;
        public String type;
        public String address;
        public String reason;
        public String image;//String[]

        public void parse(JSONObject object) {
            id = object.optString("id");
            title = object.optString("title");
            create_time = object.optString("create_time");
            type = object.optString("type");
            address = object.optString("address");
            reason = object.optString("reason");
            image = object.optString("image");
        }
    }
}
