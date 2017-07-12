package com.future.association.community.presenter;

import android.content.Context;

import com.future.association.community.contract.WeiguiContract;
import com.future.association.community.request.CommunityRequest;
import com.future.association.community.request.DataResponse;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.view.LoadingDialog;

/**
 * Created by HX·罗 on 2017/7/5.
 */

public class WeiguiPresenter implements WeiguiContract.IPresenter {

    private Context context;
    private WeiguiContract.IView iView;
    private final LoadingDialog dialog;

    public WeiguiPresenter(WeiguiContract.IView iView, Context context) {
        this.iView = iView;
        this.context = context;
        dialog = new LoadingDialog(context);
    }


    @Override
    public void doOperation() {
        dialog.show();
        CommunityRequest.dealTie(context, iView.getId(), iView.getTieId(), new HttpRequest.OnNetworkListener<DataResponse>() {
            @Override
            public void onSuccess(DataResponse response) {
                iView.dealResult(true);
            }

            @Override
            public void onFail(String message) {
                iView.dealResult(false);
            }
        });
    }

    @Override
    public void requestWGCause() {
        dialog.show();
        CommunityRequest.getWeiGuiCause(context, new HttpRequest.OnNetworkListener<DataResponse>() {
            @Override
            public void onSuccess(DataResponse response) {
                dialog.close();
                iView.setWGCauses(response.infos);
            }

            @Override
            public void onFail(String message) {
                dialog.close();
                iView.setWGCauses(null);
            }
        });
    }

    @Override
    public void requestDealType() {
        CommunityRequest.getDealType(context, new HttpRequest.OnNetworkListener<DataResponse>() {
            @Override
            public void onSuccess(DataResponse response) {
                iView.setDealTypes(response.infos);
            }

            @Override
            public void onFail(String message) {
                iView.setDealTypes(null);
            }
        });
    }
}
