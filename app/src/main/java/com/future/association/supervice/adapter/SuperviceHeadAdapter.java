package com.future.association.supervice.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.future.association.R;
import com.future.association.supervice.model.SupericerTypeList;

import java.util.List;

/**
 * Created by rain on 2017/7/7.
 */

public class SuperviceHeadAdapter extends BaseQuickAdapter<SupericerTypeList.SupericerTypeInfo,BaseViewHolder> {
    public SuperviceHeadAdapter(@LayoutRes int layoutResId, @Nullable List<SupericerTypeList.SupericerTypeInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SupericerTypeList.SupericerTypeInfo item) {
        baseViewHolder.setText(R.id.tv,item.getHangye());
    }
}
