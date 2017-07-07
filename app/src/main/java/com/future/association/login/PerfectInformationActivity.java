package com.future.association.login;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.future.association.BR;
import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.databinding.ActivityPerfectInformationBinding;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.StatusUtils;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/7/4.
 */

public class PerfectInformationActivity extends BaseActivity {
    ActivityPerfectInformationBinding binding;
    PerfectInformationViewModel perfectInformationViewModel;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_perfect_information);
    }

    @Override
    protected void initView() {
        perfectInformationViewModel = new PerfectInformationViewModel(this, binding);
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
