package com.future.association.questionnaire.models;

import com.future.association.common.GsonUtils;
import com.future.association.supervice.model.BaseBean;

import org.json.JSONException;

/**
 * Created by rain on 2017/7/17.
 */

public class QuestionStepInfo extends BaseBean<QuestionStepInfo> {
public static final Creator<QuestionStepInfo> CREATOR = new Creator<>(QuestionStepInfo.class);
    /**
     * id : 1
     * title : 问卷标题1
     * jianjie : 问卷简介1
     * type : 1
     * over_time : 2017 08 22
     * timu_num : 3
     */

    private String id;
    private String title;
    private String jianjie;
    private String type;
    private String over_time;
    private int timu_num;

    @Override
    public void parseInfo(String content) throws JSONException {
        infoBean = GsonUtils.jsonToBean(content,QuestionStepInfo.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOver_time() {
        return over_time;
    }

    public void setOver_time(String over_time) {
        this.over_time = over_time;
    }

    public int getTimu_num() {
        return timu_num;
    }

    public void setTimu_num(int timu_num) {
        this.timu_num = timu_num;
    }
}
