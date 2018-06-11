package com.future.association.supervice;

import android.content.Context;

import com.future.association.login.bean.JsonBean;
import com.future.association.supervice.model.SupericeDetail;
import com.future.association.supervice.model.SupericeList;
import com.future.association.supervice.model.SupericerTypeList;
import com.future.baselib.utils.HttpRequest;

/**
 * Created by rain on 2017/7/14.
 */

public class SupericeApi {

    public static final String SUPERICE_TYPE_LIST_APICODE = "_jdfenlei_001";
    public static final String SUPERICE_LIST_APICODE = "_jdliebiao_001";
    public static final String SUPERICE_TYPE_DETAIL_APICODE = "_jdxiangqing_001";
    public static final String SUPERICE_TYPE_PUBLISH_APICODE = "_jdfabu_001";//_jdfabu_001
    public static final String SUPERICE_TYPE_NATURE = "_jdfabunature_001";//监督性质
    public static final String SUPERICE_TYPE_GET_PROVINCE = "_postcity_001";//三级地址获取

    private static SupericeApi instance = null;

    private SupericeApi (){}

    public synchronized static SupericeApi getInstance(){
        if (instance == null){
            instance = new SupericeApi();
        }
        return instance;
    }

    /*
* 监督分类
* */
    public HttpRequest getSupericeTypeList(Context context,String token){
        return new HttpRequest<SupericerTypeList>()
                .with(context)
                .addParam("apiCode",SUPERICE_TYPE_LIST_APICODE);
    }

/*
* 监督列表
* */
    public HttpRequest getSupericeList(Context context,String page,String userToken){
        return new HttpRequest<SupericeList>()
                .with(context)
                .addParam("apiCode",SUPERICE_LIST_APICODE)
                .addParam("page",page)
                .addParam("userToken",userToken)
                .addParam("size","20");
    }
/*
* 监督详情
* */
    public HttpRequest getSupericeTypeDetail(Context context,String id){
        return new HttpRequest<SupericeDetail>()
                .with(context)
                .addParam("apiCode",SUPERICE_TYPE_DETAIL_APICODE)
                .addParam("id",id);
    }
/*
* 监督发布
* */
    public HttpRequest publishSuperice(Context context,String userToken, String hangye, String address,String title, String reason, String image,String nature){
        return new HttpRequest<SupericeDetail>()
                .with(context)
                .addParam("apiCode",SUPERICE_TYPE_PUBLISH_APICODE)
                .addParam("userToken",userToken)
                .addParam("hangye",hangye)
                .addParam("address",address)
                .addParam("title",title)
                .addParam("reason",reason)
                .addParam("nature",nature)
                .addParam("image",image);
    }
    /*
     * 监督性质列表
     * cf968e0ff0c50eb42d556fabc54f624e
     * */
    public HttpRequest getSupericeNature(Context context,String userToken){
        return new HttpRequest<>()
                .with(context)
                .addParam("apiCode",SUPERICE_TYPE_NATURE)
                .addParam("userToken",userToken);
    }

    /*
    * 三级地址数据获取
    * */
    public HttpRequest<JsonBean> getProvinces(Context context){
        return new HttpRequest()
                .with(context)
                .addParam("apiCode",SUPERICE_TYPE_GET_PROVINCE);
    }
}
