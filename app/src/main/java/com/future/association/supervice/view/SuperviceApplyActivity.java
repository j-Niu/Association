package com.future.association.supervice.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.future.association.R;
import com.future.baselib.activity.BaseActivity;

/**
 * Created by rain on 2017/7/7.
 */

public class SuperviceApplyActivity extends BaseActivity {
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        DataBindingUtil.setContentView(this, R.layout.activity_supervice_apply);
    }

    @Override
    protected void initView() {
        setTitle(R.string.superice_publish);
        setTitleLeft(R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
