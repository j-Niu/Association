package com.future.association.personal.entity;

import com.future.association.common.GsonUtils;
import com.future.association.community.utils.TextUtil;

import org.json.JSONException;

/**
 * type": "1",//问卷状态 1 进行中 2 已完成 3已过期
 * Created by javakam on 2017/7/24 0024.
 */
public class MyWenJuan extends BaseBean<MyWenJuan.MyWenJuans> {

    public static final BaseBean.Creator<MyWenJuan> CREATOR = new BaseBean.Creator<>(MyWenJuan.class);

    @Override
    public void parseInfo(String content) throws JSONException {
        if (TextUtil.isEmpty(content)) return;
        list = GsonUtils.jsonToList(content, MyWenJuans.class);
    }


    public static class MyWenJuans {
        /**
         * {
         * "error": 0,
         * "info": [
         * {
         * "id": "1",//问卷id
         * "title": "问卷标题1",//问卷标题
         * "type": "1",//问卷状态 1 进行中 2 已完成 3已过期
         * "jifen": "5",//问卷积分奖励
         * "time": "2小时前"//问卷发布时间
         * },
         * {
         * "id": "3",
         * "title": "问卷标题3",
         * "type": "1",
         * "jifen": "5",
         * "time": "2小时前"
         * },
         * {
         * "id": "2",
         * "title": "问卷标题2",
         * "type": "1",
         * "jifen": "5",
         * "time": "2小时前"
         * }
         * ]
         * }
         */
        public String id;
        public String title;
        public String type;
        public String jifen;
        public String time;
    }
}
