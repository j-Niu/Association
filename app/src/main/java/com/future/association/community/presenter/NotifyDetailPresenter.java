package com.future.association.community.presenter;

import com.future.association.community.contract.BannerContract;
import com.future.association.community.contract.NotifyDetailContract;
import com.future.association.community.model.BannerInfo;
import com.future.association.community.model.MsgDetailInfo;
import com.future.association.community.model.ReplyInfo;
import com.future.association.community.utils.TextUtil;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/5.
 */

public class NotifyDetailPresenter implements NotifyDetailContract.IPresenter {

    private NotifyDetailContract.IView iView;
    private int pageSize = 20;//每页20条数据

    public NotifyDetailPresenter(NotifyDetailContract.IView iView) {
        this.iView = iView;
    }

    @Override
    public void getData(int currentPage) {
        ArrayList<ReplyInfo> replyInfos = new ArrayList<>();
        replyInfos.add(new ReplyInfo("张三", "v1", "我说的是什么是什么是什么"));
        replyInfos.add(new ReplyInfo("张三", "v1", "我说的是什么是什么是什么"));
        replyInfos.add(new ReplyInfo("张三", "v1", "我说的是什么是什么是什么"));
        replyInfos.add(new ReplyInfo("张三", "v1", "我说的是什么是什么是什么"));
        replyInfos.add(new ReplyInfo("张三", "v1", "我说的是什么是什么是什么"));
        iView.setData(replyInfos);
    }

    @Override
    public void getNotifyDetail() {
        MsgDetailInfo detailInfo = new MsgDetailInfo("消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知",
                "2017-07-04 09:53", "发布单位",
                "消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知");
        iView.setNotifyDetail(detailInfo);
    }

    @Override
    public void sendTalk() {
        String talkContent = iView.getTalkContent();
        if (!TextUtil.isEmpty(talkContent)) {
            iView.talkReult(true, new ReplyInfo("李四", "v2", talkContent));
        }
    }

}
