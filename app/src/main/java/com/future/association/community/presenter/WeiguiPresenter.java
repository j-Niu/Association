package com.future.association.community.presenter;

import com.future.association.community.contract.BannerContract;
import com.future.association.community.contract.WeiguiContract;
import com.future.association.community.model.BannerInfo;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/5.
 */

public class WeiguiPresenter implements WeiguiContract.IPresenter {

    private WeiguiContract.IView iView ;

    public WeiguiPresenter(WeiguiContract.IView iView) {
        this.iView = iView;
    }


    @Override
    public void doOperation() {
        iView.dealResult(true);
    }
}
