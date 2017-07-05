package com.future.association.community.view;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.future.association.R;
import com.future.association.community.adapter.NotifyDetailAdapter;
import com.future.association.community.base.BaseActivity;
import com.future.association.community.model.MsgDetailInfo;
import com.future.association.community.model.ReplyInfo;
import com.future.association.databinding.ActivityNotifyDetailBinding;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/4.
 */

public class NotifyDetailActivity extends BaseActivity<ActivityNotifyDetailBinding> {

    private NotifyDetailAdapter adapter;

    @Override
    public int setContentView() {
        return R.layout.activity_notify_detail;
    }

    @Override
    public void initView() {
        viewBinding.rclReply.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void initData() {
        ArrayList<ReplyInfo> replyInfos = new ArrayList<>() ;
        viewBinding.layoutTitle.setTitle("通知详情");
        viewBinding.setDetailInfo(new MsgDetailInfo("消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知",
                "2017-07-04 09:53","发布单位",
                "消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知"));
        adapter = new NotifyDetailAdapter(context,replyInfos);
        viewBinding.rclReply.setAdapter(adapter);
        replyInfos.add(new ReplyInfo("张三","v1","我说的是什么是什么是什么")) ;
        replyInfos.add(new ReplyInfo("张三","v1","我说的是什么是什么是什么")) ;
        replyInfos.add(new ReplyInfo("张三","v1","我说的是什么是什么是什么")) ;
        replyInfos.add(new ReplyInfo("张三","v1","我说的是什么是什么是什么")) ;
        replyInfos.add(new ReplyInfo("张三","v1","我说的是什么是什么是什么")) ;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void initListener() {
        viewBinding.layoutTitle.setViewClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
