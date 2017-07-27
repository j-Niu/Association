package com.future.association.community.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.future.association.R;
import com.future.association.community.adapter.TieListAdapter;
import com.future.association.community.base.BaseActivity;
import com.future.association.community.contract.TieListContract;
import com.future.association.community.custom.CustomRecyclerView;
import com.future.association.community.model.PlateInfo;
import com.future.association.community.model.TieInfo;
import com.future.association.community.presenter.TieListPresenter;
import com.future.association.community.utils.ActivityUtils;
import com.future.association.databinding.ActivityBannerBinding;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/4.
 */

public class TieListActivity extends BaseActivity<ActivityBannerBinding> implements TieListContract.IView {

    private TieListAdapter adapter;
    private ArrayList<TieInfo> tieInfos;
    private TieListContract.IPresenter presenter;
    private LinearLayoutManager linearLayoutManager;
    private PlateInfo plateInfo;
    private int currentPage = 1;//当前页

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
    protected void onResume() {
        super.onResume();
        currentPage = 1;
        presenter.getData(currentPage);
    }

    @Override
    public void initData() {
        plateInfo = getIntent().getParcelableExtra("plateInfo");
        tieInfos = new ArrayList<>();
        viewBinding.layoutTitleRightTv.setTitle(plateInfo.getName());
        viewBinding.layoutTitleRightTv.setRightFun("发帖");
        adapter = new TieListAdapter(context, tieInfos);
        viewBinding.rcvTie.setAdapter(adapter);
        presenter = new TieListPresenter(this, context);
    }

    @Override
    public void initListener() {
        viewBinding.rcvTie.setLoadMoreListener(new CustomRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore(int currentPage) {
                TieListActivity.this.currentPage = currentPage;
                presenter.getData(currentPage);
            }
        });
        viewBinding.layoutTitleRightTv.setViewClickListener(this);
        adapter.setItemClickListener(new TieListAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("tieInfo", tieInfos.get(position));
                ActivityUtils.startActivityIntent(context, TieDetailActivity.class, bundle);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_title_right_tv:
                ActivityUtils.startActivityIntent(context, SendTieActivity.class, getIntent().getExtras());
                break;
        }
    }

    @Override
    public void setData(ArrayList<TieInfo> tieInfos) {
        if (tieInfos != null && tieInfos.size() > 0) {
            if (currentPage == 1) {
                this.tieInfos.clear();
                viewBinding.rcvTie.setPage(1);
            }
            this.tieInfos.addAll(tieInfos);
            adapter.notifyDataSetChanged();
        } else {
            viewBinding.rcvTie.setPage(currentPage-1);
        }
        viewBinding.rcvTie.setLoading(false);
    }

    @Override
    public String getPlateId() {
        if (plateInfo != null) {
            return plateInfo.getId();
        } else {
            return "";
        }
    }

    @Override
    public void showMsg(String msg) {
        showShortToast(msg);
    }
}
