package com.future.association.personal;

import android.os.Bundle;

import com.future.association.R;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.StatusUtils;

public class MyResponseActivity extends BaseActivity {

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        StatusUtils.setStatusbarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_my_response);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
