package com.future.association.questionnaire.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.future.association.R;
import com.future.association.databinding.FragmentMyQuestionnaireBinding;
import com.future.association.questionnaire.adapters.QuestionnaireAdapter;
import com.future.association.questionnaire.viewmodels.MyQuestionViewModel;
import com.future.baselib.activity.BaseFragment;

/**
 * Created by rain on 2017/7/5.
 */

public class MyQuestionnaireFragment extends BaseFragment {
    private QuestionnaireAdapter mAdapter;

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Nullable
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_my_questionnaire;
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        FragmentMyQuestionnaireBinding binding = DataBindingUtil.bind(view);
        binding.setMyQuestionViewModel(new MyQuestionViewModel(getActivity(),binding));

    }


}
