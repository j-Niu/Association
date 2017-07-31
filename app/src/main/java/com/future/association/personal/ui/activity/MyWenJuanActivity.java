package com.future.association.personal.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.personal.PersonConstant;
import com.future.association.personal.adapter.WenjuanAdapter;
import com.future.association.personal.entity.MyWenJuan;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.StatusUtils;

import java.util.ArrayList;
import java.util.List;

public class MyWenJuanActivity extends BaseActivity {

    private ListView lvMyWenjuan;
    private WenjuanAdapter wenjuanAdapter;
    private List<MyWenJuan.MyWenJuans> wenJuanList = new ArrayList<>();

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
/*
07-30 20:26:46.132 28450-28450/com.future.association E/json: {"error":0,"info":[]}
07-30 20:26:46.132 28450-28450/com.future.association W/123: MyWenJuan 内容 --- []
07-30 20:26:46.132 28450-28450/com.future.association E/BaseResponse: json格式有误:{"error":0,"info":[]}
 */
        new HttpRequest<MyWenJuan>()
                .with(this)
                .addParam("apiCode", PersonConstant.MY_WENJUAN)
                .addParam("userToken", MyApp.getUserToken())
                .addParam("page", "1")
                .addParam("size", PersonConstant.PAGE_SIZE_DEFAULT)
//                .addParam("id", id)
                .setListener(new HttpRequest.OnNetworkListener<MyWenJuan>() {
                    @Override
                    public void onSuccess(MyWenJuan response) {
                        if (response == null) return;
                        wenJuanList.clear();
                        wenJuanList=response.getList();
                        if (wenjuanAdapter == null) {
                            wenjuanAdapter = new WenjuanAdapter(MyWenJuanActivity.this, wenJuanList, getLayoutInflater());
                            lvMyWenjuan.setAdapter(wenjuanAdapter);
                        } else {
                            wenjuanAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFail(String message) {
                        toast("错误信息：" + message);
                    }
                }).start(new MyWenJuan());

//        QuestionnaireApi.getInstance().getMyWenjuan(this, MyApp.getUserToken(),String.valueOf(1))
//                .setListener(new HttpRequest.OnNetworkListener<QuestionList>() {
//                    @Override
//                    public void onSuccess(QuestionList response) {
//                        Toast.makeText(MyWenJuanActivity.this, "我的问卷"+response.getTitle(), Toast.LENGTH_SHORT).show();
//                        if (response == null || response.getList() == null) {
////                            adapterObservable.get().loadMoreEnd();
//                        } else {
//                            if (response.getList().size() < 20) {
////                                adapterObservable.get().loadMoreEnd();
//                            }
////                            items.addAll(response.getList());
//                        }
//                    }
//
//                    @Override
//                    public void onFail(String message) {
//                        toast("错误信息：" + message);
//                    }
//                }).start(new QuestionList());


    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
