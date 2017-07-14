package com.future.association.login.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.widget.Toast;

import com.future.association.databinding.ActivityFindPwdVerifyBinding;
import com.future.association.login.FindPwdResetActivity;
import com.future.association.login.UserApi;
import com.future.association.login.bean.VerifyResponse;
import com.future.association.login.util.CommonUtil;
import com.future.baselib.entity.BaseResponse;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.PatternUtils;
import com.future.baselib.utils.ToastUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

import static com.future.association.login.util.CommonUtil.mobilePattern;
import static com.future.association.login.util.CommonUtil.verifyPattern;

/**
 * Created by Mwh on 2017/7/4.
 */

public class FindPwdVerifyViewModel {
    private Activity activity;
    private ActivityFindPwdVerifyBinding binding;
    public ObservableField<String> phoneNumber = new ObservableField<>();
    public ObservableField<String> smsCode = new ObservableField<>();
    public ObservableField<String> errorMessage = new ObservableField<>();
    public ObservableBoolean clearPhonenumberFlag = new ObservableBoolean(false);
    UserApi userApi;
    ToastUtils toastUtils;

    public FindPwdVerifyViewModel(Activity activity, ActivityFindPwdVerifyBinding binding) {
        this.activity = activity;
        this.binding = binding;
        toastUtils = new ToastUtils(activity);
        userApi = new UserApi();
    }

    private void checkPhoneMessage(String phoneNumber, String password, String smsCode) {
        if (!TextUtils.isEmpty(phoneNumber) && !mobilePattern(phoneNumber)) {
            errorMessage.set("请输入正确电话号码");
            return;
        }

        if (!TextUtils.isEmpty(password) && !mobilePattern(password)) {
            errorMessage.set("请输入正确密码");
            return;
        }

        if (!TextUtils.isEmpty(smsCode) && !verifyPattern(smsCode)) {
            errorMessage.set("请输入正确验证码");
            return;
        }
        errorMessage.set("");
    }

    //region 监听事件
    public void initLinstener() {
        RxTextView
                .textChangeEvents(binding.findPwdPhonenumber)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TextViewTextChangeEvent>() {
                    @Override
                    public void accept(@NonNull TextViewTextChangeEvent textViewTextChangeEvent) throws Exception {
                        String inputNumber = textViewTextChangeEvent.text().toString();
                        clearPhonenumberFlag.set(!TextUtils.isEmpty(inputNumber));
                        if (!TextUtils.isEmpty(inputNumber) && !mobilePattern(inputNumber)) {
                            errorMessage.set("请输入正确电话号码");
                        } else if (!TextUtils.isEmpty(smsCode.get()) && !verifyPattern(smsCode.get())) {
                            errorMessage.set("请输入正确验证码");
                        } else {
                            errorMessage.set("");
                        }
                    }
                });


        RxTextView
                .textChangeEvents(binding.findPwdVerifyCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TextViewTextChangeEvent>() {
                    @Override
                    public void accept(@NonNull TextViewTextChangeEvent textViewTextChangeEvent) throws Exception {
                        String code = textViewTextChangeEvent.text().toString();
                        if (!TextUtils.isEmpty(code) && !verifyPattern(code)) {
                            errorMessage.set("请输入正确验证码");
                        } else if (!TextUtils.isEmpty(phoneNumber.get()) && !mobilePattern(phoneNumber.get())) {
                            errorMessage.set("请输入正确电话号码");
                        } else {
                            errorMessage.set("");
                        }
                    }
                });
        RxView
                .clicks(binding.findPwdSendVerifyCode)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        //验证码倒计时
                        CommonUtil.getVerify(binding.findPwdSendVerifyCode, activity);
                        if (PatternUtils.mobilePattern(toastUtils, phoneNumber.get())) {
                            HttpRequest request = userApi.getVerifyCode(activity, phoneNumber.get()).setListener(new HttpRequest.OnNetworkListener() {
                                @Override
                                public void onSuccess(BaseResponse response) {
                                    toastUtils.show(response.info);
                                }

                                @Override
                                public void onFail(String message) {
                                    toastUtils.show(message);
                                }
                            });
                            request.start(new VerifyResponse());
                        }
                    }
                });

        RxView
                .clicks(binding.findPwdNext)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (PatternUtils.mobilePattern(toastUtils, phoneNumber.get()) && CommonUtil.verifyPattern(toastUtils, smsCode.get())) {
                            Intent intent = new Intent(activity, FindPwdResetActivity.class);
                            intent.putExtra("phoneNumber", phoneNumber.get());
                            intent.putExtra("code", smsCode.get());
                            intent.putExtra("type", CommonUtil.RESET_PASSWORD_FORGET);
                            activity.startActivity(intent);
                        }
                    }
                });

        RxView
                .clicks(binding.findPwdClearPhonenumber)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        phoneNumber.set("");
                        clearPhonenumberFlag.set(false);
                    }
                });
    }
    //endregion

    //region get set method
    public ObservableField<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(ObservableField<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ObservableField<String> getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(ObservableField<String> smsCode) {
        this.smsCode = smsCode;
    }

    public ObservableField<String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ObservableField<String> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ObservableBoolean getClearPhonenumberFlag() {
        return clearPhonenumberFlag;
    }

    public void setClearPhonenumberFlag(ObservableBoolean clearPhonenumberFlag) {
        this.clearPhonenumberFlag = clearPhonenumberFlag;
    }
    //endregion
}
