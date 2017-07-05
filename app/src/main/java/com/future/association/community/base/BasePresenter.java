package com.future.association.community.base;

import android.content.Context;

/**
 * Created by HX·罗 on 2017/6/2.
 */

public class BasePresenter <T extends Basefview>{
    public T t ;
    public BasePresenter(T t){
        this.t = t ;
    }

}
