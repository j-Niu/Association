package com.future.association.community.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.future.association.R;
import com.future.association.community.adapter.NotifyListAdapter;
import com.future.association.community.contract.NotifyListContract;
import com.future.association.community.model.MsgNotifyInfo;
import com.future.association.community.presenter.NotifyListPresent;
import com.future.association.community.utils.ActivityUtils;
import com.future.association.databinding.ActivityNotifyListBinding;
import com.future.baselib.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class NotifyListActivity extends BaseActivity implements NotifyListContract.IView {

    private NotifyListPresent presenter;
    private ArrayList<MsgNotifyInfo> notifyInfos = new ArrayList<>();
    private NotifyListAdapter notifyAdapter;
    private ActivityNotifyListBinding viewBinding;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        viewBinding = DataBindingUtil.setContentView(this,R.layout.activity_notify_list);
    }

    @Override
    protected void initView() {
         setTitle("通知");
         setTitleLeft(R.drawable.ic_back, new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 finish();
             }
         });
        notifyAdapter = new NotifyListAdapter(R.layout.item_msg_notify, notifyInfos);
        viewBinding.rv.setAdapter(notifyAdapter);
        viewBinding.rv.setLayoutManager(new LinearLayoutManager(this));
        viewBinding.rv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id", notifyAdapter.getItem(position).getId());
                ActivityUtils.startActivityIntent(NotifyListActivity.this, NotifyDetailActivity.class, bundle);
            }
        });
        notifyAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                presenter.getMoreData();
            }
        },viewBinding.rv);
        presenter = new NotifyListPresent(this,this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getData();
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public void setData(List<MsgNotifyInfo> notifyInfos) {
        if (notifyInfos != null && notifyInfos.size() > 0) {
            this.notifyInfos.clear();
            this.notifyInfos.addAll(notifyInfos);
            notifyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setMoreData(List<MsgNotifyInfo> notifyInfos) {
        if (notifyInfos != null && notifyInfos.size() > 0) {
            this.notifyInfos.addAll(notifyInfos);
            notifyAdapter.notifyDataSetChanged();
            notifyAdapter.loadMoreComplete();
        }else {
            notifyAdapter.loadMoreEnd();
        }
    }

    @Override
    public void showMsg(String msg) {
        toast(msg);
    }
}
