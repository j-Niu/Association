package com.future.association.personal.entity;

import com.future.association.common.GsonUtils;
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
        public String real_name;//发帖人
        public String avatar_url;//头像
        public String level;//发帖人等级
        public String uid;//发帖人id
        public String id;//帖子id
        public String title;//帖子标题
        public String create_time;//发帖时间
        public String address;//所属区域
        public String content;//帖子内容
        public String type;//帖子类型

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
//        public String getLevel() {
//            if (TextUtil.isEmpty(level)) {
//                return "V0";
//            } else if (!level.toLowerCase().startsWith("v")) {
//                return "V" + level;
//            }else{
//                return level ;
//            }
//        }
//        public String typeFormat() {
//            if ("1".equals(type)) {
//                return "置顶帖";
//            }else{
//                return "普通帖";
//            }
//        }
    }
}
