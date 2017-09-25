package com.future.association.community.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.bumptech.glide.Glide;
import com.future.association.R;
import com.future.association.common.utils.GlideUtils;
import com.future.association.community.base.BaseListAdapter;
import com.future.association.community.model.PlateInfo;
import com.future.association.community.utils.Res;
import com.future.association.community.utils.ScreenUtils;
import com.future.association.databinding.ItemGridBinding;

/**
 * Created by HX·罗 on 2017/7/3.
 */

public class GridViewAdapter extends BaseListAdapter<PlateInfo> {

    private final int width;
    private int limitCount = 5 ;

    public GridViewAdapter(Context context) {
        super(context);
        width = ScreenUtils.getScreenWidth(context);
    }

    @Override
    public int getCount() {
        return super.getCount() > limitCount ? limitCount+1 : super.getCount();
    }

    public void setLimitCount(int limitCount) {
        this.limitCount = limitCount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_grid, null);

        AbsListView.LayoutParams param = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                width / 3);
        convertView.setLayoutParams(param);
        ItemGridBinding binding = DataBindingUtil.bind(convertView);
        if (getCount() > limitCount && position == limitCount) {
            binding.ivImg.setImageDrawable(Res.getDrawableRes(R.drawable.ic_more_plate,context));
            binding.setContent("更多");
        } else {
            binding.setContent(getItem(position).getName());
            Glide.with(context)
                    .load(datas.get(position).getImage())
                    .apply(GlideUtils.defaultImg())
                    .into(binding.ivImg);
        }
        return convertView;
    }

}
