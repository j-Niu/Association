package com.future.association.community.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.future.association.R;
import com.future.association.community.adapter.GridViewAdapter;
import com.future.association.community.base.BaseActivity;
import com.future.association.community.model.PlateInfo;
import com.future.association.community.utils.ActivityUtils;
import com.future.association.databinding.ActivityAllPlateBinding;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/11.
 */

public class AllPlateActivity extends BaseActivity<ActivityAllPlateBinding> {

    private GridViewAdapter adapter;

    @Override
    public int setContentView() {
        return R.layout.activity_all_plate;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

        ArrayList<PlateInfo> plateInfos = getIntent().getParcelableArrayListExtra("plateInfos");

        viewBinding.layoutTitle.setTitle("社区");
        adapter = new GridViewAdapter(context);
        adapter.setLimitCount(Integer.MAX_VALUE);
        viewBinding.gv.setAdapter(adapter);
        adapter.datas.addAll(plateInfos);
    }

    @Override
    public void initListener() {
        viewBinding.layoutTitle.setViewClickListener(this);
        viewBinding.gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlateInfo plateInfo = adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable("plateInfo", plateInfo);
                ActivityUtils.startActivityIntent(context, TieListActivity.class, bundle);
            }
        });
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
