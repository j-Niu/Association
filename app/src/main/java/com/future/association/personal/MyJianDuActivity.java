package com.future.association.personal;

import android.os.Bundle;
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
    private String imgUrl = "http://imgsrc.baidu.com/image/c0%3Dshijue%2C0%2C0%2C245%2C40/sign=7700a4f5376d55fbd1cb7e65054b253f/023b5bb5c9ea15ce67442822bc003af33a87b277.jpg";

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        StatusUtils.setStatusbarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_my_jian_du);
    }

    @Override
    protected void initView() {
        lvMyJiandu = (ListView) findViewById(R.id.lvMyJiandu);
        BeanJiandu beanJiandu = null;
        for (int i = 0; i < 10; i++) {
            beanJiandu = new BeanJiandu(imgUrl, getString(R.string.mystr1), getString(R.string.mystr2), getString(R.string.mystr3)
                    , getString(R.string.mystr4), getString(R.string.mystr5));
            jianduList.add(beanJiandu);
        }
        if (jianDuAdapter == null) {
            jianDuAdapter = new JianDuAdapter(this, null, getLayoutInflater());
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
