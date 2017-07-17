package com.future.association.common;

import android.app.Application;
import android.text.TextUtils;

import com.future.association.login.util.ActivityManager;

/**
 * Created by jniu on 2017/7/3.
 */

public class MyApp extends Application {

    private static MyApp app;
    // activity管理类
    private ActivityManager activityManager = null;

    private static String userToken = "08bb51d1e82bdb3ac553e8804bcb4f65";

    public MyApp() {
        app = this;
    }

    @Override
    public void onCreate() {
        // 获得activty管理实例
        activityManager = ActivityManager.getInstance();
        super.onCreate();
    }

    //获取userToken
    public static String getUserToken() {
        return userToken;
    }

    public static void setUserToken(String userToken) {
        MyApp.userToken = userToken;
    }

    public static boolean hasLogin(){
        boolean haslogin = !TextUtils.isEmpty(userToken);
        return haslogin;
    }

    public static MyApp getApp() {
        return app;
    }

    public ActivityManager getActivityManager() {
        return activityManager;
    }
}
