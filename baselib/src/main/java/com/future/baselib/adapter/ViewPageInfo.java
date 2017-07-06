package com.future.baselib.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * 页面滑动信息
 */
public final class ViewPageInfo {

	public final String tag;
    public final Class<?> clss;
    public final Bundle args;
    public final String title;
    public final Fragment fragment;

    public ViewPageInfo(String _title, String _tag, Class<?> _class, Bundle _args) {
    	title = _title;
        tag = _tag;
        clss = _class;
        args = _args;
        fragment = null;
    }
    
    public ViewPageInfo(String _title, String _tag, Fragment _fragment, Bundle _args){
    	title = _title;
        tag = _tag;
        clss = null;
        args = _args;
        fragment = _fragment;
    }
}
