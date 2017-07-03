package com.future.baselib.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.future.baselib.R;

/**
 * Created by jniu on 2017/6/3.
 */

public class LoadingDialog {
    Dialog mLoadingDialog;

    public LoadingDialog(Context context) {
        // 首先得到整个View
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_loading_dialog, null);
        // 创建自定义样式的Dialog
        mLoadingDialog = new Dialog(context, R.style.loading_dialog);
        // 设置返回键无效
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
    }

    public void show(){
        mLoadingDialog.show();
    }

    public void close(){
        if (mLoadingDialog!=null) {
            mLoadingDialog.dismiss();
        }
    }
}