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
import com.future.association.login.FindPwdResetActivity;
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
import com.trello.rxlifecycle2.android.ActivityEvent;

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
    private FindPwdResetActivity activity;
    private ActivityFindPwdResetBinding binding;
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> errorMessage = new ObservableField<>();
    String phoneNumber;
    String code;
    int type;
    UserApi userApi;
    Handler handler;

    public FindPwdResetViewModel(FindPwdResetActivity activity, ActivityFindPwdResetBinding binding) {
        this.activity = activity;
        this.binding = binding;
        userApi = new UserApi();
        handler = new Handler();
    }


    public void initLinstener() {
        RxView
                .clicks(binding.resetPwdCommit)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (PatternUtils.passwordPattern(activity.toast, password.get())) {
                            HttpRequest request = null;
                            if (type == 1) {
                                activity.showLoadingDialog();
                                //获取userResponse
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
                                                        CommonUtil.storeLoginMsg(activity, CommonUtil.userResponse);
                                                        //跳转到登陆界面
                                                        MyApp.getApp().getActivityManager().finishAllActivity();
                                                        activity.dissmissLoadingDialog();
                                                        startActivity(activity, MainActivity.class);
                                                    }
                                                }, 1500L);
                                            }

                                            @Override
                                            public void onFail(String message) {
                                                activity.dissmissLoadingDialog();
                                                activity.toast.show("" + message);
                                            }
                                        });
                            } else if (type == 2) {
                                activity.showLoadingDialog();
                                request = userApi
                                        .resetPassWord(activity, phoneNumber, code, password.get())
                                        .setListener(new HttpRequest.OnNetworkListener() {
                                            @Override
                                            public void onSuccess(BaseResponse response) {
                                                //提示密码设置成功
                                                MyToast.makeText(activity, R.layout.dialog_find_pwd_success, Toast.LENGTH_SHORT).show();
                                                handler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        //跳转到登陆界面
                                                        MyApp.getApp().getActivityManager().finishAllActivityExceptOne(LoginActivity.class);
                                                        activity.dissmissLoadingDialog();
                                                        activity.onBackPressed();
                                                    }
                                                }, 1500);
                                            }

                                            @Override
                                            public void onFail(String message) {
                                                activity.dissmissLoadingDialog();
                                                activity.toast.show("" + message);
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
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        TextViewTextChangeEvent textViewTextChangeEvent = (TextViewTextChangeEvent) o;
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
