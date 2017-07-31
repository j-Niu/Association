package com.future.association.personal.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.personal.PersonConstant;
import com.future.association.personal.adapter.NoticeAdapter;
import com.future.association.personal.entity.MyNotice;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.StatusUtils;

import java.util.ArrayList;
import java.util.List;

public class MyNoticeActivity extends BaseActivity {
    private ListView lvMyNotice;
    private List<MyNotice.MyNotices> noticeList = new ArrayList<>();
    private NoticeAdapter noticeAdapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        StatusUtils.setStatusbarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_my_notice);
    }

    @Override
    protected void initView() {
        setTitle("我的消息");
        setTitleLeft(R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lvMyNotice = (ListView) findViewById(R.id.lvMyNotice);

        new HttpRequest<MyNotice>()
                .with(this)
                .addParam("apiCode", PersonConstant.MY_NOTICE)
                .addParam("userToken", MyApp.getUserToken())
                .addParam("page", "1")
                .addParam("size", PersonConstant.PAGE_SIZE_DEFAULT)
//                .addParam("id", id)
                .setListener(new HttpRequest.OnNetworkListener<MyNotice>() {
                    @Override
                    public void onSuccess(MyNotice response) {
                        if (response == null) return;
                        noticeList.clear();
                        noticeList=response.getList();
                        if (noticeAdapter == null) {
                            noticeAdapter = new NoticeAdapter(MyNoticeActivity.this, noticeList, getLayoutInflater());
                            lvMyNotice.setAdapter(noticeAdapter);
                        } else {
                            noticeAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFail(String message) {
                        toast("错误信息：" + message);
                    }
                }).start(new MyNotice());
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
