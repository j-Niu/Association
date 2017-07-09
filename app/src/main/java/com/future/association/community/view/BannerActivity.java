package com.future.association.community.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.future.association.R;
import com.future.association.community.adapter.BannersAdapter;
import com.future.association.community.base.BaseActivity;
import com.future.association.community.base.EndlessRecyclerOnScrollListener;
import com.future.association.community.contract.BannerContract;
import com.future.association.community.model.BannerInfo;
import com.future.association.community.presenter.BannerPresenter;
import com.future.association.community.utils.ActivityUtils;
import com.future.association.community.utils.ConstantUtil;
import com.future.association.databinding.ActivityBannerBinding;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/4.
 */

public class BannerActivity extends BaseActivity<ActivityBannerBinding> implements BannerContract.IView{

    private BannersAdapter adapter;
    private ArrayList<BannerInfo> bannerInfos;
    private BannerContract.IPresenter presenter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public int setContentView() {
        return R.layout.activity_banner;
    }

    @Override
    public void initView() {
        linearLayoutManager = new LinearLayoutManager(context);
        viewBinding.rcvTie.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void initData() {
        bannerInfos = new ArrayList<>();
        viewBinding.layoutTitleRightTv.setTitle("板块名");
        viewBinding.layoutTitleRightTv.setRightFun("发帖");
        adapter = new BannersAdapter(context, bannerInfos);
        viewBinding.rcvTie.setAdapter(adapter);
        presenter = new BannerPresenter(this);
        presenter.getData(1);
    }

    @Override
    public void initListener() {
        viewBinding.rcvTie.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                presenter.getData(currentPage);
            }
        });
        viewBinding.layoutTitleRightTv.setViewClickListener(this);
        adapter.setItemClickListener(new BannersAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position) {
                ActivityUtils.startActivityIntent(context,TieDetailActivity.class);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_title_right_tv:
                ActivityUtils.startActivityIntent(context,SendTieActivity.class);
                break;
        }
    }

    @Override
    public void setData(ArrayList<BannerInfo> bannerInfos) {
        this.bannerInfos.addAll(bannerInfos);
        adapter.notifyDataSetChanged();
    }
}
