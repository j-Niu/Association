package com.future.association.common;

import android.app.Application;

import com.future.association.login.util.ActivityManager;

/**
 * Created by jniu on 2017/7/3.
 */

public class MyApp extends Application {

    private static MyApp app;
    // activity管理类
    private ActivityManager activityManager = null;

    private static String userToken = "59b85ba365102520b758681d85938ceb";

    public MyApp() {
        app = this;
    }

    @Override
    public void onCreate() {
        // 获得activty管理实例
        activityManager = ActivityManager.getInstance();
        super.onCreate();
    }

    public static String getUserToken() {
        return userToken;
    }

    public static MyApp getApp() {
        return app;
    }

    public ActivityManager getActivityManager() {
        return activityManager;
    }
}
