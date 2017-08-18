package com.future.association.news.entity;

import com.future.baselib.entity.BaseResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jniu on 2017/7/20.
 */

public class NewsDetailsResponse extends BaseResponse {

    public NewsDetails newsDetails;

    @Override
    public void parseInfo(String content) throws JSONException {
        JSONObject object = new JSONObject(content);
        newsDetails = new NewsDetails();
        newsDetails.parse(object);
    }

    public static class NewsDetails{
        /*
        "id": "1",
        "title": "asd阿萨德",//标题
        "banner": "http://47.91.104.37asd",//轮播图
        "create_time": "2017-07-09 16:40:24",//发布时间
        "content": "的说法是对方是否都是粉色的",//发布内容
        "image": ""//资讯缩略图
         */
        public String title;
        public String create_time;
        public String content;
        public String info_from;

        public void parse(JSONObject object){
            title = object.optString("title");
            create_time = object.optString("create_time");
            content = object.optString("content");
            info_from = object.optString("info_from");
        }
    }
}
