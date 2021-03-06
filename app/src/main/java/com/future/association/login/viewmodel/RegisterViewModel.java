package com.future.association.login.viewmodel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.future.association.databinding.ActivityRegisterBinding;
import com.future.association.login.MyToast;
import com.future.association.login.PerfectInformationActivity;
import com.future.association.login.RegisterActivity;
import com.future.association.login.UserApi;
import com.future.association.login.WebActivity;
import com.future.association.login.bean.VerifyResponse;
import com.future.association.login.util.CommonUtil;
import com.future.baselib.entity.BaseResponse;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.PatternUtils;
import com.future.baselib.utils.ToastUtils;
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
import static com.future.association.login.util.CommonUtil.verifyPattern;

/**
 * Created by Mwh on 2017/7/4.
 */

public class RegisterViewModel {
    UserApi userApi;
    private RegisterActivity activity;
    private ActivityRegisterBinding binding;
    private Dialog errorDialog;
    public ObservableField<String> phoneNumber = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> smsCode = new ObservableField<>();
    public ObservableField<String> errorMessage = new ObservableField<>();
    public ObservableBoolean clearPhonenumberFlag = new ObservableBoolean(false);

    public RegisterViewModel(RegisterActivity activity, ActivityRegisterBinding binding) {
        this.activity = activity;
        this.binding = binding;
        userApi = new UserApi();
    }

    //region linstener
    public void initLinstener() {
        RxTextView
                .textChangeEvents(binding.registerPhonenumber)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        TextViewTextChangeEvent textViewTextChangeEvent = (TextViewTextChangeEvent) o;
                        String inputNumber = textViewTextChangeEvent.text().toString();
                        clearPhonenumberFlag.set(!TextUtils.isEmpty(inputNumber));
                        if (!TextUtils.isEmpty(inputNumber) && !mobilePattern(inputNumber)) {
                            errorMessage.set("请输入正确电话号码");
                        } else if (!TextUtils.isEmpty(smsCode.get()) && !verifyPattern(smsCode.get())) {
                            errorMessage.set("请输入正确验证码");
                        } else if (!TextUtils.isEmpty(password.get()) && !passwordPattern(password.get())) {
                            errorMessage.set("请输入正确密码");
                        } else {
                            errorMessage.set("");
                        }
                    }
                });

        RxTextView
                .textChangeEvents(binding.registerPassword)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        TextViewTextChangeEvent textViewTextChangeEvent = (TextViewTextChangeEvent) o;
                        String password = textViewTextChangeEvent.text().toString();
                        if (!TextUtils.isEmpty(password) && !passwordPattern(password)) {
                            errorMessage.set("请输入正确密码");
                        } else if (!TextUtils.isEmpty(phoneNumber.get()) && !mobilePattern(phoneNumber.get())) {
                            errorMessage.set("请输入正确电话号码");
                        } else if (!TextUtils.isEmpty(smsCode.get()) && !verifyPattern(smsCode.get())) {
                            errorMessage.set("请输入正确验证码");
                        } else {
                            errorMessage.set("");
                        }
                    }
                });

        RxTextView
                .textChangeEvents(binding.registerVerifyCode)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        TextViewTextChangeEvent textViewTextChangeEvent = (TextViewTextChangeEvent) o;
                        String code = textViewTextChangeEvent.text().toString();
                        if (!TextUtils.isEmpty(code) && !verifyPattern(code)) {
                            errorMessage.set("请输入正确验证码");
                        } else if (!TextUtils.isEmpty(phoneNumber.get()) && !mobilePattern(phoneNumber.get())) {
                            errorMessage.set("请输入正确电话号码");
                        } else if (!TextUtils.isEmpty(password.get()) && !passwordPattern(password.get())) {
                            errorMessage.set("请输入正确密码");
                        } else {
                            errorMessage.set("");
                        }
                    }
                });

        RxView
                .clicks(binding.registerSendVerifyCode)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (PatternUtils.mobilePattern(activity.toast, phoneNumber.get())) {
                            CommonUtil.getVerify(binding.registerSendVerifyCode, activity);
                            HttpRequest request = userApi
                                    .getRegisterVerifyCode(activity, phoneNumber.get())
                                    .setListener(new HttpRequest.OnNetworkListener<VerifyResponse>() {
                                        @Override
                                        public void onSuccess(VerifyResponse response) {//请求成功回调
                                            MyToast.makeText(activity, "" + response.info, Toast.LENGTH_SHORT, 50).show();
                                        }

                                        @Override
                                        public void onFail(String message) {
                                            //请求失败回调
                                            MyToast.makeText(activity, message, Toast.LENGTH_SHORT, 50).show();
                                            //初始化
                                            CommonUtil.cancleOi(activity, binding.registerSendVerifyCode);
                                        }
                                    });
                            request.start(new VerifyResponse());
                        }

                    }
                });
        RxView
                .clicks(binding.registerClearPhonenumber)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        phoneNumber.set("");
                    }
                });
        RxView
                .clicks(binding.registerNext)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (PatternUtils.mobilePattern(activity.toast, phoneNumber.get())
                                && PatternUtils.passwordPattern(activity.toast, password.get())
                                && CommonUtil.verifyPattern(activity.toast, smsCode.get())) {
                            Intent intent = new Intent(activity, PerfectInformationActivity.class);
                            //phoneNumber  code  password
                            intent.putExtra("phoneNumber", phoneNumber.get());
                            intent.putExtra("code", smsCode.get());
                            intent.putExtra("password", password.get());
                            activity.startActivity(intent);
                        }
                    }
                });

        RxView
                .clicks(binding.agreementDetail)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .throttleFirst(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        HttpRequest request = userApi.userAgreement(activity).setListener(new HttpRequest.OnNetworkListener() {
                            @Override
                            public void onSuccess(BaseResponse response) {
                                Intent intent = new Intent(activity, WebActivity.class);
                                String url = response.info;
                                if (!TextUtils.isEmpty(url)) {
                                    intent.putExtra("url", url);
                                    activity.startActivity(intent);
                                } else {
                                    activity.toast.show("url获取失败");
                                }
                            }

                            @Override
                            public void onFail(String message) {
                                activity.toast.show("" + message);
                            }
                        });
                        request.start(new VerifyResponse());
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

    public ObservableField<String> getPassword() {
        return password;
    }

    public void setPassword(ObservableField<String> password) {
        this.password = password;
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
