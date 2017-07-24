package com.future.association.personal.entity;

import android.util.Log;

import com.future.baselib.entity.BaseResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by javakam on 2017/7/23 0023.
 */
public class MyInfoResponse extends BaseResponse {
    public MyInfos myInfos;

    @Override
    public void parseInfo(String content) throws JSONException {
        Log.w("123", "我是内容 --- " + content);
        JSONObject object = new JSONObject(content);
        myInfos = new MyInfos();
        myInfos.parse(object);
    }

    public static class MyInfos {
        /**
         * id : 136
         * avatar_url :
         * real_name :
         * quanxian : 1
         * address : 山西晋城陵川县
         * jifen : 33
         * level : V2
         * chenghao : 等级2
         * level_img :
         * jifencha : 27
         */
        public String id;
        public String avatar_url;
        public String real_name;
        public String quanxian;
        public String address;
        public String jifen;
        public String level;
        public String chenghao;
        public String level_img;
        public String jifencha;

        public void parse(JSONObject object) {
            id = object.optString("id");
            avatar_url = object.optString("avatar_url");
            real_name = object.optString("real_name");
            quanxian = object.optString("quanxian");
            address = object.optString("address");
            jifen = object.optString("jifen");
            level = object.optString("level");
            chenghao = object.optString("chenghao");
            level_img = object.optString("level_img");
            jifencha = object.optString("jifencha");
        }
    }
}
