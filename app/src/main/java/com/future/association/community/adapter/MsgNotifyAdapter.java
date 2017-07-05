package com.future.association.community.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.future.association.R;
import com.future.association.community.model.MsgNotifyInfo;
import com.future.association.community.utils.ScreenUtils;
import com.future.association.databinding.ItemMsgNotifyBinding;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/3.
 */

public class MsgNotifyAdapter extends RecyclerView.Adapter<MsgNotifyAdapter.ViewHolder> {

    private Context context ;
    private ArrayList<MsgNotifyInfo> notifyInfos ;
    private OnItemClickListener itemClickListener;
    private RecyclerView mRecycler;

    //list item 点击事件
    public interface OnItemClickListener {

        public void itemClick(int position);

    }
    public MsgNotifyAdapter(Context context,ArrayList<MsgNotifyInfo> notifyInfos) {
        this.context = context;
        this.notifyInfos = notifyInfos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_msg_notify,null) ;
        view.setLayoutParams(new RecyclerView.LayoutParams(ScreenUtils.getScreenWidth(context),
                RecyclerView.LayoutParams.WRAP_CONTENT));
        ViewHolder holder = new ViewHolder(view) ;
        return holder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecycler = recyclerView;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.binding.setNotifyInfo(notifyInfos.get(position));
    }

    @Override
    public int getItemCount() {
        return notifyInfos.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ItemMsgNotifyBinding binding ;
        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.itemClick(getRealPosition(itemView));
                    }
                }
            });
            binding = DataBindingUtil.bind(itemView) ;
        }
    }
    public int getRealPosition(View view) {
        return mRecycler.getChildLayoutPosition(view);
    }
}
