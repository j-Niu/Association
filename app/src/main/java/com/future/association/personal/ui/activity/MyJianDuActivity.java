package com.future.association.personal.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.future.association.R;
import com.future.association.personal.PersonConstant;
import com.future.association.personal.adapter.JianDuAdapter;
import com.future.association.personal.entity.MyJianDu;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.StatusUtils;

import java.util.ArrayList;
import java.util.List;

public class MyJianDuActivity extends BaseActivity {

    private ListView lvMyJiandu;
    private JianDuAdapter jianDuAdapter;
    private List<MyJianDu.MyJianDus> jianduList = new ArrayList<>();
    private String imgUrl = "http://img2.3lian.com/2014/f2/37/d/40.jpg";

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        StatusUtils.setStatusbarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_my_jian_du);
    }

    @Override
    protected void initView() {
        setTitle("我的监督");
        setTitleLeft(R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lvMyJiandu = (ListView) findViewById(R.id.lvMyJiandu);
        new HttpRequest<MyJianDu>()
                .with(this)
                .addParam("apiCode", PersonConstant.MY_JIANDU)
                //.addParam("userToken", MyApp.getUserToken())
                .addParam("page", "1")
                .addParam("size", PersonConstant.PAGE_SIZE_DEFAULT)
//                .addParam("id", id)
                .setListener(new HttpRequest.OnNetworkListener<MyJianDu>() {
                    @Override
                    public void onSuccess(MyJianDu response) {
                        if (response == null) return;
                        jianduList.clear();
                        jianduList=response.getList();
                        if (jianDuAdapter == null) {
                            jianDuAdapter = new JianDuAdapter(MyJianDuActivity.this, jianduList, getLayoutInflater());
                            lvMyJiandu.setAdapter(jianDuAdapter);
                        } else {
                            jianDuAdapter.notifyDataSetChanged();
                        }
                        Toast.makeText(MyJianDuActivity.this, "JIANDU...", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(String message) {
                        toast("错误信息：" + message);
                    }
                }).start(new MyJianDu());

    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
