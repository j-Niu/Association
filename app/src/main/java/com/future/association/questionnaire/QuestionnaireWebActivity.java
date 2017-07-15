package com.future.association.questionnaire;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.future.association.R;
import com.future.association.databinding.ActivityQuestionnaireWebBinding;
import com.future.baselib.activity.BaseActivity;

/**
 * Created by rain on 2017/7/6.
 */

public class QuestionnaireWebActivity extends BaseActivity{
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        ActivityQuestionnaireWebBinding questionnaireWebBinding = DataBindingUtil.setContentView(this,R.layout.activity_questionnaire_web);
    }

    @Override
    protected void initView() {
        setTitle(R.string.questionnaire_detail);
        setTitleLeft(R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        WebView webView = (WebView) findViewById(R.id.web_view);
//        webView.loadUrl("http://p1.gexing.com/shaitu/20120822/1921/5034c0cfa2571.jpg");
        webView.loadUrl("http://img1.3lian.com/2015/w8/31/d/101.jpg");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl(url);
            }
        });
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
