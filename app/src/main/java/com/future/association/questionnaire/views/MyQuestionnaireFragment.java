package com.future.association.questionnaire.views;

import android.content.Intent;
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
import com.future.association.questionnaire.adapters.QuestionnaireAdapter;
import com.future.baselib.activity.BaseFragment;
import com.future.baselib.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

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
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mAdapter = new QuestionnaireAdapter(R.layout.questionnaire_item, null, Contants.MYQUESTIONNAI_REFRAGMENT);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (!CommonUtils.isFastDoubleClick()) {
                    Intent intent = new Intent(getActivity(), QuestionnaireWebActivity.class);
                    startActivity(intent);
                }
            }
        });
        testData();
    }

    private void testData() {
        List<String> test = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            test.add("的身边发生的思考角度思考的酒吧看似简单" + i);
        }
        mAdapter.setNewData(test);
    }


}
