package com.future.association.login.bean;

        import com.future.baselib.entity.BaseResponse;

        import org.json.JSONException;

/**
 * Created by Mwh on 2017/8/1.
 */

public class CityResponse extends BaseResponse {
    public String jsonData;

    @Override
    public void parseInfo(String content) throws JSONException {
        jsonData = content;
    }
}
