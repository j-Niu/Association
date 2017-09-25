package com.future.association.news.ui.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.future.association.R;
import com.future.association.common.Contants;
import com.future.association.news.entity.NewsBannerResponse;
import com.future.association.news.entity.NewsResponse;
import com.future.association.news.utils.GlideImageLoader;
import com.future.association.news.adapter.NewsAdapter;
import com.future.association.news.ui.activity.NewsActivity;
import com.future.baselib.entity.BaseResponse;
import com.future.baselib.utils.DensityUtil;
import com.future.baselib.utils.HttpRequest;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements OnBannerListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {


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
    private List<NewsResponse.NewsDetail> data;
    private Banner banner;
    private ArrayList<String> titles;
    private ArrayList<String> imageUrls;
    private List<NewsBannerResponse.NewsBanner> bannerData;

    private int currentPage = 1;
    public static final int PAGE_SIZE = 10;

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
        toolbarTvTitle.setText("资讯");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        data = new ArrayList<>();
        adapter = new NewsAdapter(R.layout.item_news, data);
        adapter.setOnLoadMoreListener(this,recyclerView);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        initData();
        initBanner();
    }

    @SuppressWarnings("unchecked")
    public void initData(){
        new HttpRequest<NewsResponse>()
                .with(getContext())
                .addParam("apiCode",Contants.API_CODE_NEWS_LIST)
                .addParam("page",""+currentPage ++)
                .addParam("size",""+PAGE_SIZE)
                .setListener(new HttpRequest.OnNetworkListener<NewsResponse>() {
                    @Override
                    public void onSuccess(NewsResponse response) {
                        data.clear();
                        data.addAll(response.data);

//                        if (response.data.size()< PAGE_SIZE) {
//                            adapter.loadMoreEnd();
//                        }
                    }

                    @Override
                    public void onFail(String message) {
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    }
                }).start(new NewsResponse());
    }


    @SuppressWarnings("unchecked")
    private void initBanner() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_news_banner, null);
        banner = (Banner) view.findViewById(R.id.banner);

        banner.setOnBannerListener(this);
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setDelayTime(3500);

        titles = new ArrayList<>();
        imageUrls = new ArrayList<>();

        new HttpRequest<NewsBannerResponse>()
                .with(getContext())
                .addParam("apiCode", Contants.API_CODE_NEWS_BANNER)
                .setListener(new HttpRequest.OnNetworkListener<NewsBannerResponse>() {
                    @Override
                    public void onSuccess(NewsBannerResponse response) {
                        if (response.data == null || response.data.size() == 0) {
                            return;
                        }
                        bannerData = response.data;
                        for (int i = 0; i < response.data.size(); i++) {
                            titles.add(response.data.get(i).title);
                            imageUrls.add(response.data.get(i).banner);
                        }
                        banner.setBannerTitles(titles);
                        banner.setImages(imageUrls);
                        banner.start();
                    }

                    @Override
                    public void onFail(String message) {
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    }
                }).start(new NewsBannerResponse());

        adapter.addHeaderView(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void OnBannerClick(int position) {
        if (bannerData != null) {
            Intent intent = new Intent(getContext(), NewsActivity.class);
            intent.putExtra("id",bannerData.get(position).id);
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(getContext(), NewsActivity.class);
        intent.putExtra("id",data.get(position).id);
        startActivity(intent);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onLoadMoreRequested() {
//        if (adapter.getData().size()<PAGE_SIZE) {//如果本身数据少于一页数据，没有更多数据了
//            adapter.notifyDataSetChanged();
//            return;
//        }

        new HttpRequest<NewsResponse>()
                .with(getContext())
                .addParam("apiCode",Contants.API_CODE_NEWS_LIST)
                .addParam("page",""+currentPage ++)
                .addParam("size",""+PAGE_SIZE)
                .setListener(new HttpRequest.OnNetworkListener<NewsResponse>() {
                    @Override
                    public void onSuccess(NewsResponse response) {
                        if (response.data == null || response.data.size() == 0) {
                            adapter.loadMoreEnd(true);
                            return;
                        }
//                        if (response.data.size()<PAGE_SIZE) {
//                            adapter.loadMoreEnd(true);
//                        }
                        adapter.addData(response.data);
                        adapter.loadMoreComplete();
                    }

                    @Override
                    public void onFail(String message) {
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                        adapter.loadMoreFail();
                    }
                }).start(new NewsResponse());
    }
}
