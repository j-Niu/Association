package com.future.baselib.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by jniu on 2017/6/20.
 */

public class CustomViewBottomDialog {

    private Context context;
    private Dialog dialog;

    private View rootView;
    private Display display;

    public CustomViewBottomDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public void setView(View rootView) {
        this.rootView = rootView;
    }

    public CustomViewBottomDialog builder() {

        if (rootView == null) {
            return null;
        }

        // 设置Dialog最小宽度为屏幕宽度
        rootView.setMinimumWidth(display.getWidth());


        // 定义Dialog布局和参数
        dialog = new Dialog(context, com.future.baselib.R.style.ActionSheetDialogStyle);
        dialog.setContentView(rootView);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);

        return this;
    }

    public CustomViewBottomDialog show(){
        if (dialog != null) {
            dialog.show();
            return this;
        }
        return null;
    }
}
