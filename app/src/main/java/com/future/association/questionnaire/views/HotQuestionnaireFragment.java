package com.future.association.questionnaire.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.future.association.R;
import com.future.association.common.EventCode;
import com.future.association.databinding.FragmentHotQuestionnaireBinding;
import com.future.association.questionnaire.viewmodels.HotQuestionViewModel;
import com.future.baselib.activity.BaseFragment;
import com.future.baselib.entity.MessageEvent;

/**
 * Created by rain on 2017/7/5.
 */

public class HotQuestionnaireFragment extends BaseFragment {

    private HotQuestionViewModel mHotQuestionViewModel;

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
        mHotQuestionViewModel = new HotQuestionViewModel(getActivity(), binding);
        binding.setHotQuestionViewModel(mHotQuestionViewModel);


    }
    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveEvent(MessageEvent event) {
        if (event.getCode() == EventCode.QUESTION_LIST_REFRESH){
            mHotQuestionViewModel.refresh();
        }
    }

}
