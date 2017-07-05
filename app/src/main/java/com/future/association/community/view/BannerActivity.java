package com.future.association.community.view;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.future.association.R;
import com.future.association.community.adapter.BannersAdapter;
import com.future.association.community.base.BaseActivity;
import com.future.association.community.model.BannerInfo;
import com.future.association.community.utils.ActivityUtils;
import com.future.association.databinding.ActivityBannerBinding;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/4.
 */

public class BannerActivity extends BaseActivity<ActivityBannerBinding> {

    private BannersAdapter adapter;

    @Override
    public int setContentView() {
        return R.layout.activity_banner;
    }

    @Override
    public void initView() {
        viewBinding.rcvTie.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void initData() {
        ArrayList<BannerInfo> bannerInfos = new ArrayList<>() ;
        viewBinding.layoutTitleRightTv.setTitle("板块名");
        viewBinding.layoutTitleRightTv.setRightFun("发帖");
        adapter = new BannersAdapter(context,bannerInfos);
        viewBinding.rcvTie.setAdapter(adapter);
        bannerInfos.add(new BannerInfo("帖子标题帖子标题","2017-07-04 11:29",5)) ;
        bannerInfos.add(new BannerInfo("帖子标题帖子标题","2017-07-04 11:29",5)) ;
        bannerInfos.add(new BannerInfo("帖子标题帖子标题","2017-07-04 11:29",5)) ;
        bannerInfos.add(new BannerInfo("帖子标题帖子标题","2017-07-04 11:29",5)) ;
        bannerInfos.add(new BannerInfo("帖子标题帖子标题","2017-07-04 11:29",5)) ;
        bannerInfos.add(new BannerInfo("帖子标题帖子标题","2017-07-04 11:29",5)) ;
        bannerInfos.add(new BannerInfo("帖子标题帖子标题","2017-07-04 11:29",5)) ;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void initListener() {
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
}
