package com.future.association.community.contract;

import com.future.association.community.model.BannerInfo;
import com.future.association.community.model.TieDetailInfo;
import com.future.association.community.model.TieReplyInfo;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/5.
 */

public interface TieDetailContract {
    interface IView {
        void setData(ArrayList<TieReplyInfo> replyInfos) ;//设置数据
        String getReplyContent() ;//获取回复内容
        void setTieDetail(TieDetailInfo detailInfo) ;
        void replyResult(boolean isSuccess,TieReplyInfo replyInfo) ;
        String getTieId() ;//获取帖子id
        String getTieReplyId() ;//获取帖子回复id
        void delTieResult(boolean result) ;//删除帖子结果
        void delTieReplyResult(boolean result) ;//删除帖子回复结果
        void topTieResult(boolean result) ;//置顶帖子结果
    }
    interface IPresenter{
        void getData(int currentPage) ;//获取网络数据
        void getTieDetail() ;//获取帖子详情
        void sendReply() ; //回复帖子
        void delTie();//删除帖子
        void delTieReply() ;//删除帖子回复
        void tieTop();//帖子置顶
    }
}
