package com.future.association.personal.entity;

import com.future.baselib.entity.BaseResponse;
import com.future.baselib.utils.JLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javakam on 2017/7/20.
 */

public class JDResponse extends BaseResponse {

    public List<JDDetail> data;

    @Override
    public void parseInfo(String content) {
        String str = content.replace("{", "[{");
        str = str.replace("}", "}]");
        JSONArray array = null;
        try {
            array = new JSONArray(str);
            data = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JDDetail jdDetail = new JDDetail();
                jdDetail.parse(array.optJSONObject(i));
                data.add(jdDetail);
            }
            JLog.e("json", "data --- " + data.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static class JDDetail {
        /*
        :{"error":0,"info":
        {"id":"87","avatar_url":"http:\/\/139.224.70.219:85",
        "real_name":"love","quanxian":"1",
        "address":"\u5317\u4eac\u5e02\u5317\u4eac\u5e02\u4e1c\u57ce\u533a",
        "jifen":"20","level":"V1","chenghao":"\u7b49\u7ea71",
        "level_img":"http:\/\/139.224.70.219:85\/Uploads\/Download\/2017-07-23\/5974943b12c10.png",
        "jifencha":5}}
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
