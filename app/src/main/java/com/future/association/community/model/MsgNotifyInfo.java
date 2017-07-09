package com.future.association.community.model;

/**
 * Created by HX·罗 on 2017/7/3.
 */

public class MsgNotifyInfo {

    private String id ; //id
    private String content ;//内容
    private String from ;//来源
    private int replyNum ;//回复数量

    public MsgNotifyInfo(String id, String content, String from, int replyNum) {
        this.id = id;
        this.content = content;
        this.from = from;
        this.replyNum = replyNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }
    public String getReplyFormat(){
        return replyNum+"回复" ;
    }
}
