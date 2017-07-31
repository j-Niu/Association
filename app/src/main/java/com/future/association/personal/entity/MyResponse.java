package com.future.association.personal.entity;

import com.future.association.common.GsonUtils;
import com.future.association.community.utils.TextUtil;

import org.json.JSONException;

/**
 * Created by javakam on 2017/7/24 0024.
 */
public class MyResponse extends BaseBean<MyResponse.MyResponses> {

    public static final BaseBean.Creator<MyResponse> CREATOR = new BaseBean.Creator<>(MyResponse.class);

    @Override
    public void parseInfo(String content) throws JSONException {
        if (TextUtil.isEmpty(content)) return;
        list = GsonUtils.jsonToList(content, MyResponses.class);
    }


    public static class MyResponses {
        /**
         * {
         * "error": 0,
         * "info": [
         * {
         * "id": "1",
         * "title": "测试1",//帖子标题
         * "create_time": "2017-07-15 16:28:43",//回复最新时间
         * "name": "公共板块",//所属板块
         * "tiezi_id": "1",//帖子id
         * "huifu_num": 2//
         * },
         * {
         * "id": "2",
         * "title": "测试2",
         * "create_time": "2017-07-15 16:28:41",
         * "name": "板块2",
         * "tiezi_id": "2",
         * "huifu_num": 2
         * }
         * ]
         * }
         */
        public String id;
        public String title;
        public String create_time;
        public String name;
        public String tiezi_id;
        public String huifu_num;
    }
}
