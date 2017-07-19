package com.future.association.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.future.association.BR;
import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.databinding.ActivityRegisterBinding;
import com.future.association.login.viewmodel.RegisterViewModel;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.StatusUtils;

/**
 * Created by Mwh on 2017/7/4.
 */

public class RegisterActivity extends UBaseActivity {
    ActivityRegisterBinding binding;
    RegisterViewModel registerViewModel;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
    }

    @Override
    protected void initView() {
        registerViewModel = new RegisterViewModel(this, binding);
        binding.setVariable(BR.registerViewModel, registerViewModel);
        registerViewModel.initLinstener();
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
        StatusUtils.setStatusbarColor(this, ContextCompat.getColor(this, R.color.color_26A16E));
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }


}
