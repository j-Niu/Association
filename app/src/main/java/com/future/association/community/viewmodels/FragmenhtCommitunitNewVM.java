package com.future.association.community.viewmodels;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.future.association.community.model.PlateInfo;
import com.future.association.community.model.UserPlateInfo;
import com.future.association.community.request.CommunityRequest;
import com.future.association.community.request.DataResponse;
import com.future.association.community.view.CommitunitListFragment;
import com.future.association.databinding.FragmenhtCommitunitNewBinding;
import com.future.baselib.adapter.ViewPageFragmentAdapter;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.ToastUtils;

import java.util.List;

public class FragmenhtCommitunitNewVM {
    private final Fragment mFragment;
    private final FragmenhtCommitunitNewBinding mBinding;
    private int currentPage = 1;

    public FragmenhtCommitunitNewVM(FragmenhtCommitunitNewBinding binding, Fragment fragment) {
        this.mBinding = binding;
        this.mFragment = fragment;
        initData();
    }

    private void initData() {
        CommunityRequest.getNotifyList(mFragment.getActivity(),currentPage, new HttpRequest.OnNetworkListener<DataResponse>() {
            @Override
            public void onSuccess(DataResponse response) {
//                    iView.setData(response.infos);
            }

            @Override
            public void onFail(String message) {
                ToastUtils.shortToast(mFragment.getActivity(),message);
            }
        });
        CommunityRequest.getPlateList(mFragment.getActivity(), new HttpRequest.OnNetworkListener<DataResponse>() {
            @Override
            public void onSuccess(DataResponse response) {
//                iView.setPlateList((UserPlateInfo) response.info);
//                plateInfo.getPlateInfos()
                setAdapter(((UserPlateInfo) response.info));
            }

            @Override
            public void onFail(String message) {
                ToastUtils.shortToast(mFragment.getActivity(),message);
            }
        });
    }

    private void setAdapter(UserPlateInfo userPlateInfo) {

        ViewPageFragmentAdapter fragmentAdapter = new ViewPageFragmentAdapter(mFragment.getActivity().getSupportFragmentManager(),mBinding.vp);
        List<PlateInfo> plateInfos = userPlateInfo.getPlateInfos();
        for (PlateInfo plateInfo : plateInfos) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("data",plateInfo);
            bundle.putParcelable("userPlateInfo",userPlateInfo);
            CommitunitListFragment fragment = new CommitunitListFragment();
            fragment.setArguments(bundle);
            fragmentAdapter.addTab(plateInfo.getName(),plateInfo.getId(), fragment,null);
        }
        mBinding.vp.setAdapter(fragmentAdapter);
        mBinding.tl.setupWithViewPager(mBinding.vp);
        mBinding.vp.setOffscreenPageLimit(3);
    }
}
