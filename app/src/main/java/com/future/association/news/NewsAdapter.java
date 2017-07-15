package com.future.association.news;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.future.association.R;

import java.util.List;

/**
 * Created by jniu on 2017/7/15.
 */

public class NewsAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public NewsAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(mContext).load("https://www.baidu.com/img/baidu_jgylogo3.gif").into((ImageView) helper.getView(R.id.imageView));
        helper.setText(R.id.tv_title,"消协提醒：家电延保与三包是两回事");
        helper.setText(R.id.tv_time,"3小时前");
    }
}
