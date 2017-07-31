package com.future.association.personal.entity;

import com.future.association.common.GsonUtils;
import com.future.association.community.utils.TextUtil;

import org.json.JSONException;

/**
 * Created by javakam on 2017/7/24 0024.
 */
public class MyLevel extends BaseBean<MyLevel.MyLevels> {

    public static final BaseBean.Creator<MyLevel> CREATOR = new BaseBean.Creator<>(MyLevel.class);

    @Override
    public void parseInfo(String content) throws JSONException {
        if (TextUtil.isEmpty(content)) return;
        list = GsonUtils.jsonToList(content, MyLevels.class);
    }


    public static class MyLevels {
        /**
        "id": "1",
        "level": "V1",//等级
        "level_name": "等级1",//称号
        "jifen": "10"//积分
        */

        public String id;
        public String level;
        public String level_name;
        public String jifen;

    }


}
