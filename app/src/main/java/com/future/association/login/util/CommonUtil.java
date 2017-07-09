package com.future.association.login.util;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.future.association.R;
import com.future.association.login.MyToast;
import com.future.baselib.utils.ToastUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2017/7/6.
 */

public class CommonUtil {
    //验证码时间
    private static final long count = 10;

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
    public static boolean verifyPattern(ToastUtils toast, String pwd){
        String regex =  "^[0-9]{4,6}$";
        if (TextUtils.isEmpty(pwd)) {
            toast.show("验证码不能为空");
            return false;
        }
        if (!pwd.matches(regex)) {
            toast.show("验证码只能为数字/字符且在4-6位之间");
            return false;
        }
        return true;
    }
    //region 验证码倒计时
    public static void getVerify(final TextView textView, final Activity activity) {
        //延迟执行时间  间隔执行时间  时间单位
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(count + 1)//超过61时停止
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return count - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        //禁用点击事件
                        textView.setEnabled(false);
                        textView.setText("重新发送");
                        textView.setTextColor(ActivityCompat.getColor(activity, R.color.color_999999));
                    }
                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Long aLong) {
                        textView.setText("剩余" + aLong + "秒");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        textView.setEnabled(true);
                        textView.setText("获取验证码");
                        textView.setTextColor(ActivityCompat.getColor(activity, R.color.color_26A16E));

                    }
                });
    }


    public static void testVerifyDown(final Activity activity,final int pos) {
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
                        MyToast.makeText(activity, "验证码已发送", Toast.LENGTH_SHORT,pos).show();
                    }
                });
    }
    //endregion
}
