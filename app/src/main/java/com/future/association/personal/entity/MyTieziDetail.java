package com.future.association.personal.entity;

import com.future.association.common.utils.GsonUtils;
import com.future.association.community.utils.TextUtil;

import org.json.JSONException;

import java.io.Serializable;

/**
 * Created by javakam on 2017/7/24 0024.
 */
public class MyTieziDetail extends BaseBean<MyTieziDetail.MyTieziDetails> {

    public static final Creator<MyTieziDetail> CREATOR = new Creator<>(MyTieziDetail.class);

    @Override
    public void parseInfo(String content) throws JSONException {
        if (TextUtil.isEmpty(content)) return;
        String str = content.replace("{", "[{");
        str = str.replace("}", "}]");
        list = GsonUtils.jsonToList(str, MyTieziDetails.class);
    }


    public static class MyTieziDetails implements Serializable {
        /*
           "real_name": "",//发帖人<br />
        "avatar_url": "",//发帖人头像<br />
        "level": null,//发帖人等级
        "id": "1",//帖子id<br />
        "title": "测试1",//帖子标题
        "create_time": "2017-07-09 22:34:34",//发帖时间
        "address": "湖北省武汉市洪山区",//所属区域
        "content": "阿萨德客户发时达上课了建档立卡就是快乐的",//帖子内容
          "type": "普通"//帖子类型

	        "uid": "136"//发帖子id
	        "weigui": "1"//1为正常，2为违规
	        "reason": ""//违规原因
	        "fangshi": ""//违规处理方式，1为删除
         */
        public String real_name;//发帖人
        public String avatar_url;//头像
        private String level;//发帖人等级
        public String id;//帖子id
        public String title;//帖子标题
        public String create_time;//发帖时间
        public String address;//所属区域
        public String content;//帖子内容
        private String type;//帖子类型

        public String uid;//发帖人id
        public String weigui;//1为正常，2为违规
        public String reason;//违规原因
        public String fangshi;//违规处理方式，1为删除

        @Override
        public String toString() {
            return "MyTieziDetails{" +
                    "real_name='" + real_name + '\'' +
                    ", avatar_url='" + avatar_url + '\'' +
                    ", level='" + level + '\'' +
                    ", uid='" + uid + '\'' +
                    ", id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", create_time='" + create_time + '\'' +
                    ", address='" + address + '\'' +
                    ", content='" + content + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }

        public String getLevel() {
            if (TextUtil.isEmpty(level)) {
                return "V0";
            } else if (!level.toLowerCase().startsWith("v")) {
                return "V" + level;
            } else {
                return level;
            }
        }

        public String typeFormat() {
            if ("1".equals(type)) {
                return "置顶帖";
            } else {
                return "普通帖";
            }
        }
    }
}
