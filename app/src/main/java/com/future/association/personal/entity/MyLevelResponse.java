package com.future.association.personal.entity;

import com.future.baselib.entity.BaseResponse;
import com.future.baselib.utils.JLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javakam on 2017/7/24 0024.
 */
public class MyLevelResponse extends BaseResponse {


    public List<MyLevels> data;

    @Override
    public void parseInfo(String content) {
        String str = content.replace("{", "[{");
        str = str.replace("}", "}]");
        JSONArray array = null;
        try {
            array = new JSONArray(str);
            data = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                MyLevels jdDetail = new MyLevels();
                jdDetail.parse(array.optJSONObject(i));
                data.add(jdDetail);
            }
            JLog.e("json", "data --- " + data.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static class MyLevels {
        /**
         * "id": "1",
         * "level": "V1",//等级
         * "level_name": "等级1",//称号
         * "jifen": "10"//积分
         */

        public String id;
        public String level;
        public String level_name;
        public String jifen;

        public void parse(JSONObject object) {
            id = object.optString("id");
            level = object.optString("level");
            level_name = object.optString("level_name");
            jifen = object.optString("jifen");
        }
    }
}