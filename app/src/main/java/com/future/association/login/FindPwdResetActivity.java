package com.future.association.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.future.association.BR;
import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.databinding.ActivityFindPwdResetBinding;
import com.future.association.login.viewmodel.FindPwdResetViewModel;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.StatusUtils;

/**
 * Created by Mwh on 2017/7/4.
 */

public class FindPwdResetActivity extends BaseActivity {
    ActivityFindPwdResetBinding binding;
    FindPwdResetViewModel findPwdResetViewModel;


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_find_pwd_reset);
        Intent intent = getIntent();
        String phoneNumber = intent.getStringExtra("phoneNumber");
        String code = intent.getStringExtra("code");
        int type = intent.getIntExtra("type", 1);
        findPwdResetViewModel = new FindPwdResetViewModel(this, binding);
        findPwdResetViewModel.setCode(code);
        findPwdResetViewModel.setPhoneNumber(phoneNumber);
        findPwdResetViewModel.setType(type);
    }

    @Override
    protected void initView() {

        binding.setVariable(BR.findPwdResetViewModel, findPwdResetViewModel);
        setTitle("忘记密码");
        setTitleLeft(R.drawable.nav_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findPwdResetViewModel.initLinstener();
    }

    @Override
    protected void initLogic() {
        MyApp.getApp().getActivityManager().pushActivity(this);
        StatusUtils.setStatusbarColor(this, ContextCompat.getColor(this, R.color.color_26A16E));
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
