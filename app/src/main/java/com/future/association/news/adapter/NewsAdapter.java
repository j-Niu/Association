package com.future.association.news.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.future.association.R;
import com.future.association.common.utils.GlideUtils;
import com.future.association.news.entity.NewsResponse;

import java.util.List;

/**
 * Created by jniu on 2017/7/15.
 */

public class NewsAdapter extends BaseQuickAdapter<NewsResponse.NewsDetail,BaseViewHolder> {

    public NewsAdapter(@LayoutRes int layoutResId, @Nullable List<NewsResponse.NewsDetail> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsResponse.NewsDetail item) {
        Glide.with(mContext).load(item.image).apply(GlideUtils.defaultImg2()).into((ImageView) helper.getView(R.id.imageView));
        helper.setText(R.id.tv_title,item.title);
        helper.setText(R.id.tv_time,item.time);
    }
}
