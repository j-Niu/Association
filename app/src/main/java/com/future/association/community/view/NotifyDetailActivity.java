package com.future.association.community.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.future.association.R;
import com.future.association.community.adapter.NotifyDetailAdapter;
import com.future.association.community.base.BaseActivity;
import com.future.association.community.contract.NotifyDetailContract;
import com.future.association.community.custom.CustomRecyclerView;
import com.future.association.community.model.MsgDetailInfo;
import com.future.association.community.model.MsgNotifyInfo;
import com.future.association.community.model.NotifyReplyInfo;
import com.future.association.community.presenter.NotifyDetailPresenter;
import com.future.association.databinding.ActivityNotifyDetailBinding;
import com.future.association.databinding.LayoutNotifyReplyHeadBinding;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/4.
 */

public class NotifyDetailActivity extends BaseActivity<ActivityNotifyDetailBinding> implements NotifyDetailContract.IView {

    private NotifyDetailAdapter adapter;
    private ArrayList<NotifyReplyInfo> replyInfos;
    private NotifyDetailContract.IPresenter presenter;
    private LinearLayoutManager linearLayoutManager;
    private MsgNotifyInfo notifyInfo;
    private View mHeadView;
    private LayoutNotifyReplyHeadBinding headBinding;
    private int currentPage = 1 ;

    @Override
    public int setContentView() {
        return R.layout.activity_notify_detail;
    }

    @Override
    public void initView() {
        mHeadView = View.inflate(context, R.layout.layout_notify_reply_head,null);
        headBinding = DataBindingUtil.bind(mHeadView);
    }

    @Override
    public void initData() {
        notifyInfo = getIntent().getParcelableExtra("notifyInfo");
        replyInfos = new ArrayList<>();
        viewBinding.layoutTitle.setTitle("通知详情");
        adapter = new NotifyDetailAdapter(context, replyInfos);
        adapter.setmHeadView(mHeadView);
        viewBinding.rclReply.setAdapter(adapter);
        presenter = new NotifyDetailPresenter(this, context);
        presenter.getData(currentPage);
        presenter.getNotifyDetail();
    }

    @Override
    public void initListener() {
        viewBinding.rclReply.setLoadMoreListener(new CustomRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore(int currentPage) {
                NotifyDetailActivity.this.currentPage = currentPage ;
                presenter.getData(currentPage);
            }
        });
        viewBinding.layoutTitle.setViewClickListener(this);
        viewBinding.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_send://发表评论
                presenter.sendTalk();
        }
    }

    @Override
    public void setData(ArrayList<NotifyReplyInfo> replyInfos) {
        viewBinding.rclReply.setLoading(false);
        if(replyInfos != null && replyInfos.size() > 0){
            if(currentPage == 1){
                viewBinding.rclReply.resetPage();
                this.replyInfos.clear();
            }
            this.replyInfos.addAll(replyInfos);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setNotifyDetail(MsgDetailInfo detailInfo) {
        if (detailInfo != null) {
            headBinding.setDetailInfo(detailInfo);
        }
    }

    @Override
    public String getTalkContent() {
        return viewBinding.getTalkContent();
    }

    @Override
    public String getNofityId() {
        return notifyInfo.getId();
    }

    @Override
    public void talkReult( NotifyReplyInfo replyInfo) {
        viewBinding.setTalkContent("");
//        this.replyInfos.add(replyInfo);
        adapter.notifyDataSetChanged();
        currentPage = 1 ;
        presenter.getData(currentPage);
//        viewBinding.rclReply.scrollToPosition(adapter.getItemCount() - 1);//列表滑到最后一行
        showShortToast("评论成功");
    }

    @Override
    public void showMsg(String msg) {
        showShortToast(msg);
    }
}
