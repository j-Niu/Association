package com.future.association.news.entity;

import com.future.baselib.entity.BaseResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jniu on 2017/7/20.
 */

public class NewsBannerResponse extends BaseResponse {

    public List<NewsBanner> data;

    @Override
    public void parseInfo(String content) throws JSONException {
        JSONArray array = new JSONArray(content);
        data = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            NewsBanner banner = new NewsBanner();
            banner.parse(array.optJSONObject(i));
            data.add(banner);
        }
    }

    public static class NewsBanner{
        public String id;
        public String banner;
        public String title;

        public void parse(JSONObject object){
            id = object.optString("id");
            banner = object.optString("banner");
            title = object.optString("title");
        }
    }
}
