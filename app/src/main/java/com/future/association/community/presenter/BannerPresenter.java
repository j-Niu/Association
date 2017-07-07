package com.future.association.community.presenter;

import com.future.association.community.contract.BannerContract;
import com.future.association.community.model.BannerInfo;
import com.future.association.community.utils.ConstantUtil;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/5.
 */

public class BannerPresenter implements BannerContract.IPresenter {

    private BannerContract.IView iView ;
    private int pageSize = 20 ;//每页20条数据

    public BannerPresenter(BannerContract.IView iView) {
        this.iView = iView;
    }

    @Override
    public void getData(int currentPage) {
        ArrayList<BannerInfo> bannerInfos = new ArrayList<>() ;
        bannerInfos.add(new BannerInfo("帖子标题帖子标题","2017-07-04 11:29",5)) ;
        bannerInfos.add(new BannerInfo("帖子标题帖子标题","2017-07-04 11:29",5)) ;
        bannerInfos.add(new BannerInfo("帖子标题帖子标题","2017-07-04 11:29",5)) ;
        bannerInfos.add(new BannerInfo("帖子标题帖子标题","2017-07-04 11:29",5)) ;
        bannerInfos.add(new BannerInfo("帖子标题帖子标题","2017-07-04 11:29",5)) ;
        bannerInfos.add(new BannerInfo("帖子标题帖子标题","2017-07-04 11:29",5)) ;
        bannerInfos.add(new BannerInfo("帖子标题帖子标题","2017-07-04 11:29",5)) ;
        bannerInfos.add(new BannerInfo("帖子标题帖子标题","2017-07-04 11:29",5)) ;
        bannerInfos.add(new BannerInfo("帖子标题帖子标题","2017-07-04 11:29",5)) ;
        bannerInfos.add(new BannerInfo("帖子标题帖子标题","2017-07-04 11:29",5)) ;
        bannerInfos.add(new BannerInfo("帖子标题帖子标题","2017-07-04 11:29",5)) ;
        iView.setData(bannerInfos);
    }

}
