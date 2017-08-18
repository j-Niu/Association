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
     "id": "1",//问卷id
     "title": "问卷标题1",//问卷标题
     "type": "1",//问卷状态 1 进行中 2 已完成 3已过期
     "jifen": "5",//问卷积分奖励
     "time": "2小时前"//问卷发布时间
     */

    private String id;
    private String title;
    private String jifen;
    private String type;
    private String time;
    private String showurl;
    private String jianjie;
    private String status;


    @Override
    public void parseInfo(String content) throws JSONException {
        list = GsonUtils.jsonToList(content,QuestionList.class);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getShowurl() {
        return showurl;
    }

    public void setShowurl(String showurl) {
        this.showurl = showurl;
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
