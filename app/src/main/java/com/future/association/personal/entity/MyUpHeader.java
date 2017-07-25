package com.future.association.personal.entity;

import android.util.Log;

import com.future.baselib.entity.BaseResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by javakam on 2017/7/23 0023.
 */
public class MyUpHeader extends BaseResponse {
    public MyUpHeaders myInfos;

    @Override
    public void parseInfo(String content) throws JSONException {
        Log.w("123", "MyUpHeaders  内容 --- " + content);
        JSONObject object = new JSONObject(content);
        myInfos = new MyUpHeaders();
        myInfos.parse(object);
    }

    public static class MyUpHeaders {

        public void parse(JSONObject object) {

        }
    }
}
