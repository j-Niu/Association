package com.future.association.community.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.future.association.R;
import com.future.association.community.base.BaseActivity;
import com.future.association.community.utils.DialogUtils;
import com.future.association.databinding.ActivitySendTieBinding;

/**
 * Created by HX·罗 on 2017/7/4.
 */

public class SendTieActivity extends BaseActivity<ActivitySendTieBinding> {
    private String[] types={"论坛版块", "其他版块1", "其他版块2", "其他版块3", "其他版块4", "其他版块5"};
    @Override
    public int setContentView() {
        return R.layout.activity_send_tie;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        viewBinding.layoutTitle.setTitle("发布帖子");
        viewBinding.setTypeName(types[0]);
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
                break;
            case R.id.tv_type:
                DialogUtils.showSelectDialog(context, "请选择板块", types, new DialogUtils.ItemSelectedListener() {
                    @Override
                    public void select(int position) {
                        viewBinding.setTypeName(types[position]);
                    }
                });
                break;
        }
    }
}
