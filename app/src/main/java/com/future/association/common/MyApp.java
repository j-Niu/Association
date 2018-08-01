package com.future.association.common;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.future.association.BuildConfig;
import com.future.association.community.utils.TextUtil;
import com.future.association.login.util.ActivityManager;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by jniu on 2017/7/3.
 */

public class MyApp extends Application {

    private static MyApp app;
    // activity管理类
    private ActivityManager activityManager = null;

    private static String userToken;//"08bb51d1e82bdb3ac553e8804bcb4f65"
    private static String quanxian;// "1"//1为普通用户 2位管理员第一次登录 3是管理员非第一次登录
    public static String userId;//

    public MyApp() {
        app = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        // 获得activty管理实例
        activityManager = ActivityManager.getInstance();
        super.onCreate();

        JPushInterface.setDebugMode(BuildConfig.DEBUG); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
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
        if ("2".equals(quanxian) || "3".equals(quanxian)) {
            return true;
        } else {
            return false;
        }
    }

    public interface ObserverListener {
        void notifyChange(Bundle bundle, Object object);
    }

    // 实现整个APP观察者模式
    private Map<Integer, ObserverListener> observerListeners = new HashMap<Integer, ObserverListener>();

    // 实现整个app观察者模式,同一个action可以注册多个监听

    /**
     * 注册监听，不需要的时候要取消监听，可在onDestory()中取消
     *
     * @param action
     * @param listener
     */
    public void registerObserver(int action, ObserverListener listener) {
        if (listener != null) {
            observerListeners.put(action, listener);
        }
    }

    /**
     * 解除注册--注册必须解除,防止内存泄露
     *
     * @param action
     */
    public void unRegisterObserver(int action) {
        if (observerListeners.containsKey(action)) {
            observerListeners.remove(action);
        }
    }

    /**
     * 通知已经注册此action的监听去执行 ,action 必传，其他可传(null)
     *
     * @param action 需要传递的action要与注册的一样，
     * @param bundle 封装对象，
     * @param object 也可以传递自己封装的对象，
     */
    public void notifyDataChange(int action, Bundle bundle, Object object) {
        if (observerListeners.containsKey(action) && observerListeners.get(action) != null) {
            observerListeners.get(action).notifyChange(bundle, object);
        }
    }

}