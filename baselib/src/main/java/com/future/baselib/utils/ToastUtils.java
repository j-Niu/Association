package com.future.baselib.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by jniu on 2017/5/17.
 */

public class ToastUtils {

    Context context;
    Toast toast;

    public ToastUtils(Context context) {
        this.context = context;
        toast = Toast.makeText(context,"",Toast.LENGTH_SHORT);
    }

    public void show(int redId){
        toast.setText(redId);
        toast.show();
    }
    public void show(CharSequence s){
        toast.setText(s);
        toast.show();
    }

    public void cancel(){
        if (toast != null) {
            toast.cancel();
        }
    }
}
