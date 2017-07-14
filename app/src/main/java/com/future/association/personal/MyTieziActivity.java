package com.future.association.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.future.association.R;
import com.future.association.personal.adapter.TieziAdapter;
import com.future.association.personal.entity.BeanMyTiezi;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.StatusUtils;

import java.util.ArrayList;
import java.util.List;

public class MyTieziActivity extends BaseActivity {
    private ListView lvMyTiezi;
    private List<BeanMyTiezi> tieziList = new ArrayList<>();
    private TieziAdapter tieziAdapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        StatusUtils.setStatusbarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_my_tiezi);
    }

    @Override
    protected void initView() {
        setTitle("我的帖子");
        setTitleLeft(R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lvMyTiezi = (ListView) findViewById(R.id.lvMyTiezi);
        BeanMyTiezi myTiezi;
        for (int i = 0; i < 10; i++) {
            myTiezi = new BeanMyTiezi(getString(R.string.myHY1),
                    getString(R.string.myHY2), getString(R.string.myHY4), getString(R.string.myHY3));
            tieziList.add(myTiezi);
        }
        if (tieziAdapter == null) {
            tieziAdapter = new TieziAdapter(this, tieziList, getLayoutInflater());
            lvMyTiezi.setAdapter(tieziAdapter);
        } else {
            tieziAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
