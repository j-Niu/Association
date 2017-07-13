package com.future.association.community.contract;

import com.future.association.community.model.BannerInfo;
import com.future.association.community.model.TieInfo;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/5.
 */

public interface TieListContract {
    interface IView {
        void setData(ArrayList<TieInfo> tieInfos) ;//设置数据
        String getPlateId() ;//获取板块id
        void showMsg(String msg) ;//显示toast提示信息
    }
    interface IPresenter{
        void getData(int currentPage) ;//获取网络数据
    }
}
