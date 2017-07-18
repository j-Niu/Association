package com.future.association.questionnaire;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.future.association.R;
import com.future.association.databinding.FragmentQuestionnaireBinding;
import com.future.association.questionnaire.viewmodels.QuestionViewModel;
import com.future.baselib.activity.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionnaireFragment extends BaseFragment {


    public QuestionnaireFragment() {
        // Required empty public constructor
    }


    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Nullable
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_questionnaire;
    }


    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        setTitle(R.string.questionnaire);
        FragmentQuestionnaireBinding binding = DataBindingUtil.bind(view);
        binding.setQuestionViewModel(new QuestionViewModel(this,binding));
    }

}
