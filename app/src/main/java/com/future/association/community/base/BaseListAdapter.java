package com.future.association.community.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/5 0005.
 */
public abstract class BaseListAdapter<T> extends BaseAdapter{
    public Context context ;
    public ArrayList<T> datas ;
    public LayoutInflater inflater ;
    public BaseListAdapter(Context context){
        this.context = context ;
        inflater = LayoutInflater.from(context) ;
        datas = new ArrayList<T>() ;
    }

    @Override
    public int getCount() {
        return datas.size();
    }
    @Override
    public T getItem(int position) {
        return datas.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
}
