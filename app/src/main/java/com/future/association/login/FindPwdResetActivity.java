package com.future.association.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.future.association.BR;
import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.databinding.ActivityFindPwdResetBinding;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.StatusUtils;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Mwh on 2017/7/4.
 */

public class FindPwdResetActivity extends BaseActivity {
    ActivityFindPwdResetBinding binding;
    FindPwdResetViewModel findPwdResetViewModel;

    public FindPwdResetActivity() {

    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_find_pwd_reset);
    }

    @Override
    protected void initView() {
        findPwdResetViewModel = new FindPwdResetViewModel(this, binding);
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
