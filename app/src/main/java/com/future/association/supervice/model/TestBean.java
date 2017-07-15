package com.future.association.supervice.model;

import android.util.Log;

import com.future.baselib.entity.BaseResponse;

import org.json.JSONException;

/**
 * Created by rain on 2017/7/14.
 */

public class TestBean extends BaseResponse {
    @Override
    public void parseInfo(String content) throws JSONException {
        Log.d("json",content);
    }
}
