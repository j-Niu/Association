package com.future.association.community.contract;

import com.future.association.community.model.BannerInfo;
import com.future.association.community.model.DealTypeInfo;
import com.future.association.community.model.WGCauseInfo;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/5.
 */

public interface WeiguiContract {
    interface IView {
        String getWGCause() ;//获取违规原因
        String getDealType() ;//获取处理方式
        void setWGCauses(ArrayList<WGCauseInfo> wgCauses) ;//初始化所有违规原因
        void setDealTypes(ArrayList<DealTypeInfo> dealTypes) ;//初始化所有处理方式
        void dealResult(boolean isSuccess) ;//处理结果
        String getTieId() ;//获取帖子ID
        String getId() ;//获取帖子回复ID
    }
    interface IPresenter{
        void doOperation() ;//违规操作
        void requestWGCause() ;//请求违规原因
        void requestDealType() ;//请求处理方式
    }
}
