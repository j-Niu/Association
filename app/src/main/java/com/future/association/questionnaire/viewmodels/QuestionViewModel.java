package com.future.association.questionnaire.viewmodels;

import android.support.v4.app.Fragment;

import com.future.association.R;
import com.future.association.databinding.FragmentQuestionnaireBinding;
import com.future.association.questionnaire.views.HotQuestionnaireFragment;
import com.future.association.questionnaire.views.MyQuestionnaireFragment;
import com.future.baselib.adapter.ViewPageFragmentAdapter;

/**
 * Created by chenyu on 2017/7/18.
 */

public class QuestionViewModel {
    private final Fragment fragment;
    private final FragmentQuestionnaireBinding mBinding;

    public QuestionViewModel(Fragment fragment, FragmentQuestionnaireBinding binding) {
        this.fragment = fragment;
        this.mBinding = binding;
        initView();
        initData();
    }

    private void initView() {
        ViewPageFragmentAdapter mTabsAdapter = new ViewPageFragmentAdapter(fragment.getActivity().getSupportFragmentManager(), mBinding.pstsTabs, mBinding.viewPager);

        mTabsAdapter.addTab(fragment.getString(R.string.hot_questionnaire),"HotQuestionnaireFragment",HotQuestionnaireFragment.class,null);
        mTabsAdapter.addTab(fragment.getString(R.string.my_questionnaire),"MyQuestionnaireFragment",MyQuestionnaireFragment.class,null);

        mTabsAdapter.notifyDataSetChanged();
    }

    private void initData() {

    }
}
