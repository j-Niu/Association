package com.future.baselib.utils;


import com.future.baselib.entity.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Author: xzp
 * Date: 2017/5/22
 */
public class EventBusUtil {

    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void sendEvent(MessageEvent event) {
        EventBus.getDefault().post(event);
    }

    public static void sendStickyEvent(MessageEvent event) {
        EventBus.getDefault().postSticky(event);
    }
}
