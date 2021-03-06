package com.future.association.supervice.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.future.association.R;
import com.future.association.databinding.ActivitySuperviceApplyBinding;
import com.future.association.supervice.viewmodel.SuperviceApplyViewModel;
import com.future.baselib.activity.BaseActivity;

/**
 * Created by rain on 2017/7/7.
 */

public class SuperviceApplyActivity extends BaseActivity {

    private SuperviceApplyViewModel mViewMdel;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        String type = getIntent().getStringExtra("type");
        ActivitySuperviceApplyBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_supervice_apply);
        mViewMdel = new SuperviceApplyViewModel(this, binding, type);
        binding.setSuperviceApplyViewMdel(mViewMdel);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mViewMdel.onActivityResult(requestCode,resultCode,data);
    }
}
