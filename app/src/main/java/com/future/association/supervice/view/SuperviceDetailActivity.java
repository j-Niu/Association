package com.future.association.supervice.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.future.association.R;
import com.future.association.databinding.ActivitySuperviceDetailBinding;
import com.future.association.supervice.viewmodel.SuperviceDetailViewModel;
import com.future.baselib.activity.BaseActivity;

/**
 * Created by rain on 2017/7/7.
 */

public class SuperviceDetailActivity extends BaseActivity {
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        String id = getIntent().getStringExtra("id");
        ActivitySuperviceDetailBinding detailBinding = DataBindingUtil.setContentView(this, R.layout.activity_supervice_detail);
        detailBinding.setSuperviceDetailViewModel(new SuperviceDetailViewModel(this,detailBinding,id));
    }

    @Override
    protected void initView() {
        setTitle(R.string.superice_detail);
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
