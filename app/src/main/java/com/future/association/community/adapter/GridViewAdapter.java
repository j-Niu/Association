package com.future.association.community.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.future.association.R;
import com.future.association.community.base.BaseListAdapter;
import com.future.association.community.utils.ScreenUtils;
import com.future.association.databinding.ItemGridBinding;

/**
 * Created by HX·罗 on 2017/7/3.
 */

public class GridViewAdapter extends BaseListAdapter {

    private final int width;

    public GridViewAdapter(Context context){
        super(context);
        width = ScreenUtils.getScreenWidth(context);
        datas.add("公共板块") ;
        datas.add("区域板块1") ;
        datas.add("区域板块2") ;
        datas.add("区域板块3") ;
        datas.add("区域板块4") ;
        datas.add("更多") ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_grid,null) ;

        AbsListView.LayoutParams param = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                width/3);
        convertView.setLayoutParams(param);
        ItemGridBinding binding = DataBindingUtil.bind(convertView) ;
        binding.setContent(getItem(position).toString());
        return convertView;
    }

}
