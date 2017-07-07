package com.future.association.login;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.future.association.R;

/**
 * Created by Mwh on 2017/7/4.
 */

public class MyToast {
    private Toast mToast;

    private MyToast(Context context, CharSequence text, int duration, int yPos) {
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_toast, null);
        TextView textView = (TextView) v.findViewById(R.id.toast_content);
        textView.setText(text);
        mToast = new Toast(context);
        mToast.setDuration(duration);
        mToast.setView(v);
        mToast.setGravity(Gravity.CENTER, 0, yPos);
    }

    private MyToast(Context context, int layoutId, int duration) {
        View v = LayoutInflater.from(context).inflate(layoutId, null);
        mToast = new Toast(context);
        mToast.setDuration(duration);
        mToast.setView(v);
        mToast.setGravity(Gravity.CENTER, 0, 0);
    }

    public static MyToast makeText(Context context, CharSequence text, int duration, int yPos) {
        return new MyToast(context, text, duration, yPos);
    }

    public static MyToast makeText(Context context, int layoutId, int duration) {
        return new MyToast(context, layoutId, duration);
    }

    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }

    public void setGravity(int gravity, int xOffset, int yOffset) {
        if (mToast != null) {
            mToast.setGravity(gravity, xOffset, yOffset);
        }
    }
}
