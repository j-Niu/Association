package com.future.association.community.request;

import android.content.Context;

import com.future.association.common.MyApp;
import com.future.association.community.model.MsgDetailInfo;
import com.future.association.community.model.MsgNotifyInfo;
import com.future.association.community.model.NotifyReplyInfo;
import com.future.association.community.model.PlateInfo;
import com.future.association.community.model.TieDetailInfo;
import com.future.association.community.model.TieInfo;
import com.future.association.community.model.TieReplyInfo;
import com.future.association.community.model.UserPlateInfo;
import com.future.association.community.model.WGCauseInfo;
import com.future.association.community.utils.ConstantUtil;
import com.future.baselib.utils.HttpRequest;

import java.util.TreeMap;

/**
 * Created by HX·罗 on 2017/7/10.
 */

public class CommunityRequest {

    /**
     * 请求数据
     * @param context
     * @param treeMap
     * @param dataResponse
     * @param listener
     */
    private static void requestData(Context context, TreeMap<String,String> treeMap, DataResponse dataResponse,
                                    HttpRequest.OnNetworkListener<DataResponse> listener){
        try{
            new HttpRequest<DataResponse>()
                    .with(context)
                    .addParams(treeMap)
                    .setListener(listener)
                    .start(dataResponse);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 请求板块列表
     * @param context
     * @param listener
     */
    public static void getPlateList(Context context, HttpRequest.OnNetworkListener<DataResponse> listener){
        TreeMap<String,String> params = new TreeMap<>() ;
        params.put("apiCode",RequestConfig.CODE_PLATE_LIST) ;
        params.put("userToken",MyApp.getUserToken()) ;
        requestData(context,params,new DataResponse<UserPlateInfo>().init(UserPlateInfo.class),listener);
    }

    /**
     * 获取消息通知列表
     * @param context
     * @param page 页数
     * @param listener
     */
    public static void getNotifyList(Context context,int page ,HttpRequest.OnNetworkListener<DataResponse> listener){
        TreeMap<String,String> params = new TreeMap<>() ;
        params.put("apiCode",RequestConfig.CODE_NOTIFY_LIST) ;
        params.put("page",page+"") ;
        params.put("size", ConstantUtil.PAGE_SIZE) ;
        params.put("userToken",MyApp.getUserToken()) ;
        requestData(context,params,new DataResponse<MsgNotifyInfo>().init(MsgNotifyInfo.class),listener);
    }

    /**
     * 获取帖子列表
     * @param context
     * @param id
     * @param page 页数
     * @param listener
     */
    public static void getTieList(Context context,String id, int page , HttpRequest.OnNetworkListener<DataResponse> listener){
        TreeMap<String,String> params = new TreeMap<>() ;
        params.put("apiCode",RequestConfig.CODE_TIE_LIST) ;
        params.put("page",page+"") ;
        params.put("userToken",MyApp.getUserToken()) ;
        params.put("size", ConstantUtil.PAGE_SIZE) ;
        params.put("id",id) ;
        requestData(context,params,new DataResponse<TieInfo>().init(TieInfo.class),listener);
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
                                HttpRequest.OnNetworkListener<DataResponse> listener){
        TreeMap<String,String> params = new TreeMap<>() ;
        params.put("apiCode",RequestConfig.CODE_SEND_TIE) ;
        params.put("bankuai_id",id) ;
        params.put("userToken",MyApp.getUserToken()) ;
        params.put("content",content) ;
        params.put("title",title) ;
        requestData(context,params,new DataResponse(),listener);
    }

    /**
     * 获取帖子详情
     * @param context
     * @param id 帖子id
     * @param listener
     */
    public static void getTieDetail(Context context,String id,HttpRequest.OnNetworkListener<DataResponse> listener){
        TreeMap<String,String> params = new TreeMap<>() ;
        params.put("apiCode",RequestConfig.CODE_TIE_DETAIL) ;
        params.put("id",id) ;
        params.put("userToken",MyApp.getUserToken()) ;
        requestData(context,params,new DataResponse<TieDetailInfo>().init(TieDetailInfo.class),listener);
    }

    /**
     * 获取帖子的回复
     * @param context
     * @param id
     * @param page
     * @param listener
     */
    public static void getTieReply(Context context,String id,int page,HttpRequest.OnNetworkListener<DataResponse> listener){
        TreeMap<String,String> params = new TreeMap<>() ;
        params.put("apiCode",RequestConfig.CODE_TIE_REPLY) ;
        params.put("id",id) ;
        params.put("page",page+"") ;
        params.put("size", ConstantUtil.PAGE_SIZE) ;
        params.put("userToken",MyApp.getUserToken()) ;
        requestData(context,params,new DataResponse<TieReplyInfo>().init(TieReplyInfo.class),listener);
    }

    /**
     * 回复帖子
     * @param context
     * @param id
     * @param content
     * @param listener
     */
    public static void postReplyTie(Context context,String id,String content,HttpRequest.OnNetworkListener<DataResponse> listener){
        TreeMap<String,String> params = new TreeMap<>() ;
        params.put("apiCode",RequestConfig.CODE_REPLY_TIE) ;
        params.put("id",id) ;
        params.put("content",content) ;
        params.put("userToken",MyApp.getUserToken()) ;
        requestData(context,params,new DataResponse(),listener);
    }

    /**
     * 删除帖子
     * @param context
     * @param id
     * @param listener
     */
    public static void delTie(Context context,String id,HttpRequest.OnNetworkListener<DataResponse> listener){
        TreeMap<String,String> params = new TreeMap<>() ;
        params.put("apiCode",RequestConfig.CODE_DEL_TIE) ;
        params.put("id",id) ;
        params.put("userToken",MyApp.getUserToken()) ;
        requestData(context,params,new DataResponse(),listener);
    }

    /**
     * 删除帖子回复
     * @param context
     * @param id
     * @param listener
     */
    public static void delTieReply(Context context,String id,HttpRequest.OnNetworkListener<DataResponse> listener){
        TreeMap<String,String> params = new TreeMap<>() ;
        params.put("apiCode",RequestConfig.CODE_DEL_TIE_REPLY) ;
        params.put("id",id) ;
        params.put("userToken",MyApp.getUserToken()) ;
        requestData(context,params,new DataResponse(),listener);
    }

    /**
     * 置顶帖子
     * @param context
     * @param id
     * @param listener
     */
    public static void topTie(Context context,String id,String type,HttpRequest.OnNetworkListener<DataResponse> listener){
        TreeMap<String,String> params = new TreeMap<>() ;
        params.put("apiCode",RequestConfig.CODE_TOP_TIE) ;
        params.put("id",id) ;
        params.put("type",type) ;
        params.put("userToken",MyApp.getUserToken()) ;
        requestData(context,params,new DataResponse(),listener);
    }

    /**
     * 获取消息通知详情
     * @param context
     * @param id
     * @param listener
     */
    public static void getNotifyDetail(Context context,String id,HttpRequest.OnNetworkListener<DataResponse> listener){
        TreeMap<String,String> params = new TreeMap<>() ;
        params.put("apiCode",RequestConfig.CODE_NOTIFY_DETAIL) ;
        params.put("id",id) ;
        params.put("userToken",MyApp.getUserToken()) ;
        requestData(context,params,new DataResponse<MsgDetailInfo>().init(MsgDetailInfo.class),listener);
    }

    /**
     * 获取消息通知的回复
     * @param context
     * @param id
     * @param page
     * @param listener
     */
    public static void getNotifyReply(Context context,String id,int page,HttpRequest.OnNetworkListener<DataResponse> listener){
        TreeMap<String,String> params = new TreeMap<>() ;
        params.put("apiCode",RequestConfig.CODE_NOTIFY_REPLY) ;
        params.put("id",id) ;
        params.put("page",page+"") ;
        params.put("size", ConstantUtil.PAGE_SIZE) ;
        params.put("userToken",MyApp.getUserToken()) ;
        requestData(context,params,new DataResponse<NotifyReplyInfo>().init(NotifyReplyInfo.class),listener);
    }

    /**
     * 回复通知消息
     * @param context
     * @param id
     * @param content
     * @param listener
     */
    public static void replyNotify(Context context,String id,String content,HttpRequest.OnNetworkListener<DataResponse> listener){
        TreeMap<String,String> params = new TreeMap<>() ;
        params.put("apiCode",RequestConfig.CODE_REPLY_NOTIFY) ;
        params.put("id",id) ;
        params.put("content",content) ;
        params.put("userToken",MyApp.getUserToken()) ;
        requestData(context,params,new DataResponse(),listener);
    }

    /**
     * 获取违规原因
     * @param context
     * @param listener
     */
    public static void getWeiGuiCause(Context context,HttpRequest.OnNetworkListener<DataResponse> listener){
        TreeMap<String,String> params = new TreeMap<>() ;
        params.put("apiCode",RequestConfig.CODE_GET_WEIGUI_CAUSE) ;
        params.put("userToken",MyApp.getUserToken()) ;
        requestData(context,params,new DataResponse<WGCauseInfo>().init(WGCauseInfo.class),listener);
    }

    /**
     * 处理帖子
     * @param context
     * @param id 帖子id
     * @param listener
     */
    public static void dealTie(Context context,String id,HttpRequest.OnNetworkListener<DataResponse> listener) {
        TreeMap<String, String> params = new TreeMap<>();
        params.put("apiCode", RequestConfig.CODE_DEAL_TIE);
        params.put("id", id);
        params.put("userToken", MyApp.getUserToken());
        requestData(context, params, new DataResponse(), listener);
    }

    /**
     * 处理帖子回复违规
     * @param context
     * @param id 帖子回复ID
     * @param listener
     */
    public static void dealTieReply(Context context,String id,HttpRequest.OnNetworkListener<DataResponse> listener){
        TreeMap<String,String> params = new TreeMap<>() ;
        params.put("apiCode",RequestConfig.CODE_DEAL_TIE_REPLY) ;
        params.put("id",id) ;
        params.put("userToken",MyApp.getUserToken()) ;
        requestData(context,params,new DataResponse(),listener);
    }

}
