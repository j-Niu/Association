package com.future.association.login;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.TextUtils;
import android.widget.TextView;

import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.common.view.MainActivity;
import com.future.association.login.bean.VerifyResponse;
import com.future.association.login.util.CommonUtil;
import com.future.baselib.entity.BaseResponse;
import com.future.baselib.utils.HttpRequest;
import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class LoginActivity extends UBaseActivity {


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initLogic() {
        MyApp.getApp().getActivityManager().pushActivity(this);
//        StatusUtils.setStatusbarColor(this, ContextCompat.getColor(this, R.color.color_26A16E));
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }


    @Override
    protected void initView() {
        TextView zcxy = (TextView) findViewById(R.id.zcxy);
        zcxy.setText(Html.fromHtml(String.format(getString(R.string.zcxy))));
        //判定是否登录
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("islogin", false)) {
            MyApp.setQuanxian(PreferenceManager.getDefaultSharedPreferences(this).getString("quanxian", ""));
            MyApp.setUserToken(PreferenceManager.getDefaultSharedPreferences(this).getString("userToken", ""));

            MyApp.userId = PreferenceManager.getDefaultSharedPreferences(this).getString("userId", "");
            CommonUtil.startActivity(this, MainActivity.class);
            finish();
        }
        RxView.clicks(findViewById(R.id.login_commit))
                .throttleFirst(1, java.util.concurrent.TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        startActivity(LoginActivity2.class);
                    }
                });
        RxView.clicks(findViewById(R.id.guest_login))
                .throttleFirst(1, java.util.concurrent.TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        startActivity(MainActivity.class);
                    }
                });
        RxView.clicks(findViewById(R.id.login_register))
                .throttleFirst(1, java.util.concurrent.TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        startActivity(RegisterActivity.class);
                    }
                });
        RxView.clicks(findViewById(R.id.login_forget_password))
                .throttleFirst(1, java.util.concurrent.TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        startActivity(FindPwdVerifyActivity.class);
                    }
                });
        RxView
                .clicks(findViewById(R.id.zcxy))
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .throttleFirst(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        HttpRequest request = new UserApi().userAgreement(LoginActivity.this).setListener(new HttpRequest.OnNetworkListener() {
                            @Override
                            public void onSuccess(BaseResponse response) {
                                Intent intent = new Intent(LoginActivity.this, WebActivity.class);
                                String url = response.info;
                                if (!TextUtils.isEmpty(url)) {
                                    intent.putExtra("url", url);
                                    startActivity(intent);
                                } else {
                                    toast("url获取失败");
                                }
                            }

                            @Override
                            public void onFail(String message) {
                                toast("" + message);
                            }
                        });
                        request.start(new VerifyResponse());
                    }

                });



//        loginViewModel = new LoginViewModel(this, binding);
//        binding.setVariable(BR.loginViewModel, loginViewModel);
//        loginViewModel.initLinstener();
//        setTitle("登录");
    }
}
