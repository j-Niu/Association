package com.future.association.community.presenter;

import com.future.association.community.contract.SendTieContract;
import com.future.association.community.utils.TextUtil;

/**
 * Created by HX·罗 on 2017/7/6.
 */

public class SendTiePresenter implements SendTieContract.IPresenter {

    private SendTieContract.IView iView;

    public SendTiePresenter(SendTieContract.IView iView) {
        this.iView = iView;
    }

    @Override
    public void sendTie() {
        String content = iView.getTieContent();
        String title = iView.getTieTitle();
        String type = iView.geTietType();
        if (!TextUtil.isEmpty(content) && !TextUtil.isEmpty(title) && !TextUtil.isEmpty(type)) {
            iView.sendResult(true);
        }
    }
}
