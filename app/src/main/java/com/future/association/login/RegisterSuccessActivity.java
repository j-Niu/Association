package com.future.association.login;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.StatusUtils;

/**
 * Created by Mwh on 2017/7/4.
 */

public class RegisterSuccessActivity extends UBaseActivity {
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register_success);
    }

    @Override
    protected void initView() {
        setTitle("注册");
        setTitleLeft(R.drawable.nav_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void initLogic() {
        MyApp.getApp().getActivityManager().pushActivity(this);
    }


    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //只保留loginactivity
        MyApp.getApp().getActivityManager().finishAllActivityExceptOne(LoginActivity.class);
    }
}
