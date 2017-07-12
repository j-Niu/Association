package com.future.association.community.presenter;

import android.content.Context;

import com.future.association.community.contract.TieDetailContract;
import com.future.association.community.model.TieDetailInfo;
import com.future.association.community.model.TieReplyInfo;
import com.future.association.community.request.CommunityRequest;
import com.future.association.community.request.DataResponse;
import com.future.association.community.utils.TextUtil;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.view.LoadingDialog;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/5.
 */

public class TieDetailPresenter implements TieDetailContract.IPresenter {

    private Context context;
    private TieDetailContract.IView iView;
    private int pageSize = 20;//每页20条数据
    private LoadingDialog dialog;

    public TieDetailPresenter(TieDetailContract.IView iView, Context context) {
        this.iView = iView;
        this.context = context;
        dialog = new LoadingDialog(context);
    }

    @Override
    public void getData(int currentPage) {

//        dialog.show();
        CommunityRequest.getTieReply(context, iView.getTieId(), new HttpRequest.OnNetworkListener<DataResponse>() {
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
    public void getTieDetail() {
        dialog.show();
        CommunityRequest.getTieDetail(context, iView.getTieId(), new HttpRequest.OnNetworkListener<DataResponse>() {
            @Override
            public void onSuccess(DataResponse response) {
                iView.setTieDetail((TieDetailInfo) response.info);
                dialog.close();
            }

            @Override
            public void onFail(String message) {
                iView.showMsg(message);
                dialog.close();
            }
        });
    }

    @Override
    public void sendReply() {
        String replyContent = iView.getReplyContent();
        if (!TextUtil.isEmpty(replyContent)) {
            dialog.show();
            CommunityRequest.postReplyTie(context, iView.getTieId(), replyContent,
                    new HttpRequest.OnNetworkListener<DataResponse>() {
                @Override
                public void onSuccess(DataResponse response) {
                    iView.replyResult(new TieReplyInfo());
                    dialog.close();
                }

                @Override
                public void onFail(String message) {
                    iView.showMsg(message);
                    dialog.close();
                }
            });
        }
    }

    @Override
    public void delTie() {
        dialog.show();
        CommunityRequest.delTie(context, iView.getTieId(), new HttpRequest.OnNetworkListener<DataResponse>() {
            @Override
            public void onSuccess(DataResponse response) {
                dialog.close();
                iView.delTieResult(true);
            }

            @Override
            public void onFail(String message) {
                dialog.close();
                iView.showMsg(message);
            }
        });
    }

    @Override
    public void delTieReply() {
        dialog.show();
        CommunityRequest.delTieReply(context, iView.getTieReplyId(), new HttpRequest.OnNetworkListener<DataResponse>() {
            @Override
            public void onSuccess(DataResponse response) {
                iView.delTieReplyResult(true);
                dialog.close();
            }

            @Override
            public void onFail(String message) {
                iView.showMsg(message);
                dialog.close();
            }
        });
    }

    @Override
    public void tieTop() {
        dialog.show();
        CommunityRequest.topTie(context, iView.getTieId(),iView.getTieType(), new HttpRequest.OnNetworkListener<DataResponse>() {
            @Override
            public void onSuccess(DataResponse response) {
                iView.topTieResult(true);
                dialog.close();
            }

            @Override
            public void onFail(String message) {
                iView.showMsg(message);
                dialog.close();
            }
        });
    }

}
