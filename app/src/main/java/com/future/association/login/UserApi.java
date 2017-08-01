package com.future.association.login;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.future.association.login.bean.CityResponse;
import com.future.association.login.bean.JsonBean;
import com.future.association.login.bean.RegisterResponse;
import com.future.association.login.bean.UserResponse;
import com.future.association.login.bean.VerifyResponse;
import com.future.baselib.entity.BaseResponse;
import com.future.baselib.utils.HttpRequest;

/**
 * Created by Administrator on 2017/7/11.
 */

public class UserApi {
    public static final String API_CODE_LOGIN = "_login_001";
    public static final String API_CODE_SMS_1 = "_sms_001";
    public static final String API_CODE_SMS_2 = "_sms_002";
    public static final String API_CODE_REGISTER = "_register_001";
    public static final String API_CODE_SET_PWD_1 = "_set_pwd_001";
    public static final String API_CODE_SET_PWD_3 = "_set_pwd_003";
    public static final String API_CODE_USER_AGREEMENT = "_useragreement_001";
    public static final String API_CODE_CITY = "_postcity_001";

    public HttpRequest login(Activity activity, String mobile, String loginPass) {
        return new HttpRequest<UserResponse>()//继承BaseResponse的类
                .with(activity)//context
                .addParam("apiCode", API_CODE_LOGIN)//apiCode  接口文档里的code
                .addParam("mobile", mobile)//请求业务参数
                .addParam("loginPass", loginPass);//请求业务参数

    }


    public HttpRequest getVerifyCode(Activity activity, String mobile) {
        return new HttpRequest<VerifyResponse>()//继承BaseResponse的类
                .with(activity)
                .addParam("apiCode", API_CODE_SMS_1)//apiCode  接口文档里的code
                .addParam("mobile", mobile);//请求业务参数
    }


    public HttpRequest getRegisterVerifyCode(Activity activity, String mobile) {
        return new HttpRequest<VerifyResponse>()//继承BaseResponse的类
                .with(activity)
                .addParam("apiCode", API_CODE_SMS_2)//apiCode  接口文档里的code
                .addParam("mobile", mobile);//请求业务参数
    }


    //sex 1男 2女
    public HttpRequest register(Activity activity, String mobile, String code, String loginPass, String real_name, String address, String sex, String schooling, String old) {
        return new HttpRequest<RegisterResponse>()//继承BaseResponse的类
                .with(activity)
                .addParam("apiCode", API_CODE_REGISTER)//apiCode  接口文档里的code
                .addParam("mobile", mobile)
                .addParam("code", code)
                .addParam("loginPass", loginPass)
                .addParam("real_name", real_name)
                .addParam("address", address)
                .addParam("sex", sex)
                .addParam("old", old)
                .addParam("schooling", schooling);//请求业务参数

    }


    public HttpRequest resetPassWord(Activity activity, String mobile, String code, String newPwd) {
        return new HttpRequest<VerifyResponse>()//继承BaseResponse的类
                .with(activity)
                .addParam("apiCode", API_CODE_SET_PWD_3)//apiCode  接口文档里的code
                .addParam("mobile", mobile)
                .addParam("code", code)
                .addParam("newPwd", newPwd);//请求业务参数
    }

    public HttpRequest resetPassWord2(Activity activity, String userToken, String oldPwd, String newPwd, String sureNewPwd) {
        return new HttpRequest<VerifyResponse>()//继承BaseResponse的类
                .with(activity)
                .addParam("apiCode", API_CODE_SET_PWD_1)//apiCode  接口文档里的code
                .addParam("userToken", userToken)
                .addParam("oldPwd", oldPwd)
                .addParam("newPwd", newPwd)
                .addParam("sureNewPwd", sureNewPwd);//请求业务参数
    }

    public HttpRequest userAgreement(Activity activity) {
        return new HttpRequest<VerifyResponse>()//继承BaseResponse的类
                .with(activity)
                .addParam("apiCode", API_CODE_USER_AGREEMENT);//请求业务参数  //apiCode  接口文档里的code
    }

    public HttpRequest<CityResponse> getCitys(Activity activity) {
        return new HttpRequest()//继承BaseResponse的类
                .with(activity)
                .addParam("apiCode", API_CODE_CITY);//请求业务参数  //apiCode  接口文档里的code
    }

//    public HttpRequest<JsonBean> getProvinces(Context context){
//        return new HttpRequest()
//                .with(context)
//                .addParam("apiCode",SUPERICE_TYPE_GET_PROVINCE);
//    }
}
