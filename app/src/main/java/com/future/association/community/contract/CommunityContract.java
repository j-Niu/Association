package com.future.association.community.contract;

import com.future.association.community.model.MsgNotifyInfo;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/6.
 */

public interface CommunityContract {
    interface IPresenter{
        void getData(int currentPage) ;
    }
    interface IView{
        void setData(ArrayList<MsgNotifyInfo> notifyInfos) ;
    }
}
