package com.future.association.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.future.association.R;
import com.future.association.personal.adapter.WenjuanAdapter;
import com.future.association.personal.entity.BeanWenJuan;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.StatusUtils;

import java.util.ArrayList;
import java.util.List;

public class MyWenJuanActivity extends BaseActivity {

    private ListView lvMyWenjuan;
    private WenjuanAdapter wenjuanAdapter;
    private List<BeanWenJuan> wenJuanList = new ArrayList<>();

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        StatusUtils.setStatusbarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_my_wen_juan);
    }

    @Override
    protected void initView() {
        setTitle("我的问卷");
        setTitleLeft(R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lvMyWenjuan = (ListView) findViewById(R.id.lvMyWenjuan);
        BeanWenJuan wenJuan;
        for (int i = 0; i < 3; i++) {
            wenJuan = new BeanWenJuan(getString(R.string.myWJ1), getString(R.string.myWJ2)
                    , getString(R.string.myWJ3), 0);
            wenJuanList.add(wenJuan);
        }
        for (int i = 0; i < 3; i++) {
            wenJuan = new BeanWenJuan(getString(R.string.myWJ1), getString(R.string.myWJ2)
                    , getString(R.string.myWJ3), 1);
            wenJuanList.add(wenJuan);
        }
        for (int i = 0; i < 3; i++) {
            wenJuan = new BeanWenJuan(getString(R.string.myWJ1), getString(R.string.myWJ2)
                    , getString(R.string.myWJ3), 2);
            wenJuanList.add(wenJuan);
        }
        if (wenjuanAdapter == null) {
            wenjuanAdapter = new WenjuanAdapter(this, wenJuanList, getLayoutInflater());
            lvMyWenjuan.setAdapter(wenjuanAdapter);
        } else {
            wenjuanAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
