package com.future.association.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.future.association.R;
import com.future.association.personal.adapter.HuiYingAdapter;
import com.future.association.personal.entity.BeanMyResponse;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.StatusUtils;

import java.util.ArrayList;
import java.util.List;

public class MyResponseActivity extends BaseActivity {

    private ListView lvMyResponse;
    private List<BeanMyResponse> responseList = new ArrayList<>();
    private HuiYingAdapter huiYingAdapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        StatusUtils.setStatusbarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_my_response);
    }

    @Override
    protected void initView() {
        setTitle("我的回应");
        setTitleLeft(R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lvMyResponse = (ListView) findViewById(R.id.lvMyResponse);
        BeanMyResponse response;
        for (int i = 0; i < 10; i++) {
            response = new BeanMyResponse(getString(R.string.myHY1),
                    getString(R.string.myHY2), getString(R.string.myHY3));
            responseList.add(response);
        }
        if (huiYingAdapter == null) {
            huiYingAdapter = new HuiYingAdapter(this, responseList, getLayoutInflater());
            lvMyResponse.setAdapter(huiYingAdapter);
        } else {
            huiYingAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
