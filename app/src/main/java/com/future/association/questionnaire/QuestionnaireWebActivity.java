package com.future.association.questionnaire;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.future.association.R;
import com.future.baselib.activity.BaseActivity;

/**
 * Created by rain on 2017/7/6.
 */

public class QuestionnaireWebActivity extends BaseActivity{
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_questionnaire_web);
    }

    @Override
    protected void initView() {
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
