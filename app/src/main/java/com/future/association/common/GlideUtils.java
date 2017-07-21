package com.future.association.common;

import com.bumptech.glide.request.RequestOptions;
import com.future.association.R;

/**
 * Created by jniu on 2017/7/21.
 */

public class GlideUtils {
    public static RequestOptions defaultImg(){
        return new RequestOptions().placeholder(R.color.color_e0e0e0).error(R.color.color_e0e0e0);
    }
}
