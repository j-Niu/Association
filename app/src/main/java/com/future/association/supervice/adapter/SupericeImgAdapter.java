package com.future.association.supervice.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.future.association.R;
import com.future.association.common.GlideUtils;
import com.future.association.community.utils.TextUtil;

import java.io.File;
import java.util.List;

/**
 * Created by rain on 2017/7/23.
 */

public class SupericeImgAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public SupericeImgAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String item) {
        ImageView iv = baseViewHolder.getView(R.id.iv);
        if (!TextUtil.isEmpty(item)){
            item = item.replace("\\/", File.separator);
            Glide.with(mContext).load(item).apply(GlideUtils.defaultImg()).into(iv);
        }
    }
}
