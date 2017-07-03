package com.future.baselib.utils;

import android.util.Log;

/**
 * Created by jniu on 2017/6/5.
 */

public class JLog {

    private static boolean isDebug = true;

    public static void e(String tag,String msg){
        if (isDebug) {
            Log.e(tag,msg);
        }
    }

}
