package com.future.association.common;

import android.app.Application;

/**
 * Created by jniu on 2017/7/3.
 */

public class MyApp extends Application {

    private static MyApp app;

    public MyApp() {
        app = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static MyApp getApp() {
        return app;
    }
}
