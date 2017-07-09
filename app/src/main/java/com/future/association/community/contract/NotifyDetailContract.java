package com.future.association.community.contract;

import com.future.association.community.model.BannerInfo;
import com.future.association.community.model.MsgDetailInfo;
import com.future.association.community.model.ReplyInfo;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/5.
 */

public interface NotifyDetailContract {
    interface IView {
        void setData(ArrayList<ReplyInfo> replyInfos) ;//设置数据
        void setNotifyDetail(MsgDetailInfo detailInfo) ;//设置通知详情
        String getTalkContent() ;//获取评论内容
        void talkReult(boolean isSuccess,ReplyInfo replyInfo) ;
    }
    interface IPresenter{
        void getData(int currentPage) ;//获取所有回复
        void getNotifyDetail() ;//获取通知详情
        void sendTalk() ;//发表评论
    }
}
