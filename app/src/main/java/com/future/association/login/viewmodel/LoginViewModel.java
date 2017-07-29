package com.future.association.login.viewmodel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.future.association.R;
import com.future.association.common.MainActivity;
import com.future.association.databinding.ActivityLoginBinding;
import com.future.association.databinding.DialogLoginErrorBinding;
import com.future.association.databinding.DialogLoginProtectBinding;
import com.future.association.login.FindPwdResetActivity;
import com.future.association.login.FindPwdVerifyActivity;
import com.future.association.login.LoginActivity;
import com.future.association.login.PerfectInformationActivity;
import com.future.association.login.RegisterActivity;
import com.future.association.login.UserApi;
import com.future.association.login.bean.UserResponse;
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
import static com.future.association.login.util.CommonUtil.verifyPattern;

/**
 * Created by Administrator on 2017/7/3.
 */

public class LoginViewModel {
    UserApi userApi = new UserApi();
    private Dialog errorDialog, protectDialog;
    private DialogLoginErrorBinding errorBinding;
    private DialogLoginProtectBinding protectBinding;
    private LoginActivity activity;
    private ActivityLoginBinding binding;
    public ObservableField<String> phoneNumber = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> errorMessage = new ObservableField<>();
    public ObservableBoolean clearPhonenumberFlag = new ObservableBoolean(false);

    public LoginViewModel(LoginActivity activity, ActivityLoginBinding binding) {
        this.activity = activity;
        this.binding = binding;
        errorBinding = DataBindingUtil.inflate(activity.getLayoutInflater(), R.layout.dialog_login_error, null, false);
        protectBinding = DataBindingUtil.inflate(activity.getLayoutInflater(), R.layout.dialog_login_protect, null, false);
    }


    //region 监听事件
    public void initLinstener() {
        //登录按钮执行网络访问
        RxView
                .clicks(binding.loginCommit)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (PatternUtils.mobilePattern(activity.toast, phoneNumber.get()) && PatternUtils.passwordPattern(activity.toast, password.get())) {
                            //登陆弹窗
                            activity.showLoadingDialog();
                            //测试代码2，提示完善信息，这里是因为找不到入口
                            HttpRequest userResponseHttpRequest = userApi
                                    .login(activity, phoneNumber.get(), password.get())
                                    .setListener(new HttpRequest.OnNetworkListener<UserResponse>() {
                                        @Override
                                        public void onSuccess(UserResponse response) {
                                            //获取是否修改账户面膜标志 response.xxx
                                            String quanxian = response.quanxian;
                                            if (quanxian.equals("2")) {
                                                //当获取到的标志位强制修改密码的时候
                                                //将response保存到缓存目录
                                                CommonUtil.userResponse = response;
                                                //展示提示用户修改密码
                                                showProtectDialog();
                                            } else {
                                                //当获取到的标志位不强制修改密码的时候
                                                CommonUtil.storeLoginMsg(activity, response);
                                                //跳转到主页
                                                CommonUtil.startActivity(activity, MainActivity.class);
                                                activity.finish();
                                            }
                                            activity.dissmissLoadingDialog();
                                        }

                                        @Override
                                        public void onFail(String message) {
                                            if (CommonUtil.isNumeric(message)) {
                                                showErrorDialog(message);
                                            } else if (message.equals("用户审核未通过，请重新申请")) {
                                                startActivity(activity, PerfectInformationActivity.class);
                                            } else {
                                                activity.toast.show("" + message);
                                            }
                                            activity.dissmissLoadingDialog();
                                        }
                                    });
                            userResponseHttpRequest.start(new UserResponse());
                        }
                    }
                });
        //输入电话实现清除按钮的显示
        RxTextView
                .textChangeEvents(binding.loginPhonenumber)
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
                        } else if (!TextUtils.isEmpty(password.get()) && !passwordPattern(password.get())) {
                            errorMessage.set("请输入正确密码");
                        } else {
                            errorMessage.set("");
                        }
                    }
                });

        RxTextView
                .textChangeEvents(binding.loginPassword)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        TextViewTextChangeEvent textViewTextChangeEvent = (TextViewTextChangeEvent) o;
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
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
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
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
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
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
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
    public void showErrorDialog(String message) {
        if (errorDialog == null) {
            createErrorDialog(message);
        }
        if (errorBinding != null) {
            errorBinding.loginErrorContent.setText("错误次数过多，请" + message + "分钟后重试");
        }
        errorDialog.show();
    }

    public void showProtectDialog() {
        if (protectDialog == null) {
            createProtectDialog();
        }
        protectDialog.show();
    }

    private void createErrorDialog(String message) {
        View view = errorBinding.getRoot();
        errorBinding.loginErrorTitle.setText("登陆失败");
        errorDialog = new AlertDialog.Builder(activity).setView(view).create();
        errorDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  // 有白色背景，加这句代码
        errorDialog.setCanceledOnTouchOutside(false);
        errorDialog.show();
        Window window = errorDialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        float scale = activity.getResources().getDisplayMetrics().density;
        layoutParams.width = (int) (scale * 270);
        //(int) (scale * 128)
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
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
        View view = protectBinding.getRoot();
        protectBinding.loginProtectContent.setText("为保护账户安全,请修改密码");
        protectBinding.loginProtectTitle.setText("提示");
        protectDialog = new AlertDialog.Builder(activity).setView(view).create();
        protectDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  // 有白色背景，加这句代码
        protectDialog.setCanceledOnTouchOutside(false);
        protectDialog.show();
        Window window = protectDialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        float scale = activity.getResources().getDisplayMetrics().density;
        layoutParams.width = (int) (scale * 270);
        //(int) (scale * 128)
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.y = -70;
        window.setAttributes(layoutParams);
        //设置监听事件
        RxView
                .clicks(protectBinding.loginProtectKnown)
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (protectDialog != null) {
                            protectDialog.dismiss();
                            Intent intent = new Intent(activity, FindPwdResetActivity.class);
                            intent.putExtra("type", CommonUtil.RESET_PASSWORD_SET);
                            activity.startActivity(intent);
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
