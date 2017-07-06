package com.future.association.login;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.future.association.BR;
import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.databinding.ActivityFindPwdVerifyBinding;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.StatusUtils;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Mwh on 2017/7/4.
 */

public class FindPwdVerifyActivity extends BaseActivity {
    ActivityFindPwdVerifyBinding binding;
    FindPwdVerifyViewModel findPwdVerifyViewModel;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_find_pwd_verify);
    }

    @Override
    protected void initView() {
        findPwdVerifyViewModel = new FindPwdVerifyViewModel(this, binding);
        findPwdVerifyViewModel.initLinstener();
        binding.setVariable(BR.findPwdVerifyViewModel,findPwdVerifyViewModel);
        setTitle("忘记密码");
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
