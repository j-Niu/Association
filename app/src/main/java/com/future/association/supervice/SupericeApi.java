package com.future.association.supervice;

import android.content.Context;

import com.future.association.supervice.model.SupericeDetail;
import com.future.association.supervice.model.TestBean;
import com.future.baselib.utils.HttpRequest;

/**
 * Created by rain on 2017/7/14.
 */

public class SupericeApi {

    public static final String SUPERICE_TYPE_LIST_APICODE = "_jdfenlei_001";
    public static final String SUPERICE_TYPE_DETAIL_APICODE = "_jdxiangqing_001";
    public static final String SUPERICE_TYPE_PUBLISH_APICODE = "_jdfabu_001";

    private static SupericeApi instance = null;

    private SupericeApi (){}

    public SupericeApi getInstance(){
        if (instance == null){
            instance = new SupericeApi();
        }
        return instance;
    }
/*
* 监督列表
* */
    public HttpRequest getSupericeTypeList(Context context,String token,String size,String page){
        return new HttpRequest<TestBean>()
                .with(context)
                .addParam("apiCode",SUPERICE_TYPE_LIST_APICODE)
                .addParam("token",token)
                .addParam("page",page)
                .addParam("size",size);
    }
/*
* 监督详情
* */
    public HttpRequest getSupericeTypeDetail(Context context,String token,String id){
        return new HttpRequest<SupericeDetail>()
                .with(context)
                .addParam("apiCode",SUPERICE_TYPE_DETAIL_APICODE)
                .addParam("token",token)
                .addParam("id",id);
    }
/*
* 监督发布
* */
    public HttpRequest getSupericeTypePublish(Context context,String token,String id){
        return new HttpRequest<TestBean>()
                .with(context)
                .addParam("apiCode",SUPERICE_TYPE_PUBLISH_APICODE)
                .addParam("token",token)
                .addParam("id",id);
    }
}
