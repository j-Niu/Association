package com.future.association.community.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class Res {

    /**
     * 获取drawable资源
     * @param id
     * @param context
     * @return
     */
    public static Drawable getDrawableRes(int id, Context context){
        return ResourcesCompat.getDrawable(context.getResources(),id,null);
    }

    /**
     * 获取dimension资源
     * @param id
     * @param context
     * @return
     */
    public static float getDimen(int id,Context context){
        return context.getResources().getDimension(id) ;
    }

    /**
     * 获取颜色资源
     * @param id
     * @param context
     * @return
     */
    public static int getColorRes(int id, Context context){
        return ResourcesCompat.getColor(context.getResources(),id,null) ;
    }

}
