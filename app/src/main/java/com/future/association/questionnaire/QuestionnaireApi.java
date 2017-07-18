package com.future.association.questionnaire;

import android.content.Context;

import com.future.association.questionnaire.models.QuestionDetail;
import com.future.association.questionnaire.models.QuestionList;
import com.future.baselib.utils.HttpRequest;

/**
 * Created by rain on 2017/7/17.
 */

public class QuestionnaireApi {
    public static final String HOT_WEN_JUAN_CODE = "_hotwenjuanliebiao_001";
    public static final String MY_WEN_JUAN_CODE = "_mywenjuan_001";
    public static final String WEN_JUAN_DETAIL_CODE = "_wenjuanxq_001";
    public static final String WEN_JUAN_COMMIT_CODE = "_tjwenjuan_001";
    public static final String WEN_JUAN_STEP_CODE = "_wenjuanyd_001";
    public static final String WEN_JUAN_JOIN_CODE = "_cywenjuan_001";
    private static QuestionnaireApi instance;

    private QuestionnaireApi() {
    }

    public synchronized static QuestionnaireApi getInstance() {
        if (instance == null) {
            instance = new QuestionnaireApi();
        }
        return instance;
    }

    public HttpRequest<QuestionList> getHotWenjuan(Context context, String userToken, String page) {
        return new HttpRequest<QuestionList>()
                .with(context)
                .addParam("apiCode", HOT_WEN_JUAN_CODE)
                .addParam("userToken", userToken)
                .addParam("page", page)
                .addParam("size", "20");
    }

    public HttpRequest<QuestionList> getMyWenjuan(Context context, String userToken, String page) {
        return new HttpRequest<QuestionList>()
                .with(context)
                .addParam("apiCode", MY_WEN_JUAN_CODE)
                .addParam("userToken", userToken)
                .addParam("page", page)
                .addParam("size", "20");
    }

    public HttpRequest<QuestionDetail> getWenjuanDetail(Context context, String userToken, String id) {
        return new HttpRequest<QuestionDetail>()
                .with(context)
                .addParam("apiCode", WEN_JUAN_DETAIL_CODE)
                .addParam("userToken", userToken)
                .addParam("id",id);
    }
}
