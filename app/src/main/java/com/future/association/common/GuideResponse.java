package com.future.association.common;

import com.future.baselib.entity.BaseResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jniu on 2017/9/14.
 */

public class GuideResponse extends BaseResponse {

    public String img_url;

    @Override
    public void parseInfo(String content) throws JSONException {
        JSONObject object = new JSONObject(content);
        img_url = object.optString("img_url");
    }
}
