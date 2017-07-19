package com.future.association.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.future.association.BR;
import com.future.association.R;
import com.future.association.common.MainActivity;
import com.future.association.common.MyApp;
import com.future.association.databinding.ActivityLoginBinding;
import com.future.association.login.util.CommonUtil;
import com.future.association.login.viewmodel.LoginViewModel;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.StatusUtils;
import com.trello.rxlifecycle2.components.RxActivity;

public class LoginActivity extends UBaseActivity {
    ActivityLoginBinding binding;
    LoginViewModel loginViewModel;


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
    }

    protected void initLogic() {
        MyApp.getApp().getActivityManager().pushActivity(this);
        StatusUtils.setStatusbarColor(this, ContextCompat.getColor(this, R.color.color_26A16E));
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }


    protected void initView() {
        //判定是否登录
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("islogin", false)) {
            MyApp.setQuanxian(PreferenceManager.getDefaultSharedPreferences(this).getString("quanxian", ""));
            MyApp.setUserToken(PreferenceManager.getDefaultSharedPreferences(this).getString("userToken", ""));
            CommonUtil.startActivity(this, MainActivity.class);
            finish();
        }

        loginViewModel = new LoginViewModel(this, binding);
        binding.setVariable(BR.loginViewModel, loginViewModel);
        loginViewModel.initLinstener();
        setTitle("登录");
    }
}
