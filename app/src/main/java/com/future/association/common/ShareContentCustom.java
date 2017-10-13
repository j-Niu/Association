package com.future.association.common;

import android.content.Context;

import com.future.association.R;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;


/**
 * Author: xzp
 * Date: 2017/6/2
 * 不同分享平台差异化配置
 */
public class ShareContentCustom implements ShareContentCustomizeCallback {

    private Context context;
    private String shareContent;
    private String shareUrl;
    private String image;
    private String shareTitle;

    public ShareContentCustom(Context context, String shareContent, String shareUrl, String
            image, String shareTitle) {
        this.context = context;
        this.shareContent = shareContent;
        this.shareUrl = shareUrl;
        this.image = image;
        this.shareTitle = shareTitle;
    }

    @Override
    public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
        if (SinaWeibo.NAME.equals(platform.getName())) {
            // text是分享文本，所有平台都需要这个字段
            paramsToShare.setText(shareContent + shareUrl);
            //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
            paramsToShare.setImageUrl(image);
        } else if (Wechat.NAME.equals(platform.getName()) || WechatMoments.NAME.equals(platform
                .getName())) {
            paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
            // text是分享文本，所有平台都需要这个字段
            paramsToShare.setText(shareContent);
            //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
            paramsToShare.setImageUrl(image);
            // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
            paramsToShare.setTitle(shareTitle);
            // url仅在微信（包括好友和朋友圈）中使用
            paramsToShare.setUrl(shareUrl);
        } else {
            // text是分享文本，所有平台都需要这个字段
            paramsToShare.setText(shareContent);
            // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
            paramsToShare.setTitleUrl(shareUrl);
            // site是分享此内容的网站名称，仅在QQ空间使用
            paramsToShare.setSite(context.getString(R.string.app_name));
            //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
            paramsToShare.setImageUrl(image);
            // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
            paramsToShare.setTitle(shareTitle);
            // siteUrl是分享此内容的网站地址，仅在QQ空间使用
            paramsToShare.setSiteUrl(shareUrl);
            // url仅在微信（包括好友和朋友圈）中使用
            paramsToShare.setUrl(shareUrl);
        }
    }
}
