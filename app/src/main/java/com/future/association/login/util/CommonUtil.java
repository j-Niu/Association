package com.future.association.login.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.login.MyToast;
import com.future.association.login.bean.UserResponse;
import com.future.baselib.utils.ToastUtils;
import com.google.gson.Gson;

import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2017/7/6.
 */

public class CommonUtil {
    //登录保存的用户类
    public static UserResponse userResponse;
    public static int RESET_PASSWORD_SET = 1;
    public static int RESET_PASSWORD_FORGET = 2;
    //验证码时间
    private static final long count = 60;

    public static void startActivity(Activity activity, Class cls) {
        Intent intent = new Intent(activity, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public static boolean mobilePattern(String mobile) {
        if (TextUtils.isEmpty(mobile)) {
            return false;
        }
        String regex = "(\\+\\d+)?1[34578]\\d{9}$";
        if (!mobile.matches(regex)) {
            return false;
        }
        return true;
    }

    public static boolean passwordPattern(String pwd) {
        String regex = "^[0-9A-Za-z]{6,16}$";
        if (TextUtils.isEmpty(pwd)) {
            return false;
        }
        if (!pwd.matches(regex)) {
            return false;
        }
        return true;
    }

    public static boolean verifyPattern(String smsCode) {
        String regex = "^[0-9]{4,6}$";
        if (TextUtils.isEmpty(smsCode)) {
            return false;
        }
        if (!smsCode.matches(regex)) {
            return false;
        }
        return true;
    }

    public static boolean verifyPattern(ToastUtils toast, String code) {
        String regex = "^[0-9]{4,6}$";
        if (TextUtils.isEmpty(code)) {
            toast.show("验证码不能为空");
            return false;
        }
        if (!code.matches(regex)) {
            toast.show("验证码只能为数字/字符且在4-6位之间");
            return false;
        }
        return true;
    }

    //倒计时
    public static Disposable oi;

    //region 验证码倒计时
    public static void getVerify(final TextView textView, final Activity activity) {
        //延迟执行时间  间隔执行时间  时间单位
        oi = Flowable.interval(0, 1, TimeUnit.SECONDS)
                .take(count + 1)//超过61时停止
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return count - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(@NonNull Subscription subscription) throws Exception {
                        //禁用点击事件
                        textView.setEnabled(false);
                        textView.setText("重新发送");
                        textView.setTextColor(ActivityCompat.getColor(activity, R.color.color_999999));
                    }
                }).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        textView.setText("剩余" + aLong + "秒");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (oi != null) {
                            oi.dispose();
                            oi = null;
                        }
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        textView.setEnabled(true);
                        textView.setText("获取验证码");
                        textView.setTextColor(ActivityCompat.getColor(activity, R.color.color_26A16E));
                    }
                });


//        new Observer<Long>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//            }
//
//            @Override
//            public void onNext(Long aLong) {
//                textView.setText("剩余" + aLong + "秒");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//            }
//
//            @Override
//            public void onComplete() {
//                textView.setEnabled(true);
//                textView.setText("获取验证码");
//                textView.setTextColor(ActivityCompat.getColor(activity, R.color.color_26A16E));
//
//            }
//        }
    }

    public static void cancleOi(Activity activity, TextView verifyView) {
        if (oi != null) {
            oi.dispose();
        }
        verifyView.setText("重新发送");
        verifyView.setEnabled(true);
        verifyView.setTextColor(ActivityCompat.getColor(activity, R.color.color_26A16E));
    }


    public static void testVerifyDown(final Activity activity, final int pos) {
        final long dowmCount = 2;
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(dowmCount + 1)//超过2时停止
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Long aLong) {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        MyToast.makeText(activity, "验证码已发送", Toast.LENGTH_SHORT, pos).show();
                    }
                });
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    //endregion


    public static void storeLoginMsg(Activity activity, UserResponse userResponse) {
        Gson gson = new Gson();
        PreferenceManager
                .getDefaultSharedPreferences(activity)
                .edit()
                .putString("user", gson.toJson(userResponse))
                .putBoolean("islogin", true)
                .putString("userToken", userResponse.userToken)
                .putString("quanxian", userResponse.quanxian)
                .putString("userId", userResponse.userId)
                .apply();
        MyApp.setQuanxian(userResponse.quanxian);
        MyApp.setUserToken(userResponse.userToken);
        MyApp.userId = userResponse.userId;
    }

    public static void clearLoginMsg(Activity activity) {
        Gson gson = new Gson();
        UserResponse response = new UserResponse();
        PreferenceManager
                .getDefaultSharedPreferences(activity)
                .edit()
                .putString("user", gson.toJson(response))
                .putBoolean("islogin", false)
                .putString("userToken", response.userToken)
                .putString("quxian", response.quanxian)
                .apply();
    }
}
