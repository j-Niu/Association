package com.future.association.community.view;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.future.association.R;
import com.future.association.community.adapter.TieReplyAdapter;
import com.future.association.community.base.BaseActivity;
import com.future.association.community.model.TieReplyInfo;
import com.future.association.community.utils.ActivityUtils;
import com.future.association.community.utils.DialogUtils;
import com.future.association.community.utils.Res;
import com.future.association.databinding.ActivityTieDetailBinding;
import com.future.association.databinding.PopupTieBinding;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/4.
 */

public class TieDetailActivity extends BaseActivity<ActivityTieDetailBinding> {
    private PopupWindow popupWindow;
    private View popupView;
    private PopupTieBinding popupTieBinding;

    @Override
    public int setContentView() {
        return R.layout.activity_tie_detail;
    }

    @Override
    public void initView() {
        viewBinding.layoutTitle.ivTitleRightImg.
                setImageDrawable(Res.getDrawableRes(R.drawable.ic_more,context));
        viewBinding.rclReply.setLayoutManager(new LinearLayoutManager(context));
        popupView = inflater.inflate(R.layout.popup_tie,null);
        popupTieBinding = DataBindingUtil.bind(popupView);
    }

    @Override
    public void initData() {
        ArrayList<TieReplyInfo> tieReplyInfos = new ArrayList<>() ;
        viewBinding.layoutTitle.setTitle("帖子详情");
        TieReplyAdapter adapter = new TieReplyAdapter(context,tieReplyInfos) ;
        viewBinding.rclReply.setAdapter(adapter);
        tieReplyInfos.add(new TieReplyInfo("回复人","v1","浙江省杭州市西湖区","评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文")) ;
        tieReplyInfos.add(new TieReplyInfo("回复人","v1","浙江省杭州市西湖区","评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文")) ;
        tieReplyInfos.add(new TieReplyInfo("回复人","v1","浙江省杭州市西湖区","评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文")) ;
        tieReplyInfos.add(new TieReplyInfo("回复人","v1","浙江省杭州市西湖区","评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文")) ;
        tieReplyInfos.add(new TieReplyInfo("回复人","v1","浙江省杭州市西湖区","评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文")) ;
        tieReplyInfos.add(new TieReplyInfo("回复人","v1","浙江省杭州市西湖区","评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文")) ;
        tieReplyInfos.add(new TieReplyInfo("回复人","v1","浙江省杭州市西湖区","评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文")) ;
        tieReplyInfos.add(new TieReplyInfo("回复人","v1","浙江省杭州市西湖区","评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文")) ;
        adapter.notifyDataSetChanged();
    }

    private void showPopupWindow() {
        popupWindow = new PopupWindow(this);
        popupWindow.setContentView(popupView);
        popupWindow.setFocusable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(viewBinding.layoutTitle.ivTitleRightImg,20,-20);
    }

    @Override
    public void initListener() {
        viewBinding.layoutTitle.setViewClickListener(this);
        popupTieBinding.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_title_right_img:
                showPopupWindow();
                break;
            case R.id.tv_top:
                popupWindow.dismiss();
                break;
            case R.id.tv_del:
                popupWindow.dismiss();
                DialogUtils.showDialog4View(context, R.layout.dialog_sure, R.id.tv_positive, R.id.tv_nagtive,"删除该帖子？", new DialogUtils.EditContentDialogListener() {
                    @Override
                    public void positive(String content) {

                    }

                    @Override
                    public void nagtive() {

                    }
                });
                break;
            case R.id.tv_weigui:
                popupWindow.dismiss();
                ActivityUtils.startActivityIntent(context,WeiGuiActivity.class);
                break;
        }
    }
}
