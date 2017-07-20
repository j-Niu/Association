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

public class NewsResponse extends BaseResponse {

    public List<NewsDetail> data;

    @Override
    public void parseInfo(String content) throws JSONException {
        JSONArray array = new JSONArray(content);
        data = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            NewsDetail newsDetail = new NewsDetail();
            newsDetail.parse(array.optJSONObject(i));
            data.add(newsDetail);
        }
    }

    public static class NewsDetail{
        public String id;
        public String image;
        public String title;
        public String time;

        public void parse(JSONObject object){
            id = object.optString("id");
            image = object.optString("image");
            title = object.optString("title");
            time = object.optString("time");
        }
    }
}
