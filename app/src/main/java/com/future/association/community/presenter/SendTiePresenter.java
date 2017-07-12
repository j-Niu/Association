package com.future.association.community.presenter;

import android.content.Context;

import com.future.association.community.contract.SendTieContract;
import com.future.association.community.request.CommunityRequest;
import com.future.association.community.request.DataResponse;
import com.future.association.community.utils.TextUtil;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.view.LoadingDialog;

/**
 * Created by HX·罗 on 2017/7/6.
 */

public class SendTiePresenter implements SendTieContract.IPresenter {

    private Context context;
    private SendTieContract.IView iView;
    private LoadingDialog dialog;

    public SendTiePresenter(SendTieContract.IView iView, Context context) {
        this.iView = iView;
        this.context = context;
        dialog = new LoadingDialog(context);
    }

    @Override
    public void sendTie() {
        String content = iView.getTieContent();
        String title = iView.getTieTitle();
        String type = iView.geTietType();
        if (!TextUtil.isEmpty(content) && !TextUtil.isEmpty(title) && !TextUtil.isEmpty(type)) {
            dialog.show();
            CommunityRequest.sendTie(context, type, title,
                    content, new HttpRequest.OnNetworkListener<DataResponse>() {
                @Override
                public void onSuccess(DataResponse response) {
                    dialog.close();
                    iView.sendResult(true);
                }

                @Override
                public void onFail(String message) {
                    dialog.close();
                    iView.showMsg(message);
                }
            });
        }
    }
}
