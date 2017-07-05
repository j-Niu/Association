package com.future.association.community.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.future.association.R;
import com.future.association.community.model.BannerInfo;
import com.future.association.community.utils.ScreenUtils;
import com.future.association.databinding.ItemBannerBinding;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/3.
 */

public class BannersAdapter extends RecyclerView.Adapter<BannersAdapter.ViewHolder> {

    private Context context ;
    private ArrayList<BannerInfo> bannerInfos;
    private OnItemClickListener itemClickListener;

    //list item 点击事件
    public interface OnItemClickListener {

        void itemClick(int position);

    }
    public BannersAdapter(Context context, ArrayList<BannerInfo> bannerInfos) {
        this.context = context;
        this.bannerInfos = bannerInfos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_banner,null) ;
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
        return bannerInfos.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ItemBannerBinding binding ;
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
            binding.setBannerInfo(bannerInfos.get(position));
        }
    }
}
