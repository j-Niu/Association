package com.future.association.community.viewmodels;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.future.association.common.view.SimplePaddingDecoration;
import com.future.association.community.adapter.TieListAdapter;
import com.future.association.community.custom.CustomRecyclerView;
import com.future.association.community.model.PlateInfo;
import com.future.association.community.model.TieInfo;
import com.future.association.community.model.UserPlateInfo;
import com.future.association.community.request.CommunityRequest;
import com.future.association.community.request.DataResponse;
import com.future.association.community.utils.ActivityUtils;
import com.future.association.community.view.TieDetailActivity;
import com.future.association.databinding.FragmentCommitunitListBinding;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.ToastUtils;

import java.util.ArrayList;

public class CommitunitListFragmentVM {
    private final FragmentCommitunitListBinding mBinding;
    private final Fragment mFragment;
//    private int currentPage = 1;
    private PlateInfo mPlateInfo;
    private ArrayList<TieInfo> infos = new ArrayList<>();
    private TieListAdapter tieListAdapter;

    public CommitunitListFragmentVM(FragmentCommitunitListBinding binding, Fragment fragment) {
        this.mBinding = binding;
        this.mFragment = fragment;
        initData();
    }

    private void initData() {
        Bundle bundle = mFragment.getArguments();
        mPlateInfo =  bundle.getParcelable("data");
//        String id = mPlateInfo.getId();
        setAdapter();
        getTieList(1);
    }

    private void getTieList(int currentPage) {
        CommunityRequest.getTieList(mFragment.getActivity(), mPlateInfo.getId(),currentPage, new HttpRequest.OnNetworkListener<DataResponse>() {
            @Override
            public void onSuccess(DataResponse response) {
//                iView.setData(response.infos);
                infos.addAll(response.infos);
                tieListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String message) {
                ToastUtils.shortToast(mFragment.getActivity(),message);
            }
        });
    }

    private void setAdapter() {
         tieListAdapter = new TieListAdapter(mFragment.getActivity(), infos);
        final UserPlateInfo userPlateInfo = mFragment.getArguments().getParcelable("userPlateInfo");
        tieListAdapter.setItemClickListener(new TieListAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("huifu_Jf",mPlateInfo.getHuifu_jf());
                bundle.putString("id",infos.get(position).getId());
                bundle.putString("type",infos.get(position).getType());
                bundle.putString("jifen", userPlateInfo.getJifen());
                ActivityUtils.startActivityIntent(mFragment.getActivity(), TieDetailActivity.class, bundle);
            }
        });
        mBinding.rcvTie.setLoadMoreListener(new CustomRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore(int currentPage) {
                getTieList(currentPage);
            }
        });
        mBinding.rcvTie.addItemDecoration(new SimplePaddingDecoration(mFragment.getActivity()));
        mBinding.rcvTie.setAdapter(tieListAdapter);
    }
}
