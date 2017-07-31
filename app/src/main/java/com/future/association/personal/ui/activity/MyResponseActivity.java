package com.future.association.personal.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.personal.PersonConstant;
import com.future.association.personal.adapter.HuiYingAdapter;
import com.future.association.personal.entity.MyResponse;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.StatusUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * _mytiezihuifu_001
 */
public class MyResponseActivity extends BaseActivity {

    private ListView lvMyResponse;
    private List<MyResponse.MyResponses> responseList = new ArrayList<>();
    private HuiYingAdapter huiYingAdapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        StatusUtils.setStatusbarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_my_response);
    }

    @Override
    protected void initView() {
        setTitle("我的回应");
        setTitleLeft(R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lvMyResponse = (ListView) findViewById(R.id.lvMyResponse);
        /*
        07-30 19:48:27.168 3000-3000/com.future.association E/json: {"error":0,"info":[]}
07-30 19:48:27.168 3000-3000/com.future.association W/123: MyResponse 内容 --- []
07-30 19:48:27.168 3000-3000/com.future.association E/BaseResponse: json格式有误:{"error":0,"info":[]}
         */
        new HttpRequest<MyResponse>()
                .with(this)
                .addParam("apiCode", PersonConstant.MY_RESPONSE)
                .addParam("userToken", MyApp.getUserToken())
                .addParam("page", "1")
                .addParam("size", PersonConstant.PAGE_SIZE_DEFAULT)
//                .addParam("id", id)
                .setListener(new HttpRequest.OnNetworkListener<MyResponse>() {
                    @Override
                    public void onSuccess(MyResponse response) {
                        if (response==null) return;
                        responseList.clear();
                        responseList=response.getList();
                        if (huiYingAdapter == null) {
                            huiYingAdapter = new HuiYingAdapter(MyResponseActivity.this, responseList, getLayoutInflater());
                            lvMyResponse.setAdapter(huiYingAdapter);
                        } else {
                            huiYingAdapter.notifyDataSetChanged();
                        }
                        Toast.makeText(MyResponseActivity.this, "ResSSSS", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(String message) {
                        toast("错误信息：" + message);
                    }
                }).start(new MyResponse());

    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
