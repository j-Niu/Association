package com.future.association.personal.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.personal.PersonConstant;
import com.future.association.personal.adapter.TieziAdapter;
import com.future.association.personal.entity.BeanMyTiezi;
import com.future.association.personal.entity.MyTiezi;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.StatusUtils;

import java.util.ArrayList;
import java.util.List;

public class MyTieziActivity extends BaseActivity {
    private ListView lvMyTiezi;
    private List<BeanMyTiezi> tieziList = new ArrayList<>();
    private TieziAdapter tieziAdapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        StatusUtils.setStatusbarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_my_tiezi);
    }

    @Override
    protected void initView() {
        setTitle("我的帖子");
        setTitleLeft(R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lvMyTiezi = (ListView) findViewById(R.id.lvMyTiezi);
        BeanMyTiezi myTiezi;
        for (int i = 0; i < 10; i++) {
            myTiezi = new BeanMyTiezi(getString(R.string.myHY1),
                    getString(R.string.myHY2), getString(R.string.myHY4), getString(R.string.myHY3));
            tieziList.add(myTiezi);
        }
        if (tieziAdapter == null) {
            tieziAdapter = new TieziAdapter(this, tieziList, getLayoutInflater());
            lvMyTiezi.setAdapter(tieziAdapter);
        } else {
            tieziAdapter.notifyDataSetChanged();
        }


        new HttpRequest<MyTiezi>()
                .with(this)
                .addParam("apiCode", PersonConstant.MY_TIEZI)
                .addParam("userToken", MyApp.getUserToken())
                .addParam("page", "2")
                .addParam("size", PersonConstant.PAGE_SIZE_DEFAULT)
//                .addParam("id", id)
                .setListener(new HttpRequest.OnNetworkListener<MyTiezi>() {
                    @Override
                    public void onSuccess(MyTiezi response) {

                    }

                    @Override
                    public void onFail(String message) {
                        toast("错误信息：" + message);
                    }
                }).start(new MyTiezi());
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
