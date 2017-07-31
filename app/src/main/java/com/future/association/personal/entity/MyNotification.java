package com.future.association.personal.entity;

import com.future.association.common.GsonUtils;
import com.future.association.community.utils.TextUtil;

import org.json.JSONException;

/**
 * Created by javakam on 2017/7/24 0024.
 */
public class MyNotification extends BaseBean<MyNotification.MyNotifications> {

    public static final BaseBean.Creator<MyNotification> CREATOR = new BaseBean.Creator<>(MyNotification.class);

    @Override
    public void parseInfo(String content) throws JSONException {
        if (TextUtil.isEmpty(content)) return;
        list = GsonUtils.jsonToList(content, MyNotifications.class);
    }


    public static class MyNotifications {
        /**
         * {
         "error": 0,
         "info": [
         {
         "id": "1",//消息通知id
         "title": "测试1",//消息通知标题
         "from": "阿斯达",//消息通知来源
         "comment_num": "2"//消息通知评论数
         },
         {
         "id": "2",
         "title": "测试2",
         "from": "阿萨德",
         "comment_num": "5"
         }
         ]
         }
         */
        private String id;
        private String title;
        private String from;
        private String comment_num;

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

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getComment_num() {
            return comment_num;
        }

        public void setComment_num(String comment_num) {
            this.comment_num = comment_num;
        }
    }

}
