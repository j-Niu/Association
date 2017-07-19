package com.future.association.common;

import android.app.Application;
import android.text.TextUtils;

import com.future.association.community.utils.TextUtil;
import com.future.association.login.util.ActivityManager;

/**
 * Created by jniu on 2017/7/3.
 */

public class MyApp extends Application {

    private static MyApp app;
    // activity管理类
    private ActivityManager activityManager = null;

    private static String userToken;//"08bb51d1e82bdb3ac553e8804bcb4f65"
    private static String quanxian;// "1"//1为普通用户 2位管理员第一次登录 3是管理员非第一次登录

    public MyApp() {
        app = this;
    }

    @Override
    public void onCreate() {
        // 获得activty管理实例
        activityManager = ActivityManager.getInstance();
        super.onCreate();
    }

    public static String getQuanxian() {
        return quanxian;
    }

    public static void setQuanxian(String quanxian) {
        if (!TextUtil.isEmpty(quanxian)) {
            MyApp.quanxian = quanxian;
        }
    }

    //获取userToken
    public static String getUserToken() {
        return userToken;
    }

    public static void setUserToken(String userToken) {
        MyApp.userToken = userToken;
    }

    public static boolean hasLogin() {
        boolean haslogin = !TextUtils.isEmpty(userToken);
        return haslogin;
    }

    public static MyApp getApp() {
        return app;
    }

    public ActivityManager getActivityManager() {
        return activityManager;
    }

    /**
     * 是否具有管理员权限
     *
     * @return
     */
    public static boolean isAdministrator() {
        if ("1".equals(quanxian)) {
            return false;
        } else {
            return true;
        }
    }

}
