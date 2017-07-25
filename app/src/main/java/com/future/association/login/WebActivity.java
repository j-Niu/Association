package com.future.association.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.future.association.R;
import com.future.association.community.base.BaseActivity;
import com.future.association.community.utils.TextUtil;
import com.future.association.databinding.ActivityWebBinding;
import com.future.baselib.utils.StatusUtils;


/**
 * Created by Administrator on 2017/7/20.
 */

public class WebActivity extends UBaseActivity {
    ActivityWebBinding binding;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web);
    }

    @Override
    protected void initView() {
        StatusUtils.setStatusbarColor(this, ContextCompat.getColor(this, R.color.color_26A16E));
        setTitle("注册协议");
        setTitleLeft(R.drawable.nav_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        String url = getIntent().getStringExtra("url");
        if (!TextUtil.isEmpty(url)) {
            binding.zcxyWebView.loadUrl(url);
        }
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
