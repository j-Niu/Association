package com.future.association.community;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.future.association.R;
import com.future.association.community.viewmodels.FragmenhtCommitunitNewVM;
import com.future.association.databinding.FragmenhtCommitunitNewBinding;
import com.future.baselib.activity.BaseFragment;

public class CommunitNewFragmnet extends BaseFragment {
    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Nullable
    @Override
    protected int getLayoutResId() {
        return R.layout.fragmenht_commitunit_new;
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        setTitle("社区");
        FragmenhtCommitunitNewBinding binding = DataBindingUtil.bind(view);
        binding.setViewModel(new FragmenhtCommitunitNewVM(binding,this));
    }
}
