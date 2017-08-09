package com.future.association.personal.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.personal.PersonConstant;
import com.future.association.personal.adapter.ItemMyLevelAdapter;
import com.future.association.personal.entity.MyLevel;
import com.future.association.personal.util.MesureListView;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.StatusUtils;

import java.util.ArrayList;
import java.util.List;

public class MyLevelActivity extends BaseActivity {
    private ImageView imgDengji;
    private TextView tvMyLevelResponse, tvMyChenghao, tvMyLevelOth, tvMyDiffNextLevel;
    private List<MyLevel.MyLevels> myLevelsList = new ArrayList<>();
    private ItemMyLevelAdapter myLevelAdapter;
    private ListView lvMyZY;
    private String str1, str2, str3, str4, str5;

    @Override
    protected void getBundleExtras(Bundle extras) {
        str1 = extras.getString("level_img");
        str2 = extras.getString("level");
        str3 = extras.getString("chenghao");
        str4 = extras.getString("jifencha");
        str5 = extras.getString("jifen");
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        StatusUtils.setStatusbarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_my_level);
    }

    @Override
    protected void initLogic() {

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
        imgDengji = (ImageView) findViewById(R.id.imgDengji);
        tvMyLevelResponse = (TextView) findViewById(R.id.tvMyLevel);
        tvMyChenghao = (TextView) findViewById(R.id.tvMyChenghao);
        tvMyLevelOth = (TextView) findViewById(R.id.tvMyLevelOth);
        tvMyDiffNextLevel = (TextView) findViewById(R.id.tvMyDiffNextLevel);
        lvMyZY = (ListView) findViewById(R.id.lvMyZY);

        Glide.with(this).asBitmap().load(str1).into(imgDengji);
        tvMyLevelResponse.setText("当前等级:" + str2);
        tvMyChenghao.setText("称号:" + str3);
        tvMyLevelOth.setText("积分:" +str5);
        tvMyDiffNextLevel.setText(String.format("距离下一等级还需要%s积分", str4));

        new HttpRequest<MyLevel>()
                .with(this)
                .addParam("apiCode", PersonConstant.MY_LEVELS)
                .addParam("userToken", MyApp.getUserToken())
                .setListener(new HttpRequest.OnNetworkListener<MyLevel>() {
                    @Override
                    public void onSuccess(MyLevel response) {
                        if (response == null) return;
                        myLevelsList.clear();
                        myLevelsList = response.getList();
                        Log.v("123", "我的等级  ---    " + myLevelsList.toString());
                        if (myLevelAdapter == null) {
                            myLevelAdapter = new ItemMyLevelAdapter(MyLevelActivity.this, myLevelsList, getLayoutInflater());
                            lvMyZY.setAdapter(myLevelAdapter);
                        } else {
                            myLevelAdapter.notifyDataSetChanged();
                        }
                        MesureListView.setListViewHeightBasedOnChildren(lvMyZY);
                    }

                    @Override
                    public void onFail(String message) {
                        toast("错误信息：" + message);
                    }
                }).start(new MyLevel());
    }

}
