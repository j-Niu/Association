package com.future.association.supervice.viewmodel;

import android.app.Activity;

import com.future.association.databinding.ActivitySuperviceDetailBinding;
import com.future.association.supervice.SupericeApi;
import com.future.association.supervice.model.SupericeDetail;
import com.future.baselib.utils.HttpRequest;

/**
 * Created by rain on 2017/7/15.
 */

public class SuperviceDetailViewModel {

    private final Activity activity;
    private final ActivitySuperviceDetailBinding mBinding;
//    public ObservableField<SupericeDetail> supericeDetail = new ObservableField<>();

    public SuperviceDetailViewModel(Activity activity, ActivitySuperviceDetailBinding binding,String id) {
        this.activity = activity;
        this.mBinding = binding;

        initView();
        initData(id);
    }

    private void initView() {

    }

    private void initData(String id) {
        SupericeApi.getInstance().getSupericeTypeDetail(activity,id)
                .setListener(new HttpRequest.OnNetworkListener<SupericeDetail>() {
                    @Override
                    public void onSuccess(SupericeDetail response) {
//                        supericeDetail.set(response.getInfoBean());
                        mBinding.setSupericeDetail((SupericeDetail) response.getInfoBean());
                    }

                    @Override
                    public void onFail(String message) {

                    }
                }).start(new SupericeDetail());
    }
}
