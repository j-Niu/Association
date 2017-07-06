package com.future.association.login;

import android.app.Activity;
import android.app.Dialog;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.widget.Toast;

import com.future.association.databinding.ActivityRegisterBinding;
import com.future.association.login.util.CommonUtil;
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
import static com.future.association.login.util.CommonUtil.passwordPattern;
import static com.future.association.login.util.CommonUtil.verifyPattern;

/**
 * Created by Mwh on 2017/7/4.
 */

public class RegisterViewModel {
    private Activity activity;
    private ActivityRegisterBinding binding;
    private Dialog errorDialog;
    public ObservableField<String> phoneNumber = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> smsCode = new ObservableField<>();
    public ObservableField<String> errorMessage = new ObservableField<>();
    public ObservableBoolean clearPhonenumberFlag = new ObservableBoolean(false);
    ToastUtils toastUtils;

    public RegisterViewModel(Activity activity, ActivityRegisterBinding binding) {
        this.activity = activity;
        this.binding = binding;
        toastUtils = new ToastUtils(activity);
    }

    //region linstener
    public void initLinstener() {
        RxTextView
                .textChangeEvents(binding.registerPhonenumber)
                .debounce(600, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TextViewTextChangeEvent>() {
                    @Override
                    public void accept(@NonNull TextViewTextChangeEvent textViewTextChangeEvent) throws Exception {
                        String inputNumber = textViewTextChangeEvent.text().toString();
                        if (!TextUtils.isEmpty(inputNumber)) {
                            clearPhonenumberFlag.set(true);
                            //执行匹配
                            if (!mobilePattern(inputNumber)) {
                                errorMessage.set("请输入正确电话号码");
                            } else {
                                //检测密码是否输入
                                String code = smsCode.get();
                                if (!TextUtils.isEmpty(code) && !passwordPattern(code)) {
                                    errorMessage.set("请输入正确验证码");
                                } else {
                                    String pwd = password.get();
                                    if (!TextUtils.isEmpty(pwd) && !passwordPattern(pwd)) {
                                        errorMessage.set("请输入正确密码");
                                    } else {
                                        errorMessage.set("");
                                    }
                                }
                            }
                        } else {
                            clearPhonenumberFlag.set(false);
                        }
                    }
                });

        RxTextView
                .textChangeEvents(binding.registerPassword)
                .debounce(600, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TextViewTextChangeEvent>() {
                    @Override
                    public void accept(@NonNull TextViewTextChangeEvent textViewTextChangeEvent) throws Exception {
                        String password = textViewTextChangeEvent.text().toString();
                        if (!TextUtils.isEmpty(password)) {
                            //执行匹配
                            if (!passwordPattern(password)) {
                                errorMessage.set("请输入正确密码");
                            } else {
                                //检测电话号码输入是否正确
                                String inputNumber = phoneNumber.get();
                                if (!TextUtils.isEmpty(inputNumber) && !mobilePattern(inputNumber)) {
                                    errorMessage.set("请输入正确电话号码");
                                } else {
                                    String code = smsCode.get();
                                    if (!TextUtils.isEmpty(code) && !verifyPattern(code)) {
                                        errorMessage.set("请输入正确验证码");
                                    } else {
                                        errorMessage.set("");
                                    }
                                }
                            }
                        } else {
                            errorMessage.set("");
                        }
                    }
                });
        RxTextView
                .textChangeEvents(binding.registerVerifyCode)
                .debounce(600, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TextViewTextChangeEvent>() {
                    @Override
                    public void accept(@NonNull TextViewTextChangeEvent textViewTextChangeEvent) throws Exception {
                        String code = textViewTextChangeEvent.text().toString();
                        if (!TextUtils.isEmpty(code)) {
                            //执行匹配
                            if (!verifyPattern(code)) {
                                errorMessage.set("请输入正确验证码");
                            } else {
                                //检测电话号码输入是否正确
                                String inputNumber = phoneNumber.get();
                                if (!TextUtils.isEmpty(inputNumber) && !mobilePattern(inputNumber)) {
                                    errorMessage.set("请输入正确电话号码");
                                } else {
                                    String pwd = password.get();
                                    if (!TextUtils.isEmpty(pwd) && !passwordPattern(pwd)) {
                                        errorMessage.set("请输入正确密码");
                                    } else {
                                        errorMessage.set("");
                                    }
                                }
                            }
                        } else {
                            errorMessage.set("");
                        }
                    }
                });
        RxView
                .clicks(binding.registerSendVerifyCode)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (PatternUtils.mobilePattern(toastUtils, phoneNumber.get())) {
                            if (phoneNumber.get().equals("13547804180")) {
                                //模拟测试
                                MyToast.makeText(activity, "手机号码已经注册", Toast.LENGTH_SHORT,50).show();
                            } else {
                                CommonUtil.getVerify(binding.registerSendVerifyCode, activity);
                                CommonUtil.testVerifyDown(activity,50);
                            }
                        }

                    }
                });
        RxView
                .clicks(binding.registerClearPhonenumber)
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
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (PatternUtils.mobilePattern(toastUtils, phoneNumber.get())
                                && PatternUtils.passwordPattern(toastUtils, password.get())
                                && CommonUtil.verifyPattern(toastUtils, smsCode.get())) {
                            CommonUtil.startActivity(activity, PerfectInformationActivity.class);
                        }
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
