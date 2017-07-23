package com.future.association.personal.util;

import android.content.Context;

import com.future.association.common.MyApp;
import com.future.association.community.request.RequestConfig;
import com.future.association.personal.PersonConstant;
import com.future.association.personal.entity.MyInfoEntity;
import com.future.baselib.utils.HttpRequest;

import java.util.TreeMap;

/**
 * Created by javakam on 2017/7/23.
 */
public class RequestUtil {

    /**
     * 请求数据
     *
     * @param context
     * @param treeMap
     * @param dataResponse
     * @param listener
     */
    private static void requestData(Context context, TreeMap<String, String> treeMap, MyDataResponse dataResponse,
                                    HttpRequest.OnNetworkListener<MyDataResponse> listener) {
        try {
            new HttpRequest<MyDataResponse>()
                    .with(context)
                    .addParams(treeMap)
                    .setListener(listener)
                    .start(dataResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void getMyInfo(Context context,String id, HttpRequest.OnNetworkListener<MyDataResponse> listener){
        TreeMap<String,String> params = new TreeMap<>() ;
        params.put("apiCode", PersonConstant.MY_INFO_SHOW) ;
//        params.put("userToken",MyApp.getUserToken()) ;
        requestData(context,params,new MyDataResponse<MyInfoEntity>().init(MyInfoEntity.class),listener);
    }



    /**
     * 发帖子
     * @param context
     * @param id 板块id
     * @param title 发帖标题
     * @param content 发帖内容
     * @param listener
     */
    public  static void sendTie(Context context,String id,String title,String content,
                                HttpRequest.OnNetworkListener<MyDataResponse> listener){
        TreeMap<String,String> params = new TreeMap<>() ;
        params.put("apiCode",RequestConfig.CODE_SEND_TIE) ;
        params.put("bankuai_id",id) ;
        params.put("userToken",MyApp.getUserToken()) ;
        params.put("content",content) ;
        params.put("title",title) ;
        requestData(context,params,new MyDataResponse(),listener);
    }
}
