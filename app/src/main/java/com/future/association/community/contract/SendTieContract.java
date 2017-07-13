package com.future.association.community.contract;

import com.future.association.community.model.BannerInfo;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/5.
 */

public interface SendTieContract {
    interface IView {
        String getTieTitle() ;//获取title
        String getTieContent() ;//获取内容
        String geTietType() ;//获取板块
        void sendResult(boolean isSuccess) ;//发帖结果
        void showMsg(String msg);
    }
    interface IPresenter{
        void sendTie() ;//发布帖子
    }
}
