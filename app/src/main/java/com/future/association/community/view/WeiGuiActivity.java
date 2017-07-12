package com.future.association.community.view;

import android.view.View;

import com.future.association.R;
import com.future.association.community.base.BaseActivity;
import com.future.association.community.contract.WeiguiContract;
import com.future.association.community.model.WGCauseInfo;
import com.future.association.community.presenter.WeiguiPresenter;
import com.future.association.community.utils.DialogUtils;
import com.future.association.databinding.ActivityWeiguiBinding;

import java.util.ArrayList;


/**
 * Created by HX·罗 on 2017/7/4.
 */

public class WeiGuiActivity extends BaseActivity<ActivityWeiguiBinding> implements WeiguiContract.IView {

    private String[] causes;
    private String[] typeNames = new String[]{"删除"};
    private WeiguiContract.IPresenter presenter;
    private ArrayList<WGCauseInfo> wgCauses;
    private String tieId;
    private String id;
    private String dealTypeId = "1";//处理方式 1 删除

    @Override
    public int setContentView() {
        return R.layout.activity_weigui;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        //帖子ID
        tieId = getIntent().getStringExtra("tieId");
        //帖子回复ID
        id = getIntent().getStringExtra("id");
        viewBinding.layoutTitle.setTitle("违规操作");
        viewBinding.setDealType(typeNames[0]);
        presenter = new WeiguiPresenter(this, context);
        presenter.requestWGCause();
    }

    @Override
    public void initListener() {
        viewBinding.layoutTitle.setViewClickListener(this);
        viewBinding.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_cause:

                DialogUtils.showSelectDialog(context, "请选择违规原因", causes, new DialogUtils.ItemSelectedListener() {
                    @Override
                    public void select(int position) {
                        viewBinding.setWgCauseInfo(wgCauses.get(position));
                    }
                });
                break;
            case R.id.tv_type:

                DialogUtils.showSelectDialog(context, "请选择处理方式", typeNames, new DialogUtils.ItemSelectedListener() {
                    @Override
                    public void select(int position) {
                        dealTypeId = position+1+"" ;
                        viewBinding.setDealType(typeNames[position]);
                    }
                });
                break;
            case R.id.btn_sure:
                if (wgCauses != null) {
                    presenter.doOperation();
                }
                break;
        }
    }

    @Override
    public String getWGCause() {
        return viewBinding.getWgCauseInfo().getId();
    }

    @Override
    public String getDealType() {
        return dealTypeId;
    }

    @Override
    public void setWGCauses(ArrayList<WGCauseInfo> wgCauses) {
        if (wgCauses != null) {
            this.wgCauses = wgCauses;
            causes = new String[wgCauses.size()];
            for (int i = 0; i < wgCauses.size(); i++) {
                causes[i] = wgCauses.get(i).getId();
            }
            viewBinding.setWgCauseInfo(wgCauses.get(0));
        }
    }

    @Override
    public void dealResult(boolean isSuccess) {
        if (isSuccess) {
            DialogUtils.showResultDialog(context, true);
        }
    }

    @Override
    public String getTieId() {
        return tieId;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void showMsg(String msg) {
        showShortToast(msg);
    }
}
