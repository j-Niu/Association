package com.future.association.community.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.future.association.R;
import com.future.association.common.utils.GlideUtils;
import com.future.association.community.model.NotifyReplyInfo;
import com.future.association.community.utils.DateUtils;
import com.future.association.community.utils.ScreenUtils;
import com.future.association.databinding.ItemMsgDetailBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by HX·罗 on 2017/7/3.
 */

public class NotifyDetailAdapter extends RecyclerView.Adapter<NotifyDetailAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private Context context;
    private ArrayList<NotifyReplyInfo> replyInfos;
    private RecyclerView mRecycler;
    private View mHeadView = null;

    public NotifyDetailAdapter(Context context, ArrayList<NotifyReplyInfo> replyInfos) {
        this.context = context;
        this.replyInfos = replyInfos;
    }

    public void setmHeadView(View mHeadView) {
        this.mHeadView = mHeadView;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeadView != null && position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeadView != null && viewType == TYPE_NORMAL) {
            View view = View.inflate(context, R.layout.item_msg_detail, null);
            view.setLayoutParams(new RecyclerView.LayoutParams(ScreenUtils.getScreenWidth(context),
                    RecyclerView.LayoutParams.WRAP_CONTENT));
            ViewHolder holder = new ViewHolder(view);
            return holder;
        } else {
            return new ViewHolder(mHeadView);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecycler = recyclerView;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position > 0) {
            if (position - 1 == replyInfos.size() - 1) {
                holder.binding.setIsLast(true);
            } else {
                holder.binding.setIsLast(false);
            }
            holder.binding.setReplyInfo(replyInfos.get(position - 1));
            Glide.with(context)
                    .load(replyInfos.get(position - 1).getAvatar_url())
                    .apply(GlideUtils.defaultImg())
                    .into(holder.binding.civHead);
        }
    }

    @Override
    public int getItemCount() {
        dataSort();
        return replyInfos.size() + 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemMsgDetailBinding binding;

        public ViewHolder(final View itemView) {
            super(itemView);
            try {
                binding = DataBindingUtil.bind(itemView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对数据排序
     */
    public void dataSort() {
        Comparator comp = new SortComparator();
        Collections.sort(replyInfos, comp);
    }

    public class SortComparator implements Comparator {
        @Override
        public int compare(Object lhs, Object rhs) {
            NotifyReplyInfo a = (NotifyReplyInfo) lhs;
            NotifyReplyInfo b = (NotifyReplyInfo) rhs;
            long timeA = DateUtils.getStamp4Date(a.getCreate_time(), "yyyy-MM-dd HH:mm:ss");
            long timeB = DateUtils.getStamp4Date(b.getCreate_time(), "yyyy-MM-dd HH:mm:ss");
            return (int) (timeB - timeA);
        }
    }
}
