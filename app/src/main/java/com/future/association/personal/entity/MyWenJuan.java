package com.future.association.personal.entity;

import com.future.association.common.GsonUtils;
import com.future.association.community.utils.TextUtil;

import org.json.JSONException;

import java.io.Serializable;

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


    public static class MyWenJuans implements Serializable {
        /**
         * "id": null,
         * "title": null,
         * "jianjie": null,
         * "type": null,
         * "status": "1",
         * "jifen": "5",
         * "showurl": "http://139.224.70.219:85/app/wenjuancanyu.html?id=41&usertoken=58deff49f29d72943c4d1f8f1100db11",
         * "time": "6天前"
         */
        public String id;
        public String title;
        public String jianjie;
        public String type;
        public String status;
        public String jifen;
        public String showurl;
        public String time;

    }
}
