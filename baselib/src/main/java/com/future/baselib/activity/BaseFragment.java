package com.future.baselib.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.future.baselib.R;
import com.future.baselib.utils.JLog;
import com.future.baselib.utils.ToastUtils;
import com.future.baselib.view.LoadingDialog;

import java.util.ArrayList;
import java.util.List;


/**
 * Fragment基类
 * <p>Created by kam on 2016/6/25.
 */
public abstract class BaseFragment extends Fragment {
    public Context mContext;
    public LayoutInflater mInflater;

    public Toolbar mToolbar;
    public TextView title;
    public ImageView ivLeft;
    public TextView tvRight;
    public ImageView ivRight;

    private LoadingDialog dialog;

    protected ToastUtils toast;

    /**
     * Bundle  传递数据
     *
     * @param extras
     */
    protected abstract void getBundleExtras(Bundle extras);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mInflater = LayoutInflater.from(mContext);
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            getBundleExtras(extras);
        }
        toast = new ToastUtils(mContext);

        //此处可以给Fragment设置主题
        //mContext = new ContextThemeWrapper(getActivity(), android.R.style.Theme_Holo_Light);
    }

    @Nullable
    @LayoutRes
    protected abstract int getLayoutResId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (mToolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            initTitle(view);
        }
        initView(inflater, container, savedInstanceState);
        return view;
    }

    /**
     * 初始化view
     */
    protected abstract void initView(LayoutInflater inflater, @Nullable ViewGroup container,
                                     @Nullable Bundle savedInstanceState);

    public void onMsgObtain(Message msg) {
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            onMsgObtain(msg);
        }
    };

    /**
     * @return the handler
     */
    public Handler getHandler() {
        return handler;
    }

    public void showShortToast(String text) {
        ToastUtils.shortToast(mContext, text);
    }

    public void showLongToast(String text) {
        ToastUtils.longToast(mContext, text);
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
        Intent intent = new Intent(mContext, activityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void startActivityForResult(Class activityClass, int requestCode) {
        startActivityForResult(activityClass, null, requestCode);
    }

    public void startActivityForResult(Class activityClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(mContext, activityClass);
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
            dialog = new LoadingDialog(mContext);
        }
        dialog.show();
    }

    public void dissmissLoadingDialog() {
        if (dialog != null) {
            dialog.close();
        }
    }

    protected void initTitle(View view) {
        title = (TextView) view.findViewById(R.id.toolbar_tv_title);
        ivLeft = (ImageView) view.findViewById(R.id.toolbar_iv_left);
        ivRight = (ImageView) view.findViewById(R.id.toolbar_iv_right);
        tvRight = (TextView) view.findViewById(R.id.toolbar_tv_right);
        if (null != ivLeft) {
            ivLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
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
    public void onStop() {
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
            if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
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

}