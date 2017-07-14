package com.future.association.login.bean;

import android.util.Log;

import com.future.baselib.entity.BaseResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/7/10.
 */

public class UserResponse extends BaseResponse {
    public String userToken;
    public String userId;
    public String expireTime;
    public String avatar_url;
    public String is_trade;
    public String quanxian;

    @Override
    public void parseInfo(String content) throws JSONException {
        JSONObject obj = new JSONObject(content);
        userToken = obj.optString("userToken");
        userId = obj.optString("userId");
        expireTime = obj.optString("expireTime");
        avatar_url = obj.optString("avatar_url");
        is_trade = obj.optString("is_trade");
        quanxian = obj.optString("quanxian");
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "userToken='" + userToken + '\'' +
                ", userId='" + userId + '\'' +
                ", expireTime='" + expireTime + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", is_trade='" + is_trade + '\'' +
                '}';
    }
}
