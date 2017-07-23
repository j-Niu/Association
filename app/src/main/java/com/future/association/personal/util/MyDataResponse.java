package com.future.association.personal.util;

//import com.future.association.community.utils.JSONUtils;

import com.future.association.community.utils.JSONUtils;
import com.future.association.community.utils.TextUtil;
import com.future.baselib.entity.BaseResponse;

import java.util.ArrayList;

/**
 * Copy from HX·罗 on 2017/7/10.
 */

public class MyDataResponse<T extends Object> extends BaseResponse {
    private Class<T> clazz;
    public T info;
    public ArrayList<T> infos;

    public MyDataResponse init(Class clazz) {
        this.clazz = clazz;
        return this;
    }

    @Override
    public void parseInfo(String content) {
        try {
            if (!TextUtil.isEmpty(content) && content.startsWith("{")) {
                this.info = (T) JSONUtils.jsonToObject(content, clazz);
            } else if (!TextUtil.isEmpty(content) && content.startsWith("[")) {
                this.infos = JSONUtils.jsonToArrayObj(content, clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}