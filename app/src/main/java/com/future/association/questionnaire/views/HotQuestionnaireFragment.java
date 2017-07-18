package com.future.association.questionnaire.views;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.future.association.R;
import com.future.association.common.Contants;
import com.future.association.databinding.FragmentHotQuestionnaireBinding;
import com.future.association.questionnaire.adapters.QuestionnaireAdapter;
import com.future.association.questionnaire.viewmodels.HotQuestionViewModel;
import com.future.baselib.activity.BaseFragment;
import com.future.baselib.utils.CommonUtils;

/**
 * Created by rain on 2017/7/5.
 */

public class HotQuestionnaireFragment extends BaseFragment {

    private QuestionnaireAdapter mAdapter;

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Nullable
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_hot_questionnaire;
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        FragmentHotQuestionnaireBinding binding = DataBindingUtil.bind(view);
        binding.setHotQuestionViewModel(new HotQuestionViewModel(getActivity(),binding));


    }


}
