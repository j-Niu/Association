package com.future.association.personal;

import android.os.Bundle;

import com.future.association.R;
import com.future.baselib.activity.BaseActivity;

public class MyLevelActivity extends BaseActivity {

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_level);
    }

    @Override
    protected void initView() {
//        setTitle("我的等级");
//        setTitleLeft(R.drawable.ic_back, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
