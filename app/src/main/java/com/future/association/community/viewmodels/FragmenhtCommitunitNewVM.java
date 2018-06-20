package com.future.association.community.viewmodels;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.future.association.R;
import com.future.association.community.model.MsgNotifyInfo;
import com.future.association.community.model.PlateInfo;
import com.future.association.community.model.UserPlateInfo;
import com.future.association.community.request.CommunityRequest;
import com.future.association.community.request.DataResponse;
import com.future.association.community.utils.ActivityUtils;
import com.future.association.community.utils.StringUtils;
import com.future.association.community.view.CommitunitListFragment;
import com.future.association.community.view.NotifyDetailActivity;
import com.future.association.community.view.NotifyListActivity;
import com.future.association.community.view.SendTieActivity;
import com.future.association.databinding.FragmenhtCommitunitNewBinding;
import com.future.baselib.adapter.ViewPageFragmentAdapter;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.ToastUtils;

import java.util.List;

public class FragmenhtCommitunitNewVM implements ViewPager.OnPageChangeListener {
    private final Fragment mFragment;
    private final FragmenhtCommitunitNewBinding mBinding;
    private int currentPage = 1;
    private UserPlateInfo mUserPlateInfo;
    private PlateInfo mPlateInfo;
    private List<MsgNotifyInfo> notifyInfos;

    public FragmenhtCommitunitNewVM(FragmenhtCommitunitNewBinding binding, Fragment fragment) {
        this.mBinding = binding;
        this.mFragment = fragment;
        initData();
        initListener();
    }

    private void initListener() {
        mBinding.notifyTv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("id", notifyInfos.get(0).getId());
                ActivityUtils.startActivityIntent(mFragment.getActivity(), NotifyDetailActivity.class, bundle);
            }
        });
        mBinding.notifyTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("id", notifyInfos.get(1).getId());
                ActivityUtils.startActivityIntent(mFragment.getActivity(), NotifyDetailActivity.class, bundle);
            }
        });
        mBinding.notifyTv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("id", notifyInfos.get(2).getId());
                ActivityUtils.startActivityIntent(mFragment.getActivity(), NotifyDetailActivity.class, bundle);
            }
        });
        mBinding.notifyIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragment.startActivity(new Intent(mFragment.getActivity(), NotifyListActivity.class));
            }
        });
    }

    private void initData() {
        CommunityRequest.getNotifyList(mFragment.getActivity(), currentPage, new HttpRequest.OnNetworkListener<DataResponse>() {
            @Override
            public void onSuccess(DataResponse response) {
//                    iView.setData(response.infos);
            }

            @Override
            public void onFail(String message) {
                ToastUtils.shortToast(mFragment.getActivity(), message);
            }
        });
        CommunityRequest.getPlateList(mFragment.getActivity(), new HttpRequest.OnNetworkListener<DataResponse>() {
            @Override
            public void onSuccess(DataResponse response) {
//                iView.setPlateList((UserPlateInfo) response.info);
//                plateInfo.getPlateInfos()
                mUserPlateInfo = (UserPlateInfo) response.info;
                setAdapter();
            }

            @Override
            public void onFail(String message) {
                ToastUtils.shortToast(mFragment.getActivity(), message);
            }
        });

        CommunityRequest.getNotifyList(mFragment.getActivity(), 1, new HttpRequest.OnNetworkListener<DataResponse>() {
            @Override
            public void onSuccess(DataResponse response) {
//                iView.setData(response.infos);
                notifyInfos = response.infos;
                Drawable drawable= mFragment.getResources().getDrawable(R.drawable.xiaoxi);
/// 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                myTextview.setCompoundDrawables(drawable,null,null,null);
                if (notifyInfos == null || notifyInfos.size() == 0) {
                    mBinding.notifyLayout.setVisibility(View.GONE);
                } else {
                    mBinding.notifyLayout.setVisibility(View.VISIBLE);
                    int size = notifyInfos.size();
                    if (size >= 1) {
                        mBinding.notifyTv1.setVisibility(View.VISIBLE);
                        mBinding.notifyTv1.setCompoundDrawablePadding(4);
                        mBinding.notifyTv1.setCompoundDrawables(drawable,null,null,null);
                        mBinding.notifyTv1.setText(notifyInfos.get(0).getTitle());
                    } else if (size >= 2) {
                        mBinding.notifyTv2.setVisibility(View.VISIBLE);
                        mBinding.notifyTv2.setCompoundDrawablePadding(4);
                        mBinding.notifyTv2.setCompoundDrawables(drawable,null,null,null);
                        mBinding.notifyTv2.setText(notifyInfos.get(1).getTitle());
                    } else if (size >= 3) {
                        mBinding.notifyTv3.setVisibility(View.VISIBLE);
                        mBinding.notifyTv3.setCompoundDrawablePadding(4);
                        mBinding.notifyTv3.setCompoundDrawables(drawable,null,null,null);
                        mBinding.notifyTv3.setText(notifyInfos.get(2).getTitle());
                    }
                    if (size>3){
                        mBinding.notifyIv.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFail(String message) {
//                iView.showMsg(message);
                mBinding.notifyLayout.setVisibility(View.GONE);
            }
        });

    }

    private void setAdapter() {

        ViewPageFragmentAdapter fragmentAdapter = new ViewPageFragmentAdapter(mFragment.getActivity().getSupportFragmentManager(), mBinding.vp);
        List<PlateInfo> plateInfos = mUserPlateInfo.getPlateInfos();
        for (PlateInfo plateInfo : plateInfos) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("data", plateInfo);
            bundle.putParcelable("userPlateInfo", mUserPlateInfo);
            CommitunitListFragment fragment = new CommitunitListFragment();
            fragment.setArguments(bundle);
            fragmentAdapter.addTab(plateInfo.getName(), plateInfo.getId(), fragment, null);
        }
        mBinding.vp.setAdapter(fragmentAdapter);
        mBinding.tl.setupWithViewPager(mBinding.vp);
        mBinding.vp.setOffscreenPageLimit(3);
        mBinding.vp.addOnPageChangeListener(this);
    }

    /*
     * 发帖
     * */
    public void publishTie() {
        if (null == mUserPlateInfo || null == mPlateInfo) return;
        ;
        if ("2".equals(mPlateInfo.getIspost())) {
            ToastUtils.shortToast(mFragment.getActivity(), "该板块只有版主才能发帖");
        } else if (StringUtils.stringIsInteger(mUserPlateInfo.getJifen()) >
                StringUtils.stringIsInteger(mPlateInfo.getFatie_jf())) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("userPlateInfo", mUserPlateInfo);
            bundle.putParcelable("plateInfo", mPlateInfo);
            ActivityUtils.startActivityIntent(mFragment.getActivity(), SendTieActivity.class, bundle);
        } else {
            ToastUtils.shortToast(mFragment.getActivity(), "积分不够不能发帖");
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (mUserPlateInfo != null) {
            mPlateInfo = mUserPlateInfo.getPlateInfos().get(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
