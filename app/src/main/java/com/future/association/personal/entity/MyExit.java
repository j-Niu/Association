package com.future.association.personal.entity;

import android.util.Log;

import com.future.baselib.entity.BaseResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by javakam on 2017/7/24 0024.
 */
public class MyExit extends BaseResponse {

    public MyExits myExits;

    @Override
    public void parseInfo(String content) throws JSONException {
        Log.w("123", "MyExits  内容 --- " + content);
        JSONObject object = new JSONObject(content);
        myExits = new MyExits();
        myExits.parse(object);
    }

    public static class MyExits {
        /**
         * {
         * "error": 0,
         * "info": "退出成功"
         * }
         */
        public String error;
        public String info;

        public void parse(JSONObject object) {
            error = object.optString("error");
            info = object.optString("info");
        }
    }
}
