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
import com.future.association.questionnaire.models.QuestionDetail;
import com.future.association.questionnaire.viewmodels.QuestionnaireWebViewModel;
import com.future.baselib.activity.BaseActivity;

import java.io.File;

/**
 * Created by rain on 2017/7/6.
 */

public class QuestionnaireWebActivity extends BaseActivity {

    private QuestionDetail questionDetail;
    private QuestionnaireWebViewModel questionnaireWebViewModel;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        questionDetail = getIntent().getParcelableExtra("data");
        ActivityQuestionnaireWebBinding questionnaireWebBinding = DataBindingUtil.setContentView(this, R.layout.activity_questionnaire_web);
        questionnaireWebViewModel = new QuestionnaireWebViewModel(this, questionnaireWebBinding, questionDetail);
        questionnaireWebBinding.setQuestionnaireWebViewModel(questionnaireWebViewModel);
    }

    @Override
    protected void initView() {
        showLoadingDialog();
        if (questionDetail == null || TextUtil.isEmpty(questionDetail.getId())) {
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
                    //请求分享数据
                    questionnaireWebViewModel.onShare();
                }
            });
            WebView webView = (WebView) findViewById(R.id.web_view);
            String showurl = questionDetail.getShowurl();
//            showurl = showurl.replace("\\/", File.separator);
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


}
