package com.future.association.questionnaire;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.future.association.R;
import com.future.association.databinding.ActivityQuestionnaireEndBinding;
import com.future.baselib.activity.BaseActivity;

/**
 * Created by rain on 2017/7/7.
 */

public class QuestionnaireEndActivity extends BaseActivity {
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        ActivityQuestionnaireEndBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_questionnaire_end);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
