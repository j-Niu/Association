package com.future.association.questionnaire.models;

import com.future.association.common.GsonUtils;
import com.future.association.supervice.model.BaseBean;

import org.json.JSONException;

import java.util.List;

/**
 * Created by rain on 2017/7/17.
 */

public class QuestionDetail extends BaseBean<QuestionDetail> {
public static final Creator<QuestionDetail> CREATOR = new Creator<>(QuestionDetail.class);
    /**
     * id : 1
     * title : 题目1
     * type : 1
     * options : ["选项1","选项2","选项3","选项4"]
     */

    private String id;
    private String title;
    private String type;
    private List<String> options;

    @Override
    public void parseInfo(String content) throws JSONException {
        list = GsonUtils.jsonToList(content,QuestionDetail.class);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
