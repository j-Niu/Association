package com.future.association.questionnaire.adapters;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.future.association.R;
import com.future.association.common.Contants;
import com.future.association.questionnaire.models.QuestionList;

import java.util.List;

/**
 * Created by rain on 2017/7/6.
 */

public class QuestionnaireAdapter extends BaseQuickAdapter<QuestionList,BaseViewHolder> {
    private  int pageFrom;

    public QuestionnaireAdapter(@LayoutRes int layoutResId, @Nullable List<QuestionList> data, int pageFrom) {
        super(layoutResId, data);
        this.pageFrom = pageFrom;
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, QuestionList item) {
        viewHolder.setText(R.id.item_title_tv,item.getTitle())
                .setText(R.id.item_title_tv,item.getTime());
        if (pageFrom == Contants.HOTQUESTIONNAI_REFRAGMENT){
            //来自热门问卷
            viewHolder.getView(R.id.right_top_tv).setVisibility(View.GONE);
        }else if (pageFrom == Contants.MYQUESTIONNAI_REFRAGMENT){
            //来自我的问卷
        }
    }
}
