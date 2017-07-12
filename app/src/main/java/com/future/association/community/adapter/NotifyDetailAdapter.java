package com.future.association.community.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.future.association.R;
import com.future.association.community.model.NotifyReplyInfo;
import com.future.association.community.utils.ScreenUtils;
import com.future.association.databinding.ItemMsgDetailBinding;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/3.
 */

public class NotifyDetailAdapter extends RecyclerView.Adapter<NotifyDetailAdapter.ViewHolder> {

    private Context context ;
    private ArrayList<NotifyReplyInfo> replyInfos;
    private RecyclerView mRecycler;

    public NotifyDetailAdapter(Context context, ArrayList<NotifyReplyInfo> replyInfos) {
        this.context = context;
        this.replyInfos = replyInfos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_msg_detail,null) ;
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

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(position == replyInfos.size()-1){
            holder.binding.setIsLast(true);
        }else{
            holder.binding.setIsLast(false);
        }
        holder.binding.setReplyInfo(replyInfos.get(position));
        Glide.with(context)
                .load(replyInfos.get(position).getAvatar_url())
                .error(R.drawable.ic_demo)
                .into(holder.binding.civHead) ;
    }

    @Override
    public int getItemCount() {
        return replyInfos.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ItemMsgDetailBinding binding ;
        public ViewHolder(final View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView) ;
        }
    }
}
