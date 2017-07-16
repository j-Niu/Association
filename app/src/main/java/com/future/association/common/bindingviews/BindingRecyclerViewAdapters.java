package com.future.association.common.bindingviews;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class BindingRecyclerViewAdapters {
    // RecyclerView
    @BindingAdapter(value = {"items", "adapter"}, requireAll = true)
    public static <T> void setAdapter(RecyclerView recyclerView, List<T> items, BaseQuickAdapter<T,BaseViewHolder> adapter) {
        if (adapter == null) {
            throw new NullPointerException("adapter can not be null");
        }
        items = items==null?new ArrayList<T>():items;
        adapter.setNewData(items);
        recyclerView.setAdapter(adapter);
    }

}
