package com.future.association.common.utils;

import com.bumptech.glide.request.RequestOptions;
import com.future.association.R;

/**
 * Created by jniu on 2017/7/21.
 */

public class GlideUtils {
    public static RequestOptions defaultImg(){
        return new RequestOptions().placeholder(R.color.color_e0e0e0).error(R.color.color_e0e0e0);
    }
    public static RequestOptions defaultImg2(){
        return new RequestOptions().placeholder(R.drawable.iv_defalut).error(R.drawable.iv_defalut);
    }
    public static RequestOptions defaultImgHead(){
        return new RequestOptions().placeholder(R.drawable.moren_touxiang).error(R.drawable.moren_touxiang);
    }
}
