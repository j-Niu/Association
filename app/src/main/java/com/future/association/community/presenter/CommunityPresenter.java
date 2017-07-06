package com.future.association.community.presenter;

import com.future.association.community.contract.CommunityContract;
import com.future.association.community.model.MsgNotifyInfo;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/6.
 */

public class CommunityPresenter implements CommunityContract.IPresenter{

    private CommunityContract.IView iView ;
    private int pageSize = 20 ;//每页显示20条数据

    public CommunityPresenter(CommunityContract.IView iView) {
        this.iView = iView;
    }

    @Override
    public void getData(int currentPage) {
        ArrayList<MsgNotifyInfo> notifyInfos = new ArrayList<>() ;
        notifyInfos.add(new MsgNotifyInfo("1","消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知","发布来源",7)) ;
        notifyInfos.add(new MsgNotifyInfo("1","消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知","发布来源",7)) ;
        notifyInfos.add(new MsgNotifyInfo("1","消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知","发布来源",7)) ;
        notifyInfos.add(new MsgNotifyInfo("1","消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知","发布来源",7)) ;
        notifyInfos.add(new MsgNotifyInfo("1","消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知","发布来源",7)) ;
        notifyInfos.add(new MsgNotifyInfo("1","消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知","发布来源",7)) ;
        notifyInfos.add(new MsgNotifyInfo("1","消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知","发布来源",7)) ;
        notifyInfos.add(new MsgNotifyInfo("1","消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知","发布来源",7)) ;
        iView.setData(notifyInfos);
    }
}
