package com.future.association.questionnaire.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.future.association.R;
import com.future.association.community.utils.TextUtil;
import com.future.association.databinding.ActivityQuestionnaireWebBinding;
import com.future.association.questionnaire.models.QuestionList;
import com.future.baselib.activity.BaseActivity;

import java.io.File;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by rain on 2017/7/6.
 */

public class QuestionnaireWebActivity extends BaseActivity {
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        ActivityQuestionnaireWebBinding questionnaireWebBinding = DataBindingUtil.setContentView(this, R.layout.activity_questionnaire_web);
    }

    @Override
    protected void initView() {
        showLoadingDialog();
        final QuestionList questionList = getIntent().getParcelableExtra("data");
        if (questionList == null || TextUtil.isEmpty(questionList.getShowurl())) {
            toast("该问卷已过期");
            dissmissLoadingDialog();
            finish();
            return;
        } else {
            setTitle(R.string.questionnaire_detail);
            setTitleLeft(R.drawable.ic_back, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            setTitleRight(R.drawable.icon_share, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //分享
                    showShare(questionList);
                }
            });
            WebView webView = (WebView) findViewById(R.id.web_view);
//        webView.loadUrl("http://p1.gexing.com/shaitu/20120822/1921/5034c0cfa2571.jpg");
            String showurl = questionList.getShowurl();
            showurl = showurl.replace("\\/", File.separator);
            webView.loadUrl(showurl);
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient() {

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    dissmissLoadingDialog();
                }
            });
        }
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    private void showShare(QuestionList questionList) {
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(questionList.getTitle());
// titleUrl是标题的网络链接，QQ和QQ空间等使用
//        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("这里是概要信息");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        String showurl = questionList.getShowurl();
        showurl = showurl.replace("\\/", File.separator);
        oks.setUrl(showurl);
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(showurl);

// 启动分享GUI
        oks.show(this);
    }
}
