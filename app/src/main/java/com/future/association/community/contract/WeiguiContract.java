package com.future.association.community.contract;

import com.future.association.community.model.BannerInfo;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/5.
 */

public interface WeiguiContract {
    interface IView {
        String getWGCause() ;//获取违规原因
        String getDealType() ;//获取处理方式
        void dealResult(boolean isSuccess) ;//处理结果
    }
    interface IPresenter{
        void doOperation() ;//违规操作
    }
}
