package com.future.association.login;

import android.app.Activity;
import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.future.association.R;
import com.future.association.databinding.ActivityLoginBinding;
import com.future.association.databinding.DialogLoginErrorBinding;
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
 * Created by Administrator on 2017/7/3.
 */

public class LoginViewModel {
    private Dialog errorDialog, protectDialog;
    private DialogLoginErrorBinding errorBinding;
    private Activity activity;
    private ActivityLoginBinding binding;
    public ObservableField<String> phoneNumber = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> errorMessage = new ObservableField<>();
    public ObservableBoolean clearPhonenumberFlag = new ObservableBoolean(false);
    ToastUtils toastUtils;

    public LoginViewModel(Activity activity, ActivityLoginBinding binding) {
        this.activity = activity;
        this.binding = binding;
        toastUtils = new ToastUtils(activity);
        errorBinding = DataBindingUtil.inflate(activity.getLayoutInflater(), R.layout.dialog_login_error, null, false);
    }


    //region 监听事件
    public void initLinstener() {
        //登录按钮执行网络访问
        RxView
                .clicks(binding.loginCommit)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (!TextUtils.isEmpty(errorMessage.get())) {
                            showErrorDialog();
                        } else {
                            //跳转到信息完善页面
                            if (PatternUtils.mobilePattern(toastUtils, phoneNumber.get()) && PatternUtils.passwordPattern(toastUtils, password.get())) {
                                if (phoneNumber.get().equals("13547804180")) {
                                    //测试代码1，执行弹窗，用户第一次登陆，提示修改密码
                                    showProtectDialog();
                                } else {
                                    //测试代码2，提示完善信息，这里是因为找不到入口
                                    activity.finish();
                                }

                            }
                        }
                    }
                });
        //输入电话实现清除按钮的显示
        RxTextView
                .textChangeEvents(binding.loginPhonenumber)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TextViewTextChangeEvent>() {
                    @Override
                    public void accept(@NonNull TextViewTextChangeEvent textViewTextChangeEvent) throws Exception {
                        String inputNumber = textViewTextChangeEvent.text().toString();
                        clearPhonenumberFlag.set(!TextUtils.isEmpty(inputNumber));
                        if (!TextUtils.isEmpty(inputNumber) && !mobilePattern(inputNumber)) {
                            errorMessage.set("请输入正确电话号码");
                        } else if (!TextUtils.isEmpty(password.get()) && !passwordPattern(password.get())) {
                            errorMessage.set("请输入正确密码");
                        } else {
                            errorMessage.set("");
                        }
                    }
                });

        RxTextView
                .textChangeEvents(binding.loginPassword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TextViewTextChangeEvent>() {
                    @Override
                    public void accept(@NonNull TextViewTextChangeEvent textViewTextChangeEvent) throws Exception {
                        String pwd = textViewTextChangeEvent.text().toString();
                        if (!TextUtils.isEmpty(pwd) && !passwordPattern(pwd)) {
                            errorMessage.set("请输入正确密码");
                        } else if (!TextUtils.isEmpty(phoneNumber.get()) && !mobilePattern(phoneNumber.get())) {
                            errorMessage.set("请输入正确电话号码");
                        } else {
                            errorMessage.set("");
                        }
                    }
                });
        //清除电话号码点击事件
        RxView
                .clicks(binding.loginClearPhonenumber)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        phoneNumber.set("");
                        clearPhonenumberFlag.set(false);
                    }
                });
        //忘记密码执行跳转
        RxView
                .clicks(binding.loginForgetPassword)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        CommonUtil.startActivity(activity, FindPwdVerifyActivity.class);
                    }
                });
        //注册密码
        RxView
                .clicks(binding.loginRegister)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        CommonUtil.startActivity(activity, RegisterActivity.class);
                    }
                });
    }
    //endregion

    //region 弹窗
    public void showErrorDialog() {
        if (errorDialog == null) {
            createErrorDialog();
        }
        errorDialog.show();
    }

    public void showProtectDialog() {
        if (protectDialog == null) {
            createProtectDialog();
        }
        protectDialog.show();
    }

    private void createErrorDialog() {
        View view = errorBinding.getRoot();
        errorBinding.loginErrorContent.setText("错误次数过多，请30分钟后重试");
        errorBinding.loginErrorTitle.setText("登陆失败");
        errorDialog = new AlertDialog.Builder(activity).setView(view).create();
        errorDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  // 有白色背景，加这句代码
//        errorDialog.setCancelable(false);
        errorDialog.setCanceledOnTouchOutside(false);
        errorDialog.show();
        Window window = errorDialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        float scale = activity.getResources().getDisplayMetrics().density;
        layoutParams.width = (int) (scale * 270);
        layoutParams.height = (int) (scale * 128);
        layoutParams.y = -70;
        window.setAttributes(layoutParams);
        //设置监听事件
        RxView
                .clicks(errorBinding.loginErrorKnown)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (errorDialog != null) {
                            errorDialog.dismiss();
                        }
                    }
                });
    }

    private void createProtectDialog() {
        View view = errorBinding.getRoot();
        errorBinding.loginErrorContent.setText("为保护账户安全,请修改密码");
        errorBinding.loginErrorTitle.setText("提示");
        protectDialog = new AlertDialog.Builder(activity).setView(view).create();
        protectDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  // 有白色背景，加这句代码
//        errorDialog.setCancelable(false);
        protectDialog.setCanceledOnTouchOutside(false);
        protectDialog.show();
        Window window = protectDialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        float scale = activity.getResources().getDisplayMetrics().density;
        layoutParams.width = (int) (scale * 270);
        layoutParams.height = (int) (scale * 128);
        layoutParams.y = -70;
        window.setAttributes(layoutParams);
        //设置监听事件
        RxView
                .clicks(errorBinding.loginErrorKnown)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (protectDialog != null) {
                            protectDialog.dismiss();
                            //同时跳转到修改密码
                            CommonUtil.startActivity(activity, FindPwdResetActivity.class);
                        }
                    }
                });
    }
    //endregion

    //region get set方法
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
