package com.future.association.community.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.future.association.R;
import com.future.association.common.utils.GlideUtils;
import com.future.association.common.MyApp;
import com.future.association.community.model.TieReplyInfo;
import com.future.association.community.utils.DateUtils;
import com.future.association.community.utils.DialogUtils;
import com.future.association.community.utils.ScreenUtils;
import com.future.association.databinding.ItemReplyDetailBinding;
import com.future.association.databinding.PopupTie1Binding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.future.association.R.*;
import static com.future.association.R.layout.*;

/**
 * Created by HX·罗 on 2017/7/3.
 */

public class TieReplyAdapter extends RecyclerView.Adapter<TieReplyAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;

    public interface Callback {
        void delReply(int position);

        void weigui(int position);
    }

    private Context context;
    public ArrayList<TieReplyInfo> tieReplyInfos;
    private RecyclerView mRecycler;
    private Callback callback;
    private View mHeadView = null;

    public TieReplyAdapter(Context context, ArrayList<TieReplyInfo> tieReplyInfos) {
        this.context = context;
        this.tieReplyInfos = tieReplyInfos;
    }

    public void setmHeadView(View mHeadView) {
        this.mHeadView = mHeadView;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeadView != null && viewType == TYPE_NORMAL) {
            View view = View.inflate(context, R.layout.item_reply_detail, null);
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

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(position > 0){
            if (position-1 == tieReplyInfos.size() - 1) {
                holder.binding.setIsLast(true);
            } else {
                holder.binding.setIsLast(false);
            }
            holder.setData(position-1);
        }
    }

    @Override
    public int getItemCount() {
        dataSort();
        return tieReplyInfos.size()+1;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemReplyDetailBinding binding;
        private PopupWindow popupWindow;
        private PopupTie1Binding popupTie1Binding;
        private View view;
        private int position;

        public ViewHolder(final View itemView) {
            super(itemView);
            initView();
            initListener();
        }

        /**
         * 设置数据
         *
         * @param position
         */
        public void setData(int position) {
            binding.setReplyInfo(tieReplyInfos.get(position));
            Glide.with(context)
                    .load(tieReplyInfos.get(position).getAvatar_url())
                    .apply(GlideUtils.defaultImg())
                    .into(binding.civHead);
            this.position = position;
        }

        private void initView() {
            try {
                binding = DataBindingUtil.bind(itemView);
                binding.setHaveQuan(MyApp.isAdministrator());
            } catch (Exception e) {
                e.printStackTrace();
            }
            view = View.inflate(context, popup_tie1, null);
            popupTie1Binding = DataBindingUtil.bind(view);
        }

        private void initListener() {
            if (binding != null) {
                binding.setClickListener(this);
            }
            popupTie1Binding.setClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case id.iv_more:
                    showPopupWindow();
                    break;
                case id.tv_del:
                    popupWindow.dismiss();
                    DialogUtils.showDialog4View(context, dialog_sure, id.tv_positive,
                            id.tv_nagtive, "删除该回复？", new DialogUtils.EditContentDialogListener() {
                                @Override
                                public void positive(String content) {
                                    callback.delReply(position);
                                }

                                @Override
                                public void nagtive() {

                                }
                            });
                    break;
                case id.tv_weigui:
                    popupWindow.dismiss();
                    callback.weigui(position);

                    break;
            }
        }

        private void showPopupWindow() {
            if (popupWindow == null) {
                popupWindow = new PopupWindow(context);
                popupWindow.setContentView(view);
                popupWindow.setFocusable(true);
                popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

                popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                popupWindow.setTouchable(true);
                popupWindow.setTouchInterceptor(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });

                popupWindow.setBackgroundDrawable(new BitmapDrawable());
            }
            popupWindow.showAsDropDown(binding.ivMore, 0, 10);
        }
    }

    /**
     * 对数据排序
     */
    public void dataSort() {
        Comparator comp = new SortComparator();
        Collections.sort(tieReplyInfos, comp);
    }

    public class SortComparator implements Comparator {
        @Override
        public int compare(Object lhs, Object rhs) {
            TieReplyInfo a = (TieReplyInfo) lhs;
            TieReplyInfo b = (TieReplyInfo) rhs;
            long timeA = DateUtils.getStamp4Date(a.getCreate_time(), "yyyy-MM-dd HH:mm:ss");
            long timeB = DateUtils.getStamp4Date(b.getCreate_time(), "yyyy-MM-dd HH:mm:ss");
            return (int) (timeB - timeA);
        }
    }
}
