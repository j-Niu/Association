package com.future.association.login;

import android.app.Activity;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.widget.Toast;

import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.databinding.ActivityFindPwdResetBinding;
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

/**
 * Created by Mwh on 2017/7/4.
 */

public class FindPwdResetViewModel {
    private Activity activity;
    private ActivityFindPwdResetBinding binding;
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> errorMessage = new ObservableField<>();
    ToastUtils toastUtils;

    public FindPwdResetViewModel(Activity activity, ActivityFindPwdResetBinding binding) {
        this.activity = activity;
        this.binding = binding;
        toastUtils = new ToastUtils(activity);
    }


    public void initLinstener() {
        RxView
                .clicks(binding.resetPwdCommit)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (PatternUtils.passwordPattern(toastUtils, password.get())) {
                            //提示密码设置成功
                            MyToast.makeText(activity, R.layout.dialog_find_pwd_success, Toast.LENGTH_SHORT).show();
                            //跳转到登陆界面
                            activity.onBackPressed();
                            MyApp.getApp().getActivityManager().finishAllActivityExceptOne(LoginActivity.class);
                            activity.onBackPressed();
//                            CommonUtil.startActivity(activity, LoginActivity.class);
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

    //endregion

}
