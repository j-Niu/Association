package com.future.association.community.contract;

import com.future.association.community.model.BannerInfo;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/5.
 */

public interface BannerContract {
    interface IView {
        void setData(ArrayList<BannerInfo> bannerInfos) ;//设置数据
    }
    interface IPresenter{
        void getData(int currentPage) ;//获取网络数据
    }
}
