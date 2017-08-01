package com.future.association.news.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GlideBuilder;
import com.future.association.R;
import com.future.association.common.Contants;
import com.future.association.common.MyApp;
import com.future.association.community.utils.TextUtil;
import com.future.association.news.entity.NewsDetailsResponse;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.entity.BaseResponse;
import com.future.baselib.entity.DefaultResponse;
import com.future.baselib.utils.HttpRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends BaseActivity {

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
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.webView)
    WebView webView;
    private String id;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {

        setTitle("资讯详情");
        toolbarIvLeft.setImageResource(R.drawable.ic_back);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void initLogic() {
        if (TextUtil.isEmpty(id)) {
            toast("无效id");
            return;
        }
        new HttpRequest<NewsDetailsResponse>()
                .with(this)
                .addParam("apiCode", Contants.API_CODE_NEWS_DETAIL)
                .addParam("userToken", MyApp.getUserToken())
                .addParam("id",id)
                .setListener(new HttpRequest.OnNetworkListener<NewsDetailsResponse>() {
                    @Override
                    public void onSuccess(NewsDetailsResponse response) {
                        tvTitle.setText(response.newsDetails.title);
                        tvTime.setText(response.newsDetails.create_time);
                        webView.loadData(response.newsDetails.content,"text/html; charset=utf-8",null);
                    }

                    @Override
                    public void onFail(String message) {
                        toast(message);
                    }
                }).start(new NewsDetailsResponse());
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        id = extras.getString("id");
    }

}
