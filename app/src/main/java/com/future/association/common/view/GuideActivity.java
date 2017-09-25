package com.future.association.common.view;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.future.association.R;
import com.future.association.common.GuideResponse;
import com.future.association.login.LoginActivity;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.entity.BaseResponse;
import com.future.baselib.entity.DefaultResponse;
import com.future.baselib.utils.HttpRequest;

import java.util.Timer;
import java.util.TimerTask;

public class GuideActivity extends BaseActivity {

    private ImageView imageView;

    private String url;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_guide);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void initView() {
        imageView = (ImageView) findViewById(R.id.iv_background);
        new HttpRequest<GuideResponse>().with(this)
                .addParam("apiCode","_startpic_001")
                .setListener(new HttpRequest.OnNetworkListener<GuideResponse>() {
                    @Override
                    public void onSuccess(GuideResponse response) {
                        Glide.with(GuideActivity.this).load(response.img_url).into(imageView);
                    }

                    @Override
                    public void onFail(String message) {
                    }
                }).start(new GuideResponse());
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(LoginActivity.class);
                finish();
            }
        },3000);
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
