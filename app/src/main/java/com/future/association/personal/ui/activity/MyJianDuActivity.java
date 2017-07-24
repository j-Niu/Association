package com.future.association.personal.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.future.association.R;
import com.future.association.personal.adapter.JianDuAdapter;
import com.future.association.personal.entity.BeanJiandu;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.StatusUtils;

import java.util.ArrayList;
import java.util.List;

public class MyJianDuActivity extends BaseActivity {

    private ListView lvMyJiandu;
    private JianDuAdapter jianDuAdapter;
    private List<BeanJiandu> jianduList = new ArrayList<>();
private String imgUrl="http://img2.3lian.com/2014/f2/37/d/40.jpg";
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        StatusUtils.setStatusbarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_my_jian_du);
    }

    @Override
    protected void initView() {
        setTitle("我的监督");
        setTitleLeft(R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lvMyJiandu = (ListView) findViewById(R.id.lvMyJiandu);
        BeanJiandu beanJiandu = null;
        for (int i = 0; i < 10; i++) {
            beanJiandu = new BeanJiandu(imgUrl, getString(R.string.mystr1), getString(R.string.mystr2), getString(R.string.mystr3)
                    , getString(R.string.mystr4), getString(R.string.mystr5));
            jianduList.add(beanJiandu);
        }
        if (jianDuAdapter == null) {
            jianDuAdapter = new JianDuAdapter(this, jianduList, getLayoutInflater());
            lvMyJiandu.setAdapter(jianDuAdapter);
        } else {
            jianDuAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
