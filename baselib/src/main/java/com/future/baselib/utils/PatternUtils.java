package com.future.baselib.utils;

import android.text.TextUtils;

/**
 * Created by Administrator on 2017/4/24.
 */

public class PatternUtils {

    public static boolean passwordPattern(ToastUtils toast, String pwd) {
        String regex = "^[0-9A-Za-z]{6,16}$";
        if (TextUtils.isEmpty(pwd)) {
            toast.show("密码不能为空");
            return false;
        }
        if (!pwd.matches(regex)) {
            toast.show("密码只能为数字/字符且在6-16位之间");
            return false;
        }
        return true;
    }

    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     *
     * @param mobile 移动、联通、电信运营商的号码段
     *               <p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
     *               、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
     *               <p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p>
     *               <p>电信的号段：133、153、180（未启用）、189</p>
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean mobilePattern(ToastUtils toast, String mobile) {
        if (TextUtils.isEmpty(mobile)) {
            toast.show("手机号不能为空");
            return false;
        }
        String regex = "(\\+\\d+)?1[34578]\\d{9}$";
        if (!mobile.matches(regex)) {
            toast.show("手机号格式不正确，请输入正确手机号");
            return false;
        }
        return true;
    }

    public static boolean namePattern(ToastUtils toast, String name) {
        if (TextUtils.isEmpty(name)) {
            toast.show("姓名不能为空");
            return false;
        } else {
            return true;
        }
    }
}
