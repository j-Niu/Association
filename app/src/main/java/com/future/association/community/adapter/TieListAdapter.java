package com.future.association.community.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.future.association.R;
import com.future.association.community.model.TieInfo;
import com.future.association.community.utils.DateUtils;
import com.future.association.community.utils.ScreenUtils;
import com.future.association.databinding.ItemTieBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by HX·罗 on 2017/7/3.
 */

public class TieListAdapter extends RecyclerView.Adapter<TieListAdapter.ViewHolder> {

    private Context context ;
    private ArrayList<TieInfo> tieInfos;
    private OnItemClickListener itemClickListener;

    //list item 点击事件
    public interface OnItemClickListener {

        void itemClick(int position);

    }

    public TieListAdapter(Context context, ArrayList<TieInfo> tieInfos) {
        this.context = context;
        this.tieInfos = tieInfos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_tie,null) ;
        view.setLayoutParams(new RecyclerView.LayoutParams(ScreenUtils.getScreenWidth(context),
                RecyclerView.LayoutParams.WRAP_CONTENT));
        ViewHolder holder = new ViewHolder(view) ;
        return holder;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        dataSort();
        return tieInfos.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ItemTieBinding binding ;
        private int position ;
        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.itemClick(position);
                    }
                }
            });
            binding = DataBindingUtil.bind(itemView) ;
        }
        public void setData(int position){
            this.position = position ;
            binding.setTieInfo(tieInfos.get(position));
        }
    }

    /**
     * 对数据排序
     */
    public void dataSort(){
        Comparator comp = new SortComparator();
        Collections.sort(tieInfos,comp);
    }

    public class SortComparator implements Comparator {
        @Override
        public int compare(Object lhs, Object rhs) {
            TieInfo a = (TieInfo) lhs;
            TieInfo b = (TieInfo) rhs;
            int typeA = "置顶".equals(a.getType()) ?1:0;
            int typeB = "置顶".equals(b.getType()) ?1:0 ;
            long timeA = DateUtils.getStamp4Date(a.getCreate_time(),"yyyy-MM-dd HH:mm:ss") ;
            long timeB = DateUtils.getStamp4Date(b.getCreate_time(),"yyyy-MM-dd HH:mm:ss") ;
            if(typeA != typeB){
                return typeB-typeA ;
            }else{
                return (int) (timeB-timeA) ;
            }
        }
    }
}
