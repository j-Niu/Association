package com.future.association.questionnaire.adapters;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.future.association.R;
import com.future.association.common.Contants;
import com.future.association.questionnaire.models.QuestionDetail;

import java.util.List;

/**
 * Created by rain on 2017/7/6.
 */

public class QuestionnaireAdapter extends BaseQuickAdapter<QuestionDetail, BaseViewHolder> {
    private int pageFrom;

    public QuestionnaireAdapter(@LayoutRes int layoutResId, @Nullable List<QuestionDetail> data, int pageFrom) {
        super(layoutResId, data);
        this.pageFrom = pageFrom;
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, QuestionDetail item) {
        viewHolder.setText(R.id.item_title_tv, item.getTitle())
                .setText(R.id.time_tv, "预计截至日期 "+item.getDead_time());
        TextView rightBottomTv = viewHolder.getView(R.id.right_bottom_tv);
        TextView rightTopTv = viewHolder.getView(R.id.right_top_tv);
        if (pageFrom == Contants.HOTQUESTIONNAI_REFRAGMENT) {
            //来自热门问卷
            viewHolder.getView(R.id.right_bottom_tv).setVisibility(View.INVISIBLE);
            rightTopTv.setText(String.format("%1$s积分", item.getJifen()));
            rightTopTv.setTextColor(mContext.getResources().getColor(R.color.basic_color));
        } else if (pageFrom == Contants.MYQUESTIONNAI_REFRAGMENT) {
            //来自我的问卷
            rightTopTv.setText(String.format("%1$s积分", item.getJifen()));
            rightTopTv.setTextColor(mContext.getResources().getColor(R.color.basic_color));
            String type = item.getType();

            if ("5".equals(type)) {
                String status = item.getStatus();
                if ("1".equals(status)) {
                    rightBottomTv.setText("已完成");
                    rightBottomTv.setTextColor(Color.LTGRAY);
                } else {
                    rightBottomTv.setTextColor(Color.parseColor("#faa701"));
                    rightBottomTv.setText("进行中");
                }
            }else if ("3".equals(type)){
                rightBottomTv.setTextColor(Color.LTGRAY);
                rightBottomTv.setText("已过期");
            }
        }
    }
}
