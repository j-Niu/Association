package com.future.association.questionnaire;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.future.association.R;
import com.future.association.common.Contants;

import java.util.List;

/**
 * Created by rain on 2017/7/6.
 */

public class QuestionnaireAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private  int pageFrom;

    public QuestionnaireAdapter(@LayoutRes int layoutResId, @Nullable List<String> data, int pageFrom) {
        super(layoutResId, data);
        this.pageFrom = pageFrom;
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, String s) {
        viewHolder.setText(R.id.item_title_tv,s);
        if (pageFrom == Contants.HOTQUESTIONNAI_REFRAGMENT){
            //来自热门问卷
            viewHolder.getView(R.id.right_top_tv).setVisibility(View.GONE);
        }else if (pageFrom == Contants.MYQUESTIONNAI_REFRAGMENT){
            //来自我的问卷
        }
    }
}
