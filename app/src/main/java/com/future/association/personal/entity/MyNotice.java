package com.future.association.personal.entity;

import com.future.association.common.GsonUtils;
import com.future.association.community.utils.TextUtil;

import org.json.JSONException;

/**
 * Created by javakam on 2017/7/24 0024.
 */
public class MyNotice extends BaseBean<MyNotice.MyNotices> {

    public static final BaseBean.Creator<MyNotice> CREATOR = new BaseBean.Creator<>(MyNotice.class);

    @Override
    public void parseInfo(String content) throws JSONException {
        if (TextUtil.isEmpty(content)) return;
        list = GsonUtils.jsonToList(content, MyNotices.class);
    }


    public static class MyNotices {
        /**
         {
         "error": 0,
         "info": [
         {
         "id": "16",
         "title": "测试222",
         "name": "板块2",
         "create_time": "21分钟前"
         },
         {
         "id": "15",
         "title": "你好 这是测试",
         "name": "板块2",
         "create_time": "29分钟前"
         },
         {
         "id": "14",
         "title": "是是是",
         "name": "公共板块",
         "create_time": "24分钟前"
         },
         {
         "id": "13",
         "title": "测试",
         "name": "公共板块",
         "create_time": "4小时前"
         },
         {
         "id": "12",
         "title": "nihao",
         "name": "板块2",
         "create_time": "21分钟前"
         }
         ]
         }
         */
        private String id;
        private String title;
        private String name;
        private String create_time;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }

}
