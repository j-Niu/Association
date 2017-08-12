package com.future.association.personal.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.community.custom.CircleImageView;
import com.future.association.personal.PersonConstant;
import com.future.association.personal.adapter.MyTzReplyAdapter;
import com.future.association.personal.entity.MyTieziDetail;
import com.future.association.personal.entity.MyTzReplyInfo;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.StatusUtils;

import java.util.ArrayList;
import java.util.List;

public class TzDetailActivity extends BaseActivity {
    private ListView tz_detail;
    private View mHeadView;
    private CircleImageView civ_head;
    private TextView tv_name, tv_class, tv_address;
    private TextView tv_title, tv_date, typeFormat, content;
    private MyTzReplyAdapter adapter;
    private List<MyTzReplyInfo.MyTzReplyInfos> tieReplyInfos = new ArrayList<>();
    private String tid;

    @Override
    protected void getBundleExtras(Bundle extras) {
        tid = extras.getString("tzid");
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        StatusUtils.setStatusbarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_tz_detail);
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void initView() {
        setTitle("帖子详情");
        setTitleLeft(R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tz_detail = (ListView) findViewById(R.id.tz_detail);
//        mHeadView = View.inflate(this, R.layout.item_tz_header, null);
        mHeadView = LayoutInflater.from(this).inflate(R.layout.item_tz_header, null);
        civ_head = (CircleImageView) mHeadView.findViewById(R.id.civ_head);
        tv_name = (TextView) mHeadView.findViewById(R.id.tv_name);
        tv_class = (TextView) mHeadView.findViewById(R.id.tv_class);
        tv_address = (TextView) mHeadView.findViewById(R.id.tv_address);
        tv_title = (TextView) mHeadView.findViewById(R.id.tv_title);
        tv_date = (TextView) mHeadView.findViewById(R.id.tv_date);
        typeFormat = (TextView) mHeadView.findViewById(R.id.typeFormat);
        content = (TextView) mHeadView.findViewById(R.id.content);
        content.setText("HHHXXXX");
//        tz_detail.addHeaderView(mHeadView, null, false);

        netForReply();
        netDetail();
    }

    private void netDetail() {
        new HttpRequest<MyTieziDetail>()
                .with(this)
                .addParam("apiCode", PersonConstant.MY_TIEZI_DETAIL)
                .addParam("userToken", MyApp.getUserToken())
                .addParam("id", tid)
                .setListener(new HttpRequest.OnNetworkListener<MyTieziDetail>() {
                    @Override
                    public void onSuccess(MyTieziDetail response) {
                        if (response == null) return;
                        MyTieziDetail.MyTieziDetails details = response.getList().get(0);
                        Log.v("123", "hahhauuu ----  " + details.real_name);

//                        Glide.with(TzDetailActivity.this)
//                                .asBitmap().load(details.avatar_url).into(civ_head);
//                        tv_name.setText(details.real_name);
//                        tv_class.setText(details.level);
//                        tv_address.setText(details.address);
//                        tv_title.setText(details.title);
//                        tv_date.setText(details.create_time);
//                        typeFormat.setText(details.type);
//                        content.setText(details.content);
                    }

                    @Override
                    public void onFail(String message) {
                        toast("错误信息：" + message);
                    }
                }).start(new MyTieziDetail());
    }

    private void netForReply() {
        new HttpRequest<MyTzReplyInfo>()
                .with(this)
                .addParam("apiCode", PersonConstant.MY_TIEZI_REPLY)
                .addParam("userToken", MyApp.getUserToken())
                .addParam("id", tid)
                .addParam("page", 1 + "")
                .addParam("size", PersonConstant.PAGE_SIZE_DEFAULT)
                .setListener(new HttpRequest.OnNetworkListener<MyTzReplyInfo>() {
                    @Override
                    public void onSuccess(MyTzReplyInfo response) {
                        Log.w("123","OOOPPPP --- "+response.toString());
                        if (response == null) return;
                        tieReplyInfos = response.getList();
                        Log.i("123","xxxxx--- "+tieReplyInfos.get(0).toString());
                        if (tieReplyInfos == null) {
                            adapter = new MyTzReplyAdapter(TzDetailActivity.this, tieReplyInfos, getLayoutInflater());
                            tz_detail.setAdapter(adapter);
                        } else {
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFail(String message) {
                        toast("错误信息：" + message);
                    }
                }).start(new MyTzReplyInfo());
    }
}
