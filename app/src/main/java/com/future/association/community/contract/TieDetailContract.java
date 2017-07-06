package com.future.association.community.contract;

import com.future.association.community.model.BannerInfo;
import com.future.association.community.model.TieReplyInfo;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/5.
 */

public interface TieDetailContract {
    interface IView {
        void setData(ArrayList<TieReplyInfo> replyInfos) ;//设置数据
        String getReplyContent() ;//获取回复内容
        void replyResult(boolean isSuccess,TieReplyInfo replyInfo) ;
    }
    interface IPresenter{
        void getData(int currentPage) ;//获取网络数据
        void sendReply() ; //回复帖子
    }
}
