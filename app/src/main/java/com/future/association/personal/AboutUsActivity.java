package com.future.association.personal;

import android.os.Bundle;
import android.view.View;

import com.future.association.R;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.StatusUtils;

public class AboutUsActivity extends BaseActivity {

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        StatusUtils.setStatusbarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_about_us);
    }

    @Override
    protected void initView() {
        setTitle("关于");
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
