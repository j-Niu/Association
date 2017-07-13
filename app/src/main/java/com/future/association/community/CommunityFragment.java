package com.future.association.community;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.future.association.R;
import com.future.association.community.adapter.GridViewAdapter;
import com.future.association.community.adapter.MsgNotifyAdapter;
import com.future.association.community.base.EndlessRecyclerOnScrollListener;
import com.future.association.community.contract.CommunityContract;
import com.future.association.community.model.MsgNotifyInfo;
import com.future.association.community.model.PlateInfo;
import com.future.association.community.presenter.CommunityPresenter;
import com.future.association.community.utils.ActivityUtils;
import com.future.association.community.view.AllPlateActivity;
import com.future.association.community.view.TieListActivity;
import com.future.association.community.view.NotifyDetailActivity;
import com.future.association.databinding.FragmentCommunityBinding;

import java.util.ArrayList;

/**
 * 社区Fragment
 * A simple {@link Fragment} subclass.
 */
public class CommunityFragment extends Fragment implements CommunityContract.IView {


    private FragmentCommunityBinding viewBinding;
    private MsgNotifyAdapter notifyAdapter;
    private ArrayList<MsgNotifyInfo> notifyInfos;
    private CommunityContract.IPresenter presenter;
    private LinearLayoutManager linearLayoutManager;
    private GridViewAdapter adapter;

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
        linearLayoutManager = new LinearLayoutManager(getContext());
        viewBinding.rcvMsg.setLayoutManager(linearLayoutManager);
    }

    private void initData(Bundle arguments) {
        notifyInfos = new ArrayList<>();
        viewBinding.layoutTitle.setTitle("社区");

        adapter = new GridViewAdapter(getContext());
        viewBinding.gv.setAdapter(adapter);

        notifyAdapter = new MsgNotifyAdapter(getContext(), notifyInfos);
        viewBinding.rcvMsg.setAdapter(notifyAdapter);

        presenter = new CommunityPresenter(this, getContext());
        presenter.getPlateList();
        presenter.getData(1);
    }

    public void initListener() {
        viewBinding.rcvMsg.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                presenter.getData(currentPage);
            }
        });
        viewBinding.gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlateInfo plateInfo = adapter.getItem(position);
                Bundle bundle = new Bundle();
                Class target = null;
                if (position < 5) {
                    target = TieListActivity.class;
                    bundle.putParcelable("plateInfo", plateInfo);
                } else {//更多
                    target = AllPlateActivity.class;
                }
                bundle.putParcelableArrayList("plateInfos", adapter.datas);
                ActivityUtils.startActivityIntent(getContext(), target, bundle);
            }
        });
        notifyAdapter.setItemClickListener(new MsgNotifyAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("notifyInfo", notifyAdapter.getItem(position));
                ActivityUtils.startActivityIntent(getContext(), NotifyDetailActivity.class, bundle);
            }
        });
    }

    @Override
    public void setData(ArrayList<MsgNotifyInfo> notifyInfos) {
        this.notifyInfos.addAll(notifyInfos);
        notifyAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPlateList(ArrayList<PlateInfo> plateInfos) {
        adapter.datas.addAll(plateInfos);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

}
