package com.future.association.news;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.future.association.R;
import com.future.baselib.activity.BaseActivity;

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

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        tvTitle.setText("这是标题标题标题");
        tvTime.setText("2017-7-15 15:57:51");
        setTitle("资讯详情");
        toolbarIvLeft.setImageResource(R.drawable.ic_back);
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

}
