package com.future.association.questionnaire.viewmodels;

import android.net.Uri;

import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.databinding.ActivityQuestionnaireWebBinding;
import com.future.association.questionnaire.QuestionnaireApi;
import com.future.association.questionnaire.models.QuestionDetail;
import com.future.association.questionnaire.views.QuestionnaireWebActivity;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.ToastUtils;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by chenyu on 2017/9/3.
 */

public class QuestionnaireWebViewModel {
    private final QuestionnaireWebActivity mActivity;
    private final ActivityQuestionnaireWebBinding mBinding;
    private final QuestionDetail mData;

    public QuestionnaireWebViewModel(QuestionnaireWebActivity activity, ActivityQuestionnaireWebBinding binding, QuestionDetail questionDetail) {
        this.mActivity = activity;
        this.mBinding = binding;
        this.mData = questionDetail;
    }

    private void initData() {
        //请求分享的数据
        QuestionnaireApi.getInstance().getWenjuanDetail(mActivity, MyApp.getUserToken(), mData.getId()).setListener(new HttpRequest.OnNetworkListener<QuestionDetail>() {
            @Override
            public void onSuccess(QuestionDetail questionDetail) {
                mActivity.dissmissLoadingDialog();
                showShare(questionDetail.getInfoBean());
            }

            @Override
            public void onFail(String message) {
                mActivity.dissmissLoadingDialog();
                ToastUtils.shortToast(mActivity,message==null?"请求超时":message);
            }
        }).start(new QuestionDetail());
    }

    public void onShare() {
        //点击分享按钮
        if (mData ==null) {
            mActivity.showLoadingDialog();
            initData();
        }else{
            showShare(mData);
        }
    }

//    private void showShare(QuestionDetail questionDetail) {
//        OnekeyShare oks = new OnekeyShare();
////关闭sso授权
//        oks.disableSSOWhenAuthorize();
//
//// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
//        oks.setTitle(questionDetail.getTitle());
//// titleUrl是标题的网络链接，QQ和QQ空间等使用
//        String showurl = questionDetail.getShowurl();
//        oks.setTitleUrl(showurl);
//// text是分享文本，所有平台都需要这个字段
//        oks.setText(questionDetail.getJianjie());
//// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
////oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//// url仅在微信（包括好友和朋友圈）中使用
////        showurl = showurl.replace("\\/", File.separator);
//        oks.setUrl(showurl);
//// comment是我对这条分享的评论，仅在人人网和QQ空间使用
////        oks.setComment("我是测试评论文本");
//// site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(mActivity.getString(R.string.app_name));
//// siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl(showurl);
//
//// 启动分享GUI
//        oks.show(mActivity);
//    }

    private void showShare(QuestionDetail questionDetail) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(questionDetail.getTitle());
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(questionDetail.getShowurl());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(questionDetail.getJianjie());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(Uri.parse("android:resource://com.future.association/"+R.mipmap.ic_launcher).getPath());
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(questionDetail.getShowurl());
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(mActivity.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(questionDetail.getShowurl());

        // 启动分享GUI
        oks.show(mActivity);
    }
}
