package com.future.association.login;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //Finger touch screen event
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // get current focus,Generally it is EditText
            View view = getCurrentFocus();
            if (isShouldHideSoftKeyBoard(view, ev)) {
                hideSoftKeyBoard(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isShouldHideSoftKeyBoard(View view, MotionEvent event) {
        if (view != null && (view instanceof EditText)) {
            int[] l = {0, 0};
            view.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + view.getHeight(), right = left
                    + view.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // If click the EditText event ,ignore it
                return false;
            } else {
                return true;
            }
        }
        // if the focus is EditText,ignore it;
        return false;
    }

    private void hideSoftKeyBoard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
