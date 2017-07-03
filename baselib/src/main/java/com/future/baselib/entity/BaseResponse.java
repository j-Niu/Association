package com.future.baselib.entity;

import android.text.TextUtils;

import com.future.baselib.utils.JLog;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jniu on 2017/5/17.
 */

public abstract class BaseResponse {

    public int error;
    public String info;

    public void parseJson(String json){
        JLog.e("json",json);
        try {
            if (TextUtils.isEmpty(json)) {
                return;
            }
            JSONObject object = new JSONObject(json);
            error = object.optInt("error");
            if (error == 0) {
                String info;
                if (object.optJSONObject("info") != null) {
                    info = object.optJSONObject("info").toString();
                    parseInfo(info);
                }else if (object.optJSONArray("info")!=null){
                    info = object.optJSONArray("info").toString();
                    parseInfo(info);
                }else{
                    this.info = object.optString("info");
                }
            }else{
                info = object.optString("info");
            }
        }catch (JSONException e){
            JLog.e("BaseResponse","json格式有误:"+json);
        }
    }

    public abstract void parseInfo(String content) throws JSONException;
}
