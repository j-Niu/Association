package com.future.association.community;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.future.association.R;
import com.future.association.community.adapter.GridViewAdapter;
import com.future.association.community.adapter.MsgNotifyAdapter;
import com.future.association.community.model.MsgNotifyInfo;
import com.future.association.community.utils.ActivityUtils;
import com.future.association.community.view.BannerActivity;
import com.future.association.community.view.NotifyDetailActivity;
import com.future.association.databinding.FragmentCommunityBinding;

import java.util.ArrayList;

/**
 * 社区Fragment
 * A simple {@link Fragment} subclass.
 */
public class CommunityFragment extends Fragment {


    private FragmentCommunityBinding viewBinding;
    private MsgNotifyAdapter notifyAdapter;

    public CommunityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_community, container, false);
        viewBinding = DataBindingUtil.bind(contentView);
        return contentView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData(getArguments());
        initListener();
    }

    private void initView() {
        viewBinding.layoutTitle.ivBack.setVisibility(View.GONE);
        viewBinding.rcvMsg.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initData(Bundle arguments) {
        ArrayList<MsgNotifyInfo> notifyInfos = new ArrayList<>() ;
        viewBinding.layoutTitle.setTitle("社区");
        GridViewAdapter adapter = new GridViewAdapter(getContext()) ;
        viewBinding.gv.setAdapter(adapter);
        notifyAdapter = new MsgNotifyAdapter(getContext(),notifyInfos);
        viewBinding.rcvMsg.setAdapter(notifyAdapter);
        notifyInfos.add(new MsgNotifyInfo("1","消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知","发布来源",7)) ;
        notifyInfos.add(new MsgNotifyInfo("1","消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知","发布来源",7)) ;
        notifyInfos.add(new MsgNotifyInfo("1","消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知","发布来源",7)) ;
        notifyInfos.add(new MsgNotifyInfo("1","消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知","发布来源",7)) ;
        notifyInfos.add(new MsgNotifyInfo("1","消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知","发布来源",7)) ;
        notifyInfos.add(new MsgNotifyInfo("1","消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知","发布来源",7)) ;
        notifyInfos.add(new MsgNotifyInfo("1","消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知","发布来源",7)) ;
        notifyInfos.add(new MsgNotifyInfo("1","消息通知消息通知消息通知消息通知消息通知消息通知消息通知消息通知","发布来源",7)) ;
        notifyAdapter.notifyDataSetChanged();
    }
    public void initListener() {
        viewBinding.gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActivityUtils.startActivityIntent(getContext(),BannerActivity.class);
            }
        });
        notifyAdapter.setItemClickListener(new MsgNotifyAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position) {
                ActivityUtils.startActivityIntent(getContext(),NotifyDetailActivity.class);
            }
        });
    }

}
