package com.future.association.community.presenter;

import android.content.Context;

import com.future.association.community.contract.NotifyListContract;
import com.future.association.community.request.CommunityRequest;
import com.future.association.community.request.DataResponse;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.view.LoadingDialog;

public class NotifyListPresent implements NotifyListContract.IPresent {
    private final Context mContext;
    private final NotifyListContract.IView iView;
    private final LoadingDialog dialog;
    private int cuurentPage = 1;

    public NotifyListPresent(Context context, NotifyListContract.IView iView) {
        this.mContext = context;
        this.iView = iView;
        dialog = new LoadingDialog(context);
    }

    @Override
    public void getData() {
        dialog.show();
        cuurentPage = 1;
        CommunityRequest.getNotifyList(mContext,1, new HttpRequest.OnNetworkListener<DataResponse>() {
            @Override
            public void onSuccess(DataResponse response) {
                dialog.close();
                iView.setData(response.infos);
            }

            @Override
            public void onFail(String message) {
                iView.showMsg(message);
                dialog.close();
            }
        });
    }

    @Override
    public void getMoreData() {
        dialog.show();
        cuurentPage++;
        CommunityRequest.getNotifyList(mContext,cuurentPage, new HttpRequest.OnNetworkListener<DataResponse>() {
            @Override
            public void onSuccess(DataResponse response) {
                dialog.close();
                iView.setMoreData(response.infos);
            }

            @Override
            public void onFail(String message) {
                iView.showMsg(message);
                dialog.close();
            }
        });
    }
}
