package com.future.association.community.contract;

import com.future.association.community.model.MsgNotifyInfo;
import com.future.association.community.model.PlateInfo;
import com.future.association.community.model.UserPlateInfo;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/6.
 */

public interface CommunityContract {
    interface IPresenter{
        void getData(int currentPage) ;
        void getPlateList() ;//获取板块数据
    }
    interface IView{
        void setData(ArrayList<MsgNotifyInfo> notifyInfos) ;
        void setPlateList(UserPlateInfo plateInfo) ;
        void showMsg(String msg);
    }
}
