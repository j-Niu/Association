package com.future.association.community.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.future.association.R;
import com.future.association.community.CommunityFragment;
import com.future.association.community.base.BaseActivity;
import com.future.association.community.contract.SendTieContract;
import com.future.association.community.model.PlateInfo;
import com.future.association.community.model.UserPlateInfo;
import com.future.association.community.presenter.SendTiePresenter;
import com.future.association.community.utils.ActivityUtils;
import com.future.association.community.utils.DialogUtils;
import com.future.association.community.utils.StringUtils;
import com.future.association.databinding.ActivitySendTieBinding;

import java.util.ArrayList;

import static java.lang.System.in;

/**
 * Created by HX·罗 on 2017/7/4.
 */

public class SendTieActivity extends BaseActivity<ActivitySendTieBinding> implements SendTieContract.IView {
    private SendTieContract.IPresenter presenter;
    private ArrayList<PlateInfo> plateInfos;
    private PlateInfo plateInfo;
    private UserPlateInfo userPlateInfo;

    @Override
    public int setContentView() {
        return R.layout.activity_send_tie;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        plateInfo = getIntent().getParcelableExtra("plateInfo");
        userPlateInfo = getIntent().getParcelableExtra("userPlateInfo");
        plateInfos = userPlateInfo.getPlateInfos();
        int defaultPlateInfo = 0;
        for (int i = 0; i < plateInfos.size(); i++) {
            if (plateInfo.getId().equals(plateInfos.get(i).getId())) {
                defaultPlateInfo = i;
            }
        }
        viewBinding.layoutTitle.setTitle("发布帖子");
        viewBinding.setPlateInfo(plateInfos.get(defaultPlateInfo));
        presenter = new SendTiePresenter(this, context);
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
                if (s != null && s.toString().trim().length() >= 40) {//不超过40个字符
                    viewBinding.setTitle(s.toString().trim().substring(0, 40));
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_send:
                presenter.sendTie();
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
        if ("1".equals(plateInfo.getAudit())) {//1需要审核2不需要审核
            showShortToast("操作成功，请等待审核");
        } else {
            showShortToast("发帖成功");
        }
        finish();
    }

    @Override
    public void showMsg(String msg) {
        showShortToast(msg);
    }
}
