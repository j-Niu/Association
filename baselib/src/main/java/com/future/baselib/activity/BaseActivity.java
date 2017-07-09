package com.future.baselib.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.future.baselib.R;
import com.future.baselib.utils.JLog;
import com.future.baselib.utils.StatusUtils;
import com.future.baselib.utils.ToastUtils;
import com.future.baselib.view.LoadingDialog;
import com.zhy.autolayout.AutoLayoutActivity;

import java.lang.reflect.Method;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 跳转方法
 * 接收传值方法
 * Android 4.4版本以上沉浸式的封装
 * Toolbar的封装
 * 界面之间消息传递的封装（EventBus）
 * 加载中动画的封装
 * Toast
 * Created by jniu on 2017/6/3.
 */

public abstract class BaseActivity extends AutoLayoutActivity {


    public Toolbar mToolbar;
    public TextView title;
    public ImageView ivLeft;
    public TextView tvRight;
    public ImageView ivRight;

    private LoadingDialog dialog;

    protected ToastUtils toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusUtils.setStatusbarTranslucent(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            getBundleExtras(extras);
        }

        toast = new ToastUtils(this);

        initContentView(savedInstanceState);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            initTitle();
        }

        initLogic();
        initView();
    }

    /**
     * 跳转
     *
     * @param activityClass
     */
    public void startActivity(Class activityClass) {
        startActivity(activityClass, null);
    }

    public void startActivity(Class activityClass, Bundle bundle) {
        Intent intent = new Intent(this, activityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void startActivityForResult(Class activityClass, int requestCode) {
        startActivityForResult(activityClass, null, requestCode);
    }

    public void startActivityForResult(Class activityClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, activityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 加载对话框
     */
    public void showLoadingDialog() {
        if (dialog == null) {
            dialog = new LoadingDialog(this);
        }
        dialog.show();
    }

    public void dissmissLoadingDialog() {
        if (dialog != null) {
            dialog.close();
        }
    }


    /**
     * 替代onCreate的使用
     */
    protected abstract void initContentView(Bundle savedInstanceState);

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化逻辑
     */
    protected abstract void initLogic();

    /**
     * Bundle  传递数据
     *
     * @param extras
     */
    protected abstract void getBundleExtras(Bundle extras);

    protected void initTitle() {
        title = (TextView) findViewById(R.id.toolbar_tv_title);
        ivLeft = (ImageView) findViewById(R.id.toolbar_iv_left);
        ivRight = (ImageView) findViewById(R.id.toolbar_iv_right);
        tvRight = (TextView) findViewById(R.id.toolbar_tv_right);
        if (null != ivLeft) {
            ivLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    public void setTitleLeft(int drawable, View.OnClickListener listener) {
        ivLeft.setImageResource(drawable);
        ivLeft.setOnClickListener(listener);
    }

    public void setTitleRight(int drawable, View.OnClickListener listener) {
        ivRight.setImageResource(drawable);
        ivRight.setOnClickListener(listener);
    }

    public void setTitleRight(String str, View.OnClickListener listener) {
        tvRight.setText(str);
        tvRight.setOnClickListener(listener);
    }

    public void setTitle(String title) {
        if (null != this.title)
            this.title.setText(title);
    }

    public void setTitle(int resId) {
        if (null != this.title) {
            this.title.setText(resId);
        }
    }

    public void toast(String msg) {
        toast.show(msg);
    }

    public void loge(String tag, String msg) {
        JLog.e(tag, msg);
    }

    @Override
    protected void onStop() {
        super.onStop();
        toast.cancel();
    }

    public void checkedPermission(String[] permissions) {
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        //检查权限
        List<String> deniedPermissions = findDeniedPermissions(permissions);//获取当前需申请权限列表

        if (deniedPermissions != null && deniedPermissions.size() > 0) {
            //代表有权限需要申请

        } else {

        }

    }

    public List<String> findDeniedPermissions(String... permissions) {
        List<String> deniedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permission);
            }
        }

        return deniedPermissions;
    }

    /**
     * 检测这些权限中是否有 没有授权需要提示的
     *
     * @param activity
     * @param permission
     * @return
     */
    public static boolean shouldShowPermissions(Activity activity, String... permission) {

        for (String value : permission) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    value)) {
                return true;
            }
        }
        return false;
    }


    protected void setStatusBarIconDark(boolean dark) {
        try {
            Object win = getWindow();
            Class<?> cls = win.getClass();
            Method method = cls.getDeclaredMethod("setStatusBarIconDark", boolean.class);
            method.invoke(win, dark);
        } catch (Exception e) {
            JLog.e("ff", "statusBarIconDark,e=" + e);
        }
    }

}
