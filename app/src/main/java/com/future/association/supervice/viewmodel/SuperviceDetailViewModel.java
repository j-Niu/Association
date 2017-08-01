package com.future.association.supervice.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.future.association.R;
import com.future.association.databinding.ActivitySuperviceDetailBinding;
import com.future.association.supervice.SupericeApi;
import com.future.association.supervice.adapter.SupericeImgAdapter;
import com.future.association.supervice.model.SupericeDetail;
import com.future.association.supervice.view.SuperviceDetailActivity;
import com.future.baselib.utils.HttpRequest;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rain on 2017/7/15.
 */

public class SuperviceDetailViewModel {

    private final SuperviceDetailActivity activity;
    private final ActivitySuperviceDetailBinding mBinding;
    public ObservableField<SupericeImgAdapter> adapterObservable = new ObservableField<>();
    public ObservableArrayList<String> items = new ObservableArrayList<>();

    public SuperviceDetailViewModel(SuperviceDetailActivity activity, ActivitySuperviceDetailBinding binding, String id) {
        this.activity = activity;
        this.mBinding = binding;

        initView();
        initData(id);
    }

    private void initView() {
        mBinding.imgRv.setLayoutManager(new LinearLayoutManager(activity){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        SupericeImgAdapter adapter = new SupericeImgAdapter(R.layout.item_superice_img,null);
        adapterObservable.set(adapter);
//        mBinding.imgRv.setAdapter(adapter);
    }

    private void initData(String id) {
        activity.showLoadingDialog();
        SupericeApi.getInstance().getSupericeTypeDetail(activity,id)
                .setListener(new HttpRequest.OnNetworkListener<SupericeDetail>() {
                    @Override
                    public void onSuccess(SupericeDetail response) {
//                        supericeDetail.set(response.getInfoBean());
                        activity.dissmissLoadingDialog();
                        List<String> collection = Arrays.asList(response.getInfoBean().getImage());
                        if (collection.size()==0){
                            mBinding.ivLayout.setVisibility(View.GONE);
                        }
                        items.addAll(collection);
                        mBinding.setSupericeDetail(response.getInfoBean());
                    }

                    @Override
                    public void onFail(String message) {
                        activity.dissmissLoadingDialog();
                    }
                }).start(new SupericeDetail());
    }

}
