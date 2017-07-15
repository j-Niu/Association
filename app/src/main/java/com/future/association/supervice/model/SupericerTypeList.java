package com.future.association.supervice.model;

import com.future.association.common.GsonUtils;
import com.future.association.community.utils.TextUtil;

import org.json.JSONException;

/**
 * Created by rain on 2017/7/14.
 */

public class SupericerTypeList extends BaseBean<SupericerTypeList.SupericerTypeInfo> {
    public static final Creator<SupericerTypeList> CREATOR = new Creator<>(SupericerTypeList.class);

//    private List<InfoBean> list;
    @Override
    public void parseInfo(String content) throws JSONException {
        if (TextUtil.isEmpty(content)) return;
        list = GsonUtils.jsonToList(content, SupericerTypeInfo.class);
    }


    public static class SupericerTypeInfo{
        private String id;
        private String hangye;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getHangye() {
            return hangye;
        }

        public void setHangye(String hangye) {
            this.hangye = hangye;
        }
    }
}
