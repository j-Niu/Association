package com.future.association.supervice.view;

import android.os.Bundle;
import android.view.View;

import com.future.association.R;
import com.future.baselib.activity.BaseActivity;

public class SuperviceApplySuccessActivity extends BaseActivity {
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_supervice_success);
    }

    @Override
    protected void initView() {
        setTitle("监督发布");
        setTitleLeft(R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
