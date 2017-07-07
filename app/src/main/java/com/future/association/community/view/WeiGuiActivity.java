package com.future.association.community.view;

import android.view.View;

import com.future.association.R;
import com.future.association.community.base.BaseActivity;
import com.future.association.community.contract.WeiguiContract;
import com.future.association.community.presenter.WeiguiPresenter;
import com.future.association.community.utils.DialogUtils;
import com.future.association.databinding.ActivityWeiguiBinding;


/**
 * Created by HX·罗 on 2017/7/4.
 */

public class WeiGuiActivity extends BaseActivity<ActivityWeiguiBinding> implements WeiguiContract.IView{

    private String[] causes;
    private String[] typeNames;
    private WeiguiContract.IPresenter presenter;

    @Override
    public int setContentView() {
        return R.layout.activity_weigui;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        causes = new String[]{"原因1","原因2","原因3","原因4","原因5"};
        typeNames = new String[]{"处理方式1","处理方式2","处理方式3","处理方式4","处理方式5"};
        viewBinding.setCause(causes[0]);
        viewBinding.setTypeName(typeNames[0]);
        viewBinding.layoutTitle.setTitle("违规操作");
        presenter = new WeiguiPresenter(this);
    }

    @Override
    public void initListener() {
        viewBinding.layoutTitle.setViewClickListener(this);
        viewBinding.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_cause:

                DialogUtils.showSelectDialog(context, "请选择违规原因", causes, new DialogUtils.ItemSelectedListener() {
                    @Override
                    public void select(int position) {
                        viewBinding.setCause(causes[position]);
                    }
                });
                break;
            case R.id.tv_type:

                DialogUtils.showSelectDialog(context, "请选择处理方式", typeNames, new DialogUtils.ItemSelectedListener() {
                    @Override
                    public void select(int position) {
                        viewBinding.setTypeName(typeNames[position]);
                    }
                });
                break;
            case R.id.btn_sure:
                presenter.doOperation();
                break;
        }
    }

    @Override
    public String getWGCause() {
        return viewBinding.getCause();
    }

    @Override
    public String getDealType() {
        return viewBinding.getTypeName();
    }

    @Override
    public void dealResult(boolean isSuccess) {
        if (isSuccess) {
            DialogUtils.showResultDialog(context,true);
        }
    }
}
