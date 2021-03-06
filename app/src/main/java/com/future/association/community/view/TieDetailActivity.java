package com.future.association.community.view;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.future.association.R;
import com.future.association.common.utils.GlideUtils;
import com.future.association.common.MyApp;
import com.future.association.community.adapter.TieReplyAdapter;
import com.future.association.community.base.BaseActivity;
import com.future.association.community.contract.TieDetailContract;
import com.future.association.community.custom.CustomRecyclerView;
import com.future.association.community.model.TieDetailInfo;
import com.future.association.community.model.TieReplyInfo;
import com.future.association.community.presenter.TieDetailPresenter;
import com.future.association.community.utils.ActivityUtils;
import com.future.association.community.utils.DialogUtils;
import com.future.association.community.utils.Res;
import com.future.association.community.utils.StringUtils;
import com.future.association.databinding.ActivityTieDetailBinding;
import com.future.association.databinding.LayoutTieReplyHeadBinding;
import com.future.association.databinding.PopupTieBinding;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/4.
 */

public class TieDetailActivity extends BaseActivity<ActivityTieDetailBinding> implements TieDetailContract.IView {
    private PopupWindow popupWindow;
    private View popupView;
    private PopupTieBinding popupTieBinding;
    private ArrayList<TieReplyInfo> tieReplyInfos;
    private TieReplyAdapter adapter;
    private TieDetailContract.IPresenter presenter;
    private int delReplyPosition;
    private LayoutTieReplyHeadBinding headBinding;
    private View mHeadView;
    private int currentPage = 1;
    private String jifen;

    private String tieziId;
    private int flag;
    private String huifu_jf;

    @Override
    public int setContentView() {
        return R.layout.activity_tie_detail;
    }

    @Override
    public void initView() {
        popupView = inflater.inflate(R.layout.popup_tie, null);
        popupTieBinding = DataBindingUtil.bind(popupView);

        mHeadView = View.inflate(context, R.layout.layout_tie_reply_head, null);
        headBinding = DataBindingUtil.bind(mHeadView);
    }

    @Override
    public void initData() {
        flag = getIntent().getFlags();
        String type = getIntent().getStringExtra("type") ;
        jifen = getIntent().getStringExtra("jifen");
        huifu_jf = getIntent().getStringExtra("huifu_jf");
        tieziId =getIntent().getStringExtra("id");
        if ("1".equals(type)) {
            popupTieBinding.setIsTop("取消置顶");
        } else {
            popupTieBinding.setIsTop("置顶");
        }
        tieReplyInfos = new ArrayList<>();
        viewBinding.layoutTitle.setTitle("帖子详情");
        adapter = new TieReplyAdapter(context, tieReplyInfos);
        adapter.setmHeadView(mHeadView);
        viewBinding.rclReply.setAdapter(adapter);
        presenter = new TieDetailPresenter(this, context);
        presenter.getData(currentPage);
        presenter.getTieDetail();
    }

    private void showPopupWindow() {
        if (popupWindow == null) {
            popupWindow = new PopupWindow(this);
            popupWindow.setContentView(popupView);

            if(isSelfTie){
                popupView.findViewById(R.id.tv_top).setVisibility(View.GONE);
                popupView.findViewById(R.id.tv_weigui).setVisibility(View.GONE);
            }

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
        }
        popupWindow.showAsDropDown(viewBinding.layoutTitle.ivTitleRightImg, 20, -20);

    }

    @Override
    public void initListener() {
        viewBinding.rclReply.setLoadMoreListener(new CustomRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore(int currentPage) {
                TieDetailActivity.this.currentPage = currentPage;
                presenter.getData(currentPage);
            }
        });
        viewBinding.layoutTitle.setViewClickListener(this);
        popupTieBinding.setClickListener(this);
        viewBinding.setClickListener(this);
        adapter.setCallback(new TieReplyAdapter.Callback() {
            @Override
            public void delReply(int position) {
                delReplyPosition = position;
                presenter.delTieReply();
            }

            @Override
            public void weigui(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("tieId", tieziId);
                bundle.putString("id", adapter.tieReplyInfos.get(position).getId());
                ActivityUtils.startActivityIntent(context, WeiGuiActivity.class, bundle);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_title_right_img:
                if(isSelfTie || MyApp.isAdministrator())
                    showPopupWindow();
                break;
            case R.id.tv_top:
                popupWindow.dismiss();
                presenter.tieTop();
                break;
            case R.id.tv_del:
                popupWindow.dismiss();
                DialogUtils.showDialog4View(context, R.layout.dialog_sure, R.id.tv_positive, R.id.tv_nagtive, "删除该帖子？", new DialogUtils.EditContentDialogListener() {
                    @Override
                    public void positive(String content) {
                        presenter.delTie();
                    }

                    @Override
                    public void nagtive() {

                    }
                });
                break;
            case R.id.tv_weigui:
                popupWindow.dismiss();
                Bundle bundle = new Bundle();
                bundle.putString("tieId", tieziId);
                bundle.putString("id", "");
                ActivityUtils.startActivityIntent(context, WeiGuiActivity.class, bundle);
                break;
            case R.id.tv_send:
                if (StringUtils.stringIsInteger(jifen) < StringUtils.stringIsInteger(huifu_jf)) {
                    showShortToast("积分不够不能回复");
                    return;
                }
                presenter.sendReply();//发送回复
                break;
        }
    }

    @Override
    public void setData(ArrayList<TieReplyInfo> replyInfos) {
        viewBinding.rclReply.setLoading(false);
        if (replyInfos != null && replyInfos.size() > 0) {
            if (currentPage == 1) {
                viewBinding.rclReply.setPage(1);
                tieReplyInfos.clear();
            }
            tieReplyInfos.addAll(replyInfos);
            adapter.notifyDataSetChanged();
        } else {
            viewBinding.rclReply.setPage(currentPage - 1);
        }
    }

    @Override
    public String getReplyContent() {
        return viewBinding.getReplyContent();
    }

    private boolean isSelfTie;
    @Override
    public void setTieDetail(TieDetailInfo detailInfo) {
        if (detailInfo != null) {
            headBinding.setTieDetailInfo(detailInfo);

            isSelfTie = detailInfo.getUid().equals(MyApp.userId);
            //自己发的或者管理员
            if(isSelfTie || MyApp.isAdministrator()){
                viewBinding.layoutTitle.ivTitleRightImg.
                        setImageDrawable(Res.getDrawableRes(R.drawable.ic_more, context));
            }

            Glide.with(context)
                    .load(detailInfo.getAvatar_url())
                    .apply(GlideUtils.defaultImg())
                    .into(headBinding.civHead);
        }
    }

    @Override
    public void replyResult(TieReplyInfo replyInfo) {
        viewBinding.setReplyContent("");
        adapter.notifyDataSetChanged();
        currentPage = 1;
        presenter.getData(currentPage);
        showShortToast("评论成功");
    }

    @Override
    public String getTieId() {
        return tieziId;
    }

    @Override
    public String getTieReplyId() {
        return adapter.tieReplyInfos.get(delReplyPosition).getId();
    }

    @Override
    public void delTieResult(boolean result) {
        if (result) {
            showShortToast("删除帖子成功");
            finish();
        }
    }

    @Override
    public void delTieReplyResult(boolean result) {
        if (result) {
            adapter.notifyItemRemoved(delReplyPosition + 1);
            adapter.tieReplyInfos.remove(delReplyPosition);
            showShortToast("删除回复成功");
        }
    }

    @Override
    public void topTieResult(boolean result) {
        if (result) {
            showShortToast("操作成功");
            TieDetailInfo detailInfo = headBinding.getTieDetailInfo();
            if ("1".equals(getTieType())) {
                popupTieBinding.setIsTop("置顶");
                detailInfo.setType("2");
            } else {
                popupTieBinding.setIsTop("取消置顶");
                detailInfo.setType("1");
            }
            headBinding.setTieDetailInfo(detailInfo);
        }
    }

    @Override
    public void showMsg(String msg) {
        showShortToast(msg);
    }

    @Override
    public String getTieType() {
        return headBinding.getTieDetailInfo().getType();
    }
}
