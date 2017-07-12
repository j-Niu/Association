package com.future.association.community.presenter;

import android.content.Context;

import com.future.association.community.contract.TieListContract;
import com.future.association.community.model.BannerInfo;
import com.future.association.community.request.CommunityRequest;
import com.future.association.community.request.DataResponse;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.view.LoadingDialog;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/5.
 */

public class TieListPresenter implements TieListContract.IPresenter {

    private Context context;
    private TieListContract.IView iView;
    private int pageSize = 20;//每页20条数据
    private LoadingDialog dialog;

    public TieListPresenter(TieListContract.IView iView, Context context) {
        this.iView = iView;
        this.context = context;
    }

    @Override
    public void getData(int currentPage) {
        if (dialog == null) {
            dialog = new LoadingDialog(context);
        }
        dialog.show();

        CommunityRequest.getTieList(context, iView.getPlateId(), new HttpRequest.OnNetworkListener<DataResponse>() {
            @Override
            public void onSuccess(DataResponse response) {
                iView.setData(response.infos);
                dialog.close();
            }

            @Override
            public void onFail(String message) {
                iView.showMsg(message);
            }
        });
    }

}
