package com.future.association.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.future.association.R;
import com.future.association.personal.adapter.NoticeAdapter;
import com.future.association.personal.entity.BeanNotice;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.StatusUtils;

import java.util.ArrayList;
import java.util.List;

public class MyNoticeActivity extends BaseActivity {
    private ListView lvMyNotice;
    private List<BeanNotice> noticeList = new ArrayList<>();
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
        BeanNotice notice;
        for (int i = 0; i < 10; i++) {
            notice = new BeanNotice(getString(R.string.myHY1),
                    getString(R.string.myHY2), getString(R.string.myHY3));
            noticeList.add(notice);
        }
        if (noticeAdapter == null) {
            noticeAdapter = new NoticeAdapter(this, noticeList, getLayoutInflater());
            lvMyNotice.setAdapter(noticeAdapter);
        } else {
            noticeAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
