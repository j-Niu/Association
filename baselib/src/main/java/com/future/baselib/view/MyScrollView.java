package com.future.baselib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by jniu on 2017/6/20.
 */

public class MyScrollView extends ScrollView {

    private onScrollListener listener;

    public void setListener(onScrollListener listener) {
        this.listener = listener;
    }

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (listener != null) {
            listener.onScrollChanged(t);
        }
    }

    public interface onScrollListener{
        void onScrollChanged(int y);
    }
}
