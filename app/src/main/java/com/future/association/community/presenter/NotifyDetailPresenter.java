package com.future.association.community.presenter;

import android.content.Context;

import com.future.association.community.contract.NotifyDetailContract;
import com.future.association.community.model.MsgDetailInfo;
import com.future.association.community.model.NotifyReplyInfo;
import com.future.association.community.request.CommunityRequest;
import com.future.association.community.request.DataResponse;
import com.future.association.community.utils.TextUtil;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.view.LoadingDialog;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/5.
 */

public class NotifyDetailPresenter implements NotifyDetailContract.IPresenter {

    private Context context;
    private NotifyDetailContract.IView iView;
    private int pageSize = 20;//每页20条数据
    private final LoadingDialog dialog;

    public NotifyDetailPresenter(NotifyDetailContract.IView iView, Context context) {
        this.iView = iView;
        this.context = context;
        dialog = new LoadingDialog(context);
    }

    @Override
    public void getData(int currentPage) {
        CommunityRequest.getNotifyReply(context, iView.getNofityId(), new HttpRequest.OnNetworkListener<DataResponse>() {
            @Override
            public void onSuccess(DataResponse response) {
                iView.setData(response.infos);
            }

            @Override
            public void onFail(String message) {
                iView.showMsg(message);
            }
        });
    }

    @Override
    public void getNotifyDetail() {
        dialog.show();
        CommunityRequest.getNotifyDetail(context, iView.getNofityId(), new HttpRequest.OnNetworkListener<DataResponse>() {
            @Override
            public void onSuccess(DataResponse response) {
                dialog.close();
                iView.setNotifyDetail((MsgDetailInfo) response.info);
            }

            @Override
            public void onFail(String message) {
                dialog.close();
                iView.showMsg(message);
            }
        });
    }

    @Override
    public void sendTalk() {
        String talkContent = iView.getTalkContent();
        if (!TextUtil.isEmpty(talkContent)) {
            dialog.show();
            CommunityRequest.replyNotify(context, iView.getNofityId(), talkContent, new HttpRequest.OnNetworkListener<DataResponse>() {
                @Override
                public void onSuccess(DataResponse response) {
                    iView.talkReult(new NotifyReplyInfo());
                    dialog.close();
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
