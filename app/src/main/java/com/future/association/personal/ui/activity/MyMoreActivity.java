package com.future.association.personal.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.login.LoginActivity;
import com.future.association.login.util.CommonUtil;
import com.future.association.personal.PersonConstant;
import com.future.association.personal.entity.MyExit;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.StatusUtils;

public class MyMoreActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout myChangePwd, myAboutUs, myGiveUsPingJia, myVerifyUpdate, myClearCache;
    private Button mySafeExit;
    private Toast checkUpdate, clearSuccToast;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        StatusUtils.setStatusbarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_my_more);
    }

    @Override
    protected void initView() {
        setTitle("更多");
        setTitleLeft(R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        myChangePwd = (RelativeLayout) findViewById(R.id.myChangePwd);
        myAboutUs = (RelativeLayout) findViewById(R.id.myAboutUs);
        myGiveUsPingJia = (RelativeLayout) findViewById(R.id.myGiveUsPingJia);
        myVerifyUpdate = (RelativeLayout) findViewById(R.id.myVerifyUpdate);
        myClearCache = (RelativeLayout) findViewById(R.id.myClearCache);
        mySafeExit = (Button) findViewById(R.id.mySafeExit);

        myChangePwd.setOnClickListener(this);
        myAboutUs.setOnClickListener(this);
        myGiveUsPingJia.setOnClickListener(this);
        myVerifyUpdate.setOnClickListener(this);
        myClearCache.setOnClickListener(this);
        mySafeExit.setOnClickListener(this);
    }

    @Override
    protected void initLogic() {
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myChangePwd:
                startActivity(ChangePwdActivity.class);
                break;
            case R.id.myAboutUs:
                startActivity(AboutUsActivity.class);
                break;
            case R.id.myGiveUsPingJia:

                break;
            case R.id.myVerifyUpdate:
                checkUpdate = Toast.makeText(this, "已是最新版本", Toast.LENGTH_SHORT);
                checkUpdate.setGravity(Gravity.CENTER, 0, 0);
                LinearLayout toastView = (LinearLayout) checkUpdate.getView();
                toastView.setLayoutParams(new LinearLayout.LayoutParams(140, 90));
                ImageView imageCodeProject = new ImageView(this);
                imageCodeProject.setImageResource(R.drawable.clear_succ);
                toastView.addView(imageCodeProject, 0);
                checkUpdate.show();
                break;
            case R.id.myClearCache:
                clearSuccToast = Toast.makeText(this, "缓存清理成功", Toast.LENGTH_SHORT);
                clearSuccToast.setGravity(Gravity.CENTER, 0, 0);
                LinearLayout toastView2 = (LinearLayout) clearSuccToast.getView();
                toastView2.setLayoutParams(new LinearLayout.LayoutParams(140, 90));
                ImageView imageCodeProject2 = new ImageView(this);
                imageCodeProject2.setImageResource(R.drawable.clear_succ);
                toastView2.addView(imageCodeProject2, 0);
                clearSuccToast.show();
                break;
            case R.id.mySafeExit:
                WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
                Display display = windowManager.getDefaultDisplay();

                final Dialog buyTimeDialog = new Dialog(this, R.style.filletDialog);
                buyTimeDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                View bView = getLayoutInflater().inflate(R.layout.dialog_exit, null);

                RadioGroup group = (RadioGroup) bView.findViewById(R.id.rbCharge).getParent();
                group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        switch (checkedId) {
                            case R.id.rbCharge:
                                buyTimeDialog.dismiss();
                                break;
                            case R.id.rbCancel:
                                new HttpRequest<MyExit>()
                                        .with(MyMoreActivity.this)
                                        .addParam("apiCode", PersonConstant.MY_LOGOUT)
                                        .addParam("userToken", MyApp.getUserToken())
//                .addParam("id", id)
                                        .setListener(new HttpRequest.OnNetworkListener<MyExit>() {
                                            @Override
                                            public void onSuccess(MyExit response) {
                                                CommonUtil.clearLoginMsg(MyMoreActivity.this);
                                                finish();
                                                MyApp.getApp().notifyDataChange(0x898, null, null);
//                                                ToastUtils.shortToast(MyMoreActivity.this, "退出成功");
                                                startActivity(new Intent(MyMoreActivity.this, LoginActivity.class));
                                            }

                                            @Override
                                            public void onFail(String message) {
                                                toast("错误信息：" + message);
                                            }
                                        }).start(new MyExit());
                                buyTimeDialog.dismiss();
                                break;
                        }
                    }
                });

                buyTimeDialog.setContentView(bView);
                buyTimeDialog.show();
                Window window = buyTimeDialog.getWindow();
                WindowManager.LayoutParams params = window.getAttributes();
                params.width = display.getWidth() * 2 / 3;
//        params.height = display.getHeight() * 2 / 3;
                window.setAttributes(params);
                break;
        }
    }
}
