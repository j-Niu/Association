package com.future.association.personal.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.personal.PersonConstant;
import com.future.association.personal.entity.MyLevel;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.StatusUtils;

public class MyLevelActivity extends BaseActivity {

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        StatusUtils.setStatusbarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_my_level);
    }

    @Override
    protected void initView() {
        setTitle("我的等级");
        setTitleLeft(R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        new HttpRequest<MyLevel>()
                .with(this)
                .addParam("apiCode", PersonConstant.MY_LEVELS_LINSHI)
                .addParam("userToken", MyApp.getUserToken())
                .setListener(new HttpRequest.OnNetworkListener<MyLevel>() {
                    @Override
                    public void onSuccess(MyLevel response) {
                        if (response == null) return;
                        Log.v("123", "我的等级  ---    " + response.toString());
                    }

                    @Override
                    public void onFail(String message) {
                        toast("错误信息：" + message);
                    }
                }).start(new MyLevel());
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
