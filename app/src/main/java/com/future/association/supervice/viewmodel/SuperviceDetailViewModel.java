package com.future.association.supervice.viewmodel;

import com.future.association.databinding.ActivitySuperviceDetailBinding;
import com.future.association.supervice.SupericeApi;
import com.future.association.supervice.model.SupericeDetail;
import com.future.association.supervice.view.SuperviceDetailActivity;
import com.future.baselib.utils.HttpRequest;

/**
 * Created by rain on 2017/7/15.
 */

public class SuperviceDetailViewModel {

    private final SuperviceDetailActivity activity;
    private final ActivitySuperviceDetailBinding mBinding;
//    public ObservableField<SupericeDetail> supericeDetail = new ObservableField<>();

    public SuperviceDetailViewModel(SuperviceDetailActivity activity, ActivitySuperviceDetailBinding binding, String id) {
        this.activity = activity;
        this.mBinding = binding;

        initView();
        initData(id);
    }

    private void initView() {
    }

    private void initData(String id) {
        activity.showLoadingDialog();
        SupericeApi.getInstance().getSupericeTypeDetail(activity,id)
                .setListener(new HttpRequest.OnNetworkListener<SupericeDetail>() {
                    @Override
                    public void onSuccess(SupericeDetail response) {
//                        supericeDetail.set(response.getInfoBean());
                        activity.dissmissLoadingDialog();
                        mBinding.setSupericeDetail(response.getInfoBean());
                    }

                    @Override
                    public void onFail(String message) {
                        activity.dissmissLoadingDialog();
                    }
                }).start(new SupericeDetail());
    }
}
