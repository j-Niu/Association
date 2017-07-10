package com.future.association.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.future.association.BR;
import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.databinding.ActivityLoginBinding;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.entity.DefaultResponse;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.PatternUtils;
import com.future.baselib.utils.StatusUtils;
import com.future.baselib.utils.ToastUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class LoginActivity extends BaseActivity {
    ActivityLoginBinding binding;
    LoginViewModel loginViewModel;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
    }

    @Override
    protected void initView() {
        loginViewModel = new LoginViewModel(this, binding);
        binding.setVariable(BR.loginViewModel, loginViewModel);
        loginViewModel.initLinstener();
        setTitle("登录");
    }

    @Override
    protected void initLogic() {
        MyApp.getApp().getActivityManager().pushActivity(this);
        StatusUtils.setStatusbarColor(this, ContextCompat.getColor(this, R.color.color_26A16E));

        new HttpRequest<DefaultResponse>()
                .with(this)
                .addParam("apiCode","_sms_001")
                .addParam("mobile","18516515573")
                .setListener(new HttpRequest.OnNetworkListener<DefaultResponse>() {
                    @Override
                    public void onSuccess(DefaultResponse response) {
                        toast("请求成功");
                    }

                    @Override
                    public void onFail(String message) {
                        toast(message);
                    }
                }).start(new DefaultResponse());
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

}
