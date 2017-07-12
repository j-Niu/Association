package com.future.association.community.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.future.association.R;
import com.future.association.community.base.BaseActivity;
import com.future.association.community.contract.SendTieContract;
import com.future.association.community.model.PlateInfo;
import com.future.association.community.presenter.SendTiePresenter;
import com.future.association.community.utils.DialogUtils;
import com.future.association.databinding.ActivitySendTieBinding;

import java.util.ArrayList;

import static java.lang.System.in;

/**
 * Created by HX·罗 on 2017/7/4.
 */

public class SendTieActivity extends BaseActivity<ActivitySendTieBinding> implements SendTieContract.IView{
    private String[] types ;
    private SendTieContract.IPresenter presenter;
    private ArrayList<PlateInfo> plateInfos;

    @Override
    public int setContentView() {
        return R.layout.activity_send_tie;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        PlateInfo plateInfo = getIntent().getParcelableExtra("plateInfo");
        plateInfos = getIntent().getParcelableArrayListExtra("plateInfos");
        types = new String[plateInfos.size()] ;
        int defaultPlateInfo = 0 ;
        for (int i = 0; i< plateInfos.size(); i++){
            if(plateInfo.getId().equals( plateInfos.get(i).getId())){
                defaultPlateInfo = i ;
            }
            types[i] = plateInfos.get(i).getName() ;
        }
        viewBinding.layoutTitle.setTitle("发布帖子");
        viewBinding.setPlateInfo(plateInfos.get(defaultPlateInfo));
        presenter = new SendTiePresenter(this,context);
    }

    @Override
    public void initListener() {
        viewBinding.layoutTitle.setViewClickListener(this);
        viewBinding.setClickListener(this);
        viewBinding.etTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s != null && s.toString().trim().length() >= 40){//不超过40个字符
                    viewBinding.setTitle(s.toString().trim().substring(0,40));
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_send:
                presenter.sendTie();
                break;
            case R.id.tv_type:
                DialogUtils.showSelectDialog(context, "请选择板块", types, new DialogUtils.ItemSelectedListener() {
                    @Override
                    public void select(int position) {
                        viewBinding.setPlateInfo(plateInfos.get(position));
                    }
                });
                break;
        }
    }

    @Override
    public String getTieTitle() {
        return viewBinding.getTitle();
    }

    @Override
    public String getTieContent() {
        return viewBinding.getContent();
    }

    @Override
    public String geTietType() {
        return viewBinding.getPlateInfo().getId();
    }

    @Override
    public void sendResult(boolean isSuccess) {
        if(isSuccess){
            showShortToast("发帖成功");
            viewBinding.setTitle("");
            viewBinding.setContent("");
        }else{
            showShortToast("发帖失败，请稍候再试");
        }
    }
}
