package com.future.association.supervice.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.future.association.R;

import java.util.List;

/**
 * Created by rain on 2017/7/7.
 */

public class SuperviceHeadAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public SuperviceHeadAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.tv,s);
    }
}
