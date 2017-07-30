package com.future.association.personal.entity;

import android.util.Log;

import com.future.baselib.entity.BaseResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by javakam on 2017/7/24 0024.
 */
public class MyLevel extends BaseResponse {

    public MyLevels myLevels;

    @Override
    public void parseInfo(String content) throws JSONException {
        Log.w("123", "MyLevel  内容 --- " + content);
        JSONObject object = new JSONObject(content);
        myLevels = new MyLevels();
        myLevels.parse(object);
    }

    public static class MyLevels {


        public void parse(JSONObject object) {
        }
    }
}
