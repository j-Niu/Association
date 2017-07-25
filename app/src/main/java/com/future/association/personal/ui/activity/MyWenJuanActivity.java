package com.future.association.personal.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.personal.PersonConstant;
import com.future.association.personal.adapter.WenjuanAdapter;
import com.future.association.personal.entity.BeanWenJuan;
import com.future.association.personal.entity.MyWenJuan;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.StatusUtils;

import java.util.ArrayList;
import java.util.List;

public class MyWenJuanActivity extends BaseActivity {

    private ListView lvMyWenjuan;
    private WenjuanAdapter wenjuanAdapter;
    private List<BeanWenJuan> wenJuanList = new ArrayList<>();

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        StatusUtils.setStatusbarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_my_wen_juan);
    }

    @Override
    protected void initView() {
        setTitle("我的问卷");
        setTitleLeft(R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lvMyWenjuan = (ListView) findViewById(R.id.lvMyWenjuan);
        BeanWenJuan wenJuan;
        for (int i = 0; i < 3; i++) {
            wenJuan = new BeanWenJuan(getString(R.string.myWJ1), getString(R.string.myWJ2)
                    , getString(R.string.myWJ3), 0);
            wenJuanList.add(wenJuan);
        }
        for (int i = 0; i < 3; i++) {
            wenJuan = new BeanWenJuan(getString(R.string.myWJ1), getString(R.string.myWJ2)
                    , getString(R.string.myWJ3), 1);
            wenJuanList.add(wenJuan);
        }
        for (int i = 0; i < 3; i++) {
            wenJuan = new BeanWenJuan(getString(R.string.myWJ1), getString(R.string.myWJ2)
                    , getString(R.string.myWJ3), 2);
            wenJuanList.add(wenJuan);
        }
        if (wenjuanAdapter == null) {
            wenjuanAdapter = new WenjuanAdapter(this, wenJuanList, getLayoutInflater());
            lvMyWenjuan.setAdapter(wenjuanAdapter);
        } else {
            wenjuanAdapter.notifyDataSetChanged();
        }



        new HttpRequest<MyWenJuan>()
                .with(this)
                .addParam("apiCode", PersonConstant.MY_WENJUAN)
                .addParam("userToken", MyApp.getUserToken())
                .addParam("page", "2")
                .addParam("size", PersonConstant.PAGE_SIZE_DEFAULT)
//                .addParam("id", id)
                .setListener(new HttpRequest.OnNetworkListener<MyWenJuan>() {
                    @Override
                    public void onSuccess(MyWenJuan response) {

                    }

                    @Override
                    public void onFail(String message) {
                        toast("错误信息：" + message);
                    }
                }).start(new MyWenJuan());
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
