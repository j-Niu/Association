package com.future.association.community.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.future.association.R;
import com.future.association.community.model.MsgNotifyInfo;
import com.future.association.community.model.TieInfo;
import com.future.association.community.utils.DateUtils;
import com.future.association.community.utils.ScreenUtils;
import com.future.association.databinding.ItemMsgNotifyBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by HX·罗 on 2017/7/3.
 */

public class MsgNotifyAdapter extends RecyclerView.Adapter<MsgNotifyAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private Context context;
    private ArrayList<MsgNotifyInfo> notifyInfos;
    private OnItemClickListener itemClickListener;
    private RecyclerView mRecycler;
    private View mHeadView = null;

    public void setmHeadView(View mHeadView) {
        this.mHeadView = mHeadView;
    }

    //list item 点击事件
    public interface OnItemClickListener {

        public void itemClick(int position);

    }

    public MsgNotifyAdapter(Context context, ArrayList<MsgNotifyInfo> notifyInfos) {
        this.context = context;
        this.notifyInfos = notifyInfos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeadView != null && viewType == TYPE_NORMAL) {
            View view = View.inflate(context, R.layout.item_msg_notify, null);
            view.setLayoutParams(new RecyclerView.LayoutParams(ScreenUtils.getScreenWidth(context),
                    RecyclerView.LayoutParams.WRAP_CONTENT));
            ViewHolder holder = new ViewHolder(view);
            return holder;
        } else {
            return new ViewHolder(mHeadView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeadView != null && position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_NORMAL;
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
        if (position > 0) {
            holder.setData(position);
        }
    }

    /**
     * 获取某item
     *
     * @param position
     * @return
     */
    public MsgNotifyInfo getItem(int position) {
        return notifyInfos.get(position);
    }

    @Override
    public int getItemCount() {
        dataSort();
        return notifyInfos.size()+1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemMsgNotifyBinding binding;
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
            try {
                binding = DataBindingUtil.bind(itemView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void setData(int position) {
            this.position = position -1 ;
            binding.setNotifyInfo(notifyInfos.get(position-1));
        }
    }

    /**
     * 对数据排序
     */
    public void dataSort() {
        Comparator comp = new SortComparator();
        Collections.sort(notifyInfos, comp);
    }

    public class SortComparator implements Comparator {
        @Override
        public int compare(Object lhs, Object rhs) {
            MsgNotifyInfo a = (MsgNotifyInfo) lhs;
            MsgNotifyInfo b = (MsgNotifyInfo) rhs;
            long timeA = DateUtils.getStamp4Date(a.getCreate_time(), "yyyy-MM-dd HH:mm:ss");
            long timeB = DateUtils.getStamp4Date(b.getCreate_time(), "yyyy-MM-dd HH:mm:ss");
            return (int) (timeB - timeA);
        }
    }
}
