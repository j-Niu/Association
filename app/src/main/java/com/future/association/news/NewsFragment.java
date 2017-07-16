package com.future.association.news;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.future.association.R;
import com.future.baselib.utils.DensityUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements OnBannerListener, BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.toolbar_iv_left)
    ImageView toolbarIvLeft;
    @BindView(R.id.toolbar_tv_title)
    TextView toolbarTvTitle;
    @BindView(R.id.toolbar_iv_right)
    ImageView toolbarIvRight;
    @BindView(R.id.toolbar_tv_right)
    TextView toolbarTvRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private NewsAdapter adapter;
    private ArrayList<String> data;
    private Banner banner;

    public NewsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        unbinder = ButterKnife.bind(this, view);

        initView();
        return view;
    }

    private void initView() {
        toolbarTvTitle.setText("消协");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("测试数据");
        }
        adapter = new NewsAdapter(R.layout.item_news, data);
        initBanner();

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    private void initBanner() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_news_banner, null);
        banner = (Banner) view.findViewById(R.id.banner);

        banner.setOnBannerListener(this);
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setIndicatorGravity(BannerConfig.RIGHT);

        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            titles.add("标题"+(i+1));
        }
        banner.setBannerTitles(titles);

        ArrayList<Integer> imageUrls = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            imageUrls.add(R.drawable.ic_launcher);
        }
        banner.setImages(imageUrls);

        banner.start();
        adapter.addHeaderView(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void OnBannerClick(int position) {
        startActivity(new Intent(getContext(),NewsActivity.class));
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(new Intent(getContext(),NewsActivity.class));
    }
}
