package com.future.association.questionnaire;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.future.association.R;
import com.future.baselib.activity.BaseFragment;
import com.future.baselib.adapter.ViewPageFragmentAdapter;
import com.future.baselib.view.PagerSlidingTabStrip;

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
        DataBindingUtil.bind(view);
        PagerSlidingTabStrip slidingTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.psts_tabs);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        ViewPageFragmentAdapter mTabsAdapter = new ViewPageFragmentAdapter(getActivity().getSupportFragmentManager(), slidingTabStrip, viewPager);

        mTabsAdapter.addTab(getString(R.string.hot_questionnaire),"HotQuestionnaireFragment",HotQuestionnaireFragment.class,null);
        mTabsAdapter.addTab(getString(R.string.hot_questionnaire),"MyQuestionnaireFragment",MyQuestionnaireFragment.class,null);

        mTabsAdapter.notifyDataSetChanged();
//        viewPager.setOffscreenPageLimit(2);
    }

}
