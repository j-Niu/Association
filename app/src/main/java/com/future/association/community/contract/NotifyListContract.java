package com.future.association.community.contract;

import com.future.association.community.model.MsgNotifyInfo;

import java.util.List;

public interface NotifyListContract {
    interface IPresent{
        void getData();
        void getMoreData();
    }
    interface IView{
        void setData(List<MsgNotifyInfo> notifyInfos);
        void setMoreData(List<MsgNotifyInfo> notifyInfos);
        void showMsg(String msg);
    }
}
