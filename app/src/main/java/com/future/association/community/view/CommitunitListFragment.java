package com.future.association.community.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.future.association.R;
import com.future.association.community.viewmodels.CommitunitListFragmentVM;
import com.future.association.databinding.FragmentCommitunitListBinding;
import com.future.baselib.activity.BaseFragment;

public class CommitunitListFragment extends BaseFragment {
    @Override
    protected void getBundleExtras(Bundle bundle) {
    }

    @Nullable
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_commitunit_list;
    }


    @Override
    protected void initView(View view, @Nullable Bundle bundle) {
        FragmentCommitunitListBinding binding = DataBindingUtil.bind(view);
        binding.setViewModel(new CommitunitListFragmentVM(binding,this));
    }
}
