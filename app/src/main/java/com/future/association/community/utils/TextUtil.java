package com.future.association.community.utils;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
public class TextUtil {

    /**
      *判断字符串是否是空字符串
      *@auther Leo
      *created at 2016/8/2 0002 12:57
      */
    public static boolean isEmpty(String str){
        if(str == null || "".equals(str) || "null".equals(str)){
            return true ;
        }else{
            return false ;
        }
    }
    public static SpannableStringBuilder getChargeRecordContent(String text, String colorText, int color){
        SpannableStringBuilder style=new SpannableStringBuilder(text);
        int index = text.indexOf(colorText) ;
        style.setSpan(new ForegroundColorSpan(color),
                index, colorText.length()+index, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return style ;
    }
    public static String filterOffUtf8Mb4(String buff) {
        String regEx = "[^0-9\u4E00-\u9FA5]";
        return buff.replaceAll(regEx,"").trim() ;
    }

}
