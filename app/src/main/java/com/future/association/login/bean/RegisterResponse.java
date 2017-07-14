package com.future.association.login.bean;

import com.future.baselib.entity.BaseResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/7/11.
 */

public class RegisterResponse extends BaseResponse{
    public String userId;
    public String userToken;
    public String expireTime;

    @Override
    public void parseInfo(String content) throws JSONException {
        JSONObject obj = new JSONObject(content);
        userToken = obj.optString("userToken");
        userId = obj.optString("userId");
        expireTime = obj.optString("expireTime");
    }
}
