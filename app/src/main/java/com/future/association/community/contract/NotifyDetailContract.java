package com.future.association.community.contract;

import com.future.association.community.model.MsgDetailInfo;
import com.future.association.community.model.NotifyReplyInfo;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/5.
 */

public interface NotifyDetailContract {
    interface IView {
        void setData(ArrayList<NotifyReplyInfo> replyInfos) ;//设置数据
        void setNotifyDetail(MsgDetailInfo detailInfo) ;//设置通知详情
        String getTalkContent() ;//获取评论内容
        String getNofityId() ;//获取通知信息id
        void talkReult(NotifyReplyInfo replyInfo) ;
        void showMsg(String msg);
    }
    interface IPresenter{
        void getData(int currentPage) ;//获取所有回复
        void getNotifyDetail() ;//获取通知详情
        void sendTalk() ;//发表评论
    }
}
