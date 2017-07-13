package com.future.association.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.future.association.R;
import com.future.association.personal.adapter.TongzhiAdapter;
import com.future.association.personal.entity.BeanTongzhi;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.StatusUtils;

import java.util.ArrayList;
import java.util.List;

public class MyTongZhi extends BaseActivity {
    private ListView lvMyTongzhi;
    private List<BeanTongzhi> tongzhiList = new ArrayList<>();
    private TongzhiAdapter tongzhiAdapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        StatusUtils.setStatusbarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_my_tongzhi);
    }

    @Override
    protected void initView() {
        setTitle("我的通知");
        setTitleLeft(R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lvMyTongzhi = (ListView) findViewById(R.id.lvMyTongzhi);
        BeanTongzhi tongzhi;
        for (int i = 0; i < 10; i++) {
            tongzhi = new BeanTongzhi(getString(R.string.myTZ1),
                    getString(R.string.myTZ2));
            tongzhiList.add(tongzhi);
        }
        if (tongzhiAdapter == null) {
            tongzhiAdapter = new TongzhiAdapter(this, tongzhiList, getLayoutInflater());
            lvMyTongzhi.setAdapter(tongzhiAdapter);
        } else {
            tongzhiAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
