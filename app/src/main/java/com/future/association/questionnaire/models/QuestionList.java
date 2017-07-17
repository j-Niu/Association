package com.future.association.questionnaire.models;

import com.future.association.common.GsonUtils;
import com.future.association.supervice.model.BaseBean;

import org.json.JSONException;

/**
 * Created by rain on 2017/7/17.
 */

public class QuestionList extends BaseBean<QuestionList> {
public static final Creator<QuestionList> CREATOR = new Creator<>(QuestionList.class);
    /**
     * id : 1
     * title : 问卷标题1
     * jifen : 5
     * time : 24分钟前
     */

    private String id;
    private String title;
    private String jifen;
    private String time;

    @Override
    public void parseInfo(String content) throws JSONException {
        list = GsonUtils.jsonToList(content,QuestionList.class);
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

    public String getJifen() {
        return jifen;
    }

    public void setJifen(String jifen) {
        this.jifen = jifen;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
