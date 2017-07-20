package com.future.association.common;

import android.os.Environment;

/**
 * 静态常量类
 * Created by jniu on 2017/7/3.
 */

public class Contants {
    public static final int HOTQUESTIONNAI_REFRAGMENT = 0x100;
    public static final int MYQUESTIONNAI_REFRAGMENT = 0x101;

    public static final String SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String APP_IMG_PATH = SD_PATH+"/Association/Pictures";

    //资讯页轮播图接口
    public static final String API_CODE_NEWS_BANNER = "_zxlunbotu_001";
    //资讯列表
    public static final String API_CODE_NEWS_LIST = "_zxliebiao_001";
    //资讯详情
    public static final String API_CODE_NEWS_DETAIL = "_zxlunbotuxiangqing_001";
}
