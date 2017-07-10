package com.future.association.supervice;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.future.association.R;
import com.future.association.databinding.ActivitySuperviceDetailBinding;
import com.future.baselib.activity.BaseActivity;

/**
 * Created by rain on 2017/7/7.
 */

public class SuperviceDetailActivity extends BaseActivity {
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        ActivitySuperviceDetailBinding detailBinding = DataBindingUtil.setContentView(this, R.layout.activity_supervice_detail);
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
