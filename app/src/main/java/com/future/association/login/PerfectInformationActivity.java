package com.future.association.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.future.association.BR;
import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.databinding.ActivityPerfectInformationBinding;
import com.future.association.login.viewmodel.PerfectInformationViewModel;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.StatusUtils;

/**
 * Created by Administrator on 2017/7/4.
 */

public class PerfectInformationActivity extends BaseActivity {
    ActivityPerfectInformationBinding binding;
    PerfectInformationViewModel perfectInformationViewModel;
    String phoneNumber;
    String code;
    String password;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_perfect_information);
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        code = getIntent().getStringExtra("code");
        password = getIntent().getStringExtra("password");
        perfectInformationViewModel = new PerfectInformationViewModel(this, binding);
        perfectInformationViewModel.setCode(code);
        perfectInformationViewModel.setPhoneNumber(phoneNumber);
        perfectInformationViewModel.setPassword(password);
    }

    @Override
    protected void initView() {

        binding.setVariable(BR.perfectInformationViewModel, perfectInformationViewModel);
        perfectInformationViewModel.initLinstener();
        setTitle("完善信息");
        setTitleLeft(R.drawable.nav_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void initLogic() {
        StatusUtils.setStatusbarColor(this, ContextCompat.getColor(this, R.color.color_26A16E));
        MyApp.getApp().getActivityManager().pushActivity(this);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }


}
