package com.future.association.community.base;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.community.utils.ConstantUtil;
import com.future.association.community.utils.Res;
import com.future.association.community.utils.ScreenUtils;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;


/**
 * Created by Leo on 2016/8/2 0002.
 */
public abstract class BaseActivity<G extends ViewDataBinding> extends Activity implements View.OnClickListener {

    /**
     * 设置activity布局
     */
    public abstract int setContentView();

    /**
     * 初始化组件
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化监听
     */
    public abstract void initListener();


    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";
    public int color = 0;
    public boolean doubleKeyExit = false;//是否双击推出应用
    public Context context;//上下文对象
    private long firstTime = 0;//第一次按下返回键
    private long secondTime = 0;//第二次按下返回键
    private Toast toast;
    public boolean isAlph = false;
    public boolean isTop = false;
    public View parentView;
    public LayoutInflater inflater;
    public G viewBinding;
    public MyApp app;
    public boolean fullScreen = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        if (color == 0) {
            color = Res.getColorRes(R.color.color_26A16E, context);
        }
        app = (MyApp) getApplication();
        parentView = View.inflate(context, setContentView(), null);
        try {
            viewBinding = DataBindingUtil.bind(parentView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!isTop) {
            parentView.setFitsSystemWindows(true);
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(parentView);
        inflater = LayoutInflater.from(context);
        if (!fullScreen) {
            ScreenUtils.titleAlph(isAlph, color, this);
        }
        ActivityManager.addActivity(this);
        setContentView();
        initView();
        initData();
        initListener();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs)
    {
        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT))
        {
            view = new AutoFrameLayout(context, attrs);
        }

        if (name.equals(LAYOUT_LINEARLAYOUT))
        {
            view = new AutoLinearLayout(context, attrs);
        }

        if (name.equals(LAYOUT_RELATIVELAYOUT))
        {
            view = new AutoRelativeLayout(context, attrs);
        }

        if (view != null) return view;

        return super.onCreateView(name, context, attrs);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && doubleKeyExit) {
            if (firstTime == 0) {
                firstTime = System.currentTimeMillis();
                showShortToast("再次点击返回键退出应用");
            } else {
                secondTime = System.currentTimeMillis();
                long interval = secondTime - firstTime;
                if (interval <= ConstantUtil.DOUBLE_CLICK_EXIT_DENY) {
                    ActivityManager.exitApplicaion();
                } else {
                    firstTime = 0;
                    secondTime = 0;
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public final <T extends View> T findView(int id) {
        return (T) super.findViewById(id);
    }

    /**
     * 弹出short toast提示
     *
     * @param msg
     */
    public void showShortToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    toast.cancel();
                } catch (Exception e) {
                }
                toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
                toast.show();

            }
        });
    }

    /**
     * 弹出long toast提示
     *
     * @param msg
     */
    public void showLongToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    toast.cancel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    @Override
    protected void onPause() {
        if (toast != null) {//toast随页面消失而消失
            try {
                toast.cancel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 判断当前应用程序处于前台还是后台
     */
    public boolean isBackground() {
        android.app.ActivityManager activityManager = (android.app.ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);

        String packageName = getApplicationContext().getPackageName();
        List<android.app.ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();

        if (appProcesses == null)
            return false;

        for (android.app.ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {

            if (appProcess.processName.equals(packageName) && appProcess.importance == android.app.ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

}
