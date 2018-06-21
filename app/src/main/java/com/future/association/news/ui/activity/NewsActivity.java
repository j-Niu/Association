package com.future.association.news.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.future.association.R;
import com.future.association.common.Contants;
import com.future.association.common.MyApp;
import com.future.association.common.ShareContentCustom;
import com.future.association.community.utils.TextUtil;
import com.future.association.news.entity.NewsDetailsResponse;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;

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
    @BindView(R.id.tv_from)
    TextView tvFrom;
    @BindView(R.id.webView)
    WebView webView;
    private String id;
    private NewsDetailsResponse newsDetail;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {

        setTitle("资讯详情");
        setTitleRight(R.drawable.icon_share, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newsDetail!=null && newsDetail.newsDetails!=null) {
                    showShare(NewsActivity.this, newsDetail.newsDetails.content,newsDetail.newsDetails.url,newsDetail.newsDetails.title);
                }
            }
        });
        toolbarIvLeft.setImageResource(R.drawable.ic_back);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                if(url.contains(""))
                ToastUtils.shortToast(NewsActivity.this,url);
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                // TODO Auto-generated method stub
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
            }

        });

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
                        newsDetail = response;
                        tvTitle.setText(response.newsDetails.title);
                        tvTime.setText(response.newsDetails.create_time);
                        tvFrom.setText("来源："+response.newsDetails.info_from);
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
    public  void showShare(Context context, String shareSummary, String shareUrl, String shareTitle) {
//        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
//        oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//		oks.setComment("我是阳光微警务发布的资讯内容，快来阅读吧！");

        //默认分享内容
        String shareContent = context.getResources().getString(R.string.app_name);
        if (!TextUtils.isEmpty(shareSummary)) {
            shareContent = shareSummary;
        }
        //默认分享图片
        String image = "http://img9.3lian.com/c1/vector/10/01/155.jpg";
//        if (!TextUtils.isEmpty(shareImg)) {
//            image = shareImg;
//        }

//        // text是分享文本，所有平台都需要这个字段
//        oks.setText(shareContent + shareUrl);
        //不同平台差异化配置
        oks.setShareContentCustomizeCallback(new ShareContentCustom(context, shareContent,
                shareUrl, image, shareTitle));
//        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl(shareUrl);
//        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(getString(R.string.app_name));
//        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//        oks.setImageUrl(image);
//        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle(shareTitle);
//        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl(shareUrl);
//        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl(shareUrl);

        // 启动分享GUI
        oks.show(context);
    }
}
