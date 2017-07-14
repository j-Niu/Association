package com.future.association.login.viewmodel;

import android.app.Activity;
import android.databinding.ObservableField;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.future.association.R;
import com.future.association.common.MainActivity;
import com.future.association.common.MyApp;
import com.future.association.databinding.ActivityFindPwdResetBinding;
import com.future.association.login.LoginActivity;
import com.future.association.login.MyToast;
import com.future.association.login.UserApi;
import com.future.association.login.bean.VerifyResponse;
import com.future.association.login.util.CommonUtil;
import com.future.baselib.entity.BaseResponse;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.PatternUtils;
import com.future.baselib.utils.ToastUtils;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

import static com.future.association.login.util.CommonUtil.mobilePattern;
import static com.future.association.login.util.CommonUtil.passwordPattern;
import static com.future.association.login.util.CommonUtil.startActivity;

/**
 * Created by Mwh on 2017/7/4.
 */

public class FindPwdResetViewModel {
    private Activity activity;
    private ActivityFindPwdResetBinding binding;
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> errorMessage = new ObservableField<>();
    ToastUtils toastUtils;
    String phoneNumber;
    String code;
    int type;
    UserApi userApi;
    Handler handler;

    public FindPwdResetViewModel(Activity activity, ActivityFindPwdResetBinding binding) {
        this.activity = activity;
        this.binding = binding;
        toastUtils = new ToastUtils(activity);
        userApi = new UserApi();
        handler = new Handler();
    }


    public void initLinstener() {
        RxView
                .clicks(binding.resetPwdCommit)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (PatternUtils.passwordPattern(toastUtils, password.get())) {
                            HttpRequest request = null;
                            if (type == 1) {
                                //获取userResponse
                                // if()
                                request = userApi
                                        .resetPassWord2(activity, CommonUtil.userResponse.userToken, "000000", password.get(), password.get())
                                        .setListener(new HttpRequest.OnNetworkListener() {
                                            @Override
                                            public void onSuccess(BaseResponse response) {
                                                //提示密码设置成功
                                                MyToast.makeText(activity, R.layout.dialog_find_pwd_success, Toast.LENGTH_SHORT).show();
                                                handler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        //将用户信息保存打xml中
                                                        Gson gson = new Gson();
                                                        PreferenceManager
                                                                .getDefaultSharedPreferences(activity)
                                                                .edit()
                                                                .putString("user", gson.toJson(CommonUtil.userResponse))
                                                                .putBoolean("islogin", true)
                                                                .apply();
                                                        //跳转到登陆界面
                                                        MyApp.getApp().getActivityManager().finishAllActivity();
                                                        startActivity(activity, MainActivity.class);
                                                    }
                                                }, 1500L);
                                            }

                                            @Override
                                            public void onFail(String message) {
                                                toastUtils.show("" + message);
                                            }
                                        });
                            } else if (type == 2) {
                                request = userApi
                                        .resetPassWord(activity, phoneNumber, code, password.get())
                                        .setListener(new HttpRequest.OnNetworkListener() {
                                            @Override
                                            public void onSuccess(BaseResponse response) {
                                                //提示密码设置成功
                                                MyToast.makeText(activity, R.layout.dialog_find_pwd_success, Toast.LENGTH_SHORT).show();
                                                //跳转到登陆界面
                                                MyApp.getApp().getActivityManager().finishAllActivityExceptOne(LoginActivity.class);
                                                activity.onBackPressed();
                                            }

                                            @Override
                                            public void onFail(String message) {
                                                toastUtils.show("" + message);
                                            }
                                        });
                            }
                            if (request != null) {
                                request.start(new VerifyResponse());
                            }
                        }

                    }
                });

        RxTextView
                .textChangeEvents(binding.resetPwdPassword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TextViewTextChangeEvent>() {
                    @Override
                    public void accept(@NonNull TextViewTextChangeEvent textViewTextChangeEvent) throws Exception {
                        String pwd = textViewTextChangeEvent.text().toString();
                        if (!TextUtils.isEmpty(pwd) && !passwordPattern(pwd)) {
                            errorMessage.set("请输入正确密码");
                        } else {
                            errorMessage.set("");
                        }
                    }
                });
    }

    //region  get set method
    public ObservableField<String> getPassword() {
        return password;
    }

    public void setPassword(ObservableField<String> password) {
        this.password = password;
    }

    public ObservableField<String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ObservableField<String> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    //endregion

}
