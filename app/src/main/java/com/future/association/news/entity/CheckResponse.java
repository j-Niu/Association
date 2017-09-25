package com.future.association.news.entity;

import com.future.baselib.entity.BaseResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jniu on 2017/9/4.
 */

public class CheckResponse extends BaseResponse {

    public int versionCode = 0;
    public String apk = "";

    @Override
    public void parseInfo(String content) throws JSONException {
        JSONObject object = new JSONObject(content);
        versionCode = object.optInt("app_version");
        apk = object.optString("downloadurl");
    }
}
