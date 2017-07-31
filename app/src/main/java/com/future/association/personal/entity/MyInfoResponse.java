package com.future.association.personal.entity;

import com.future.association.common.GsonUtils;
import com.future.association.community.utils.TextUtil;

import org.json.JSONException;

/**
 * Created by javakam on 2017/7/23 0023.
 */
public class MyInfoResponse extends  BaseBean<MyInfoResponse.MyInfos>{
    public static final BaseBean.Creator<MyInfoResponse> CREATOR = new BaseBean.Creator<>(MyInfoResponse.class);

    @Override
    public void parseInfo(String content) throws JSONException {
        if (TextUtil.isEmpty(content)) return;
        list = GsonUtils.jsonToList(content, MyInfos.class);
    }

   
    public static class MyInfos {
        /**
         * id : 136
         * avatar_url :
         * real_name :
         * quanxian : 1
         * address : 山西晋城陵川县
         * jifen : 33
         * level : V2
         * chenghao : 等级2
         * level_img :
         * jifencha : 27
         */
        private String id;
        private String avatar_url;
        private String real_name;
        private String quanxian;
        private String address;
        private String jifen;
        private String level;
        private String chenghao;
        private String level_img;
        private String jifencha;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getQuanxian() {
            return quanxian;
        }

        public void setQuanxian(String quanxian) {
            this.quanxian = quanxian;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getJifen() {
            return jifen;
        }

        public void setJifen(String jifen) {
            this.jifen = jifen;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getChenghao() {
            return chenghao;
        }

        public void setChenghao(String chenghao) {
            this.chenghao = chenghao;
        }

        public String getLevel_img() {
            return level_img;
        }

        public void setLevel_img(String level_img) {
            this.level_img = level_img;
        }

        public String getJifencha() {
            return jifencha;
        }

        public void setJifencha(String jifencha) {
            this.jifencha = jifencha;
        }
    }
     
    
}