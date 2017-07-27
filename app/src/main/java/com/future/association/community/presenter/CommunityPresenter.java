package com.future.association.community.presenter;

import android.content.Context;

import com.future.association.community.contract.CommunityContract;
import com.future.association.community.model.UserPlateInfo;
import com.future.association.community.request.CommunityRequest;
import com.future.association.community.request.DataResponse;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.view.LoadingDialog;

/**
 * Created by HX·罗 on 2017/7/6.
 */

public class CommunityPresenter implements CommunityContract.IPresenter{

    private CommunityContract.IView iView ;
    private Context context ;
    private int pageSize = 20 ;//每页显示20条数据
    private LoadingDialog dialog;

    public CommunityPresenter(CommunityContract.IView iView,Context context) {
        this.iView = iView;
        this.context = context;
        dialog = new LoadingDialog(context);
    }

    @Override
    public void getData(int currentPage) {
        dialog.show();
        CommunityRequest.getNotifyList(context,currentPage, new HttpRequest.OnNetworkListener<DataResponse>() {
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
    public void getPlateList() {
        CommunityRequest.getPlateList(context, new HttpRequest.OnNetworkListener<DataResponse>() {
            @Override
            public void onSuccess(DataResponse response) {
                dialog.close();
                iView.setPlateList((UserPlateInfo) response.info);
            }

            @Override
            public void onFail(String message) {
                iView.showMsg(message);
                dialog.close();
            }
        });
    }

}
