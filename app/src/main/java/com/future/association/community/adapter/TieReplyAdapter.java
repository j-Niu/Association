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

import com.future.association.R;
import com.future.association.community.model.TieReplyInfo;
import com.future.association.community.utils.ActivityUtils;
import com.future.association.community.utils.DialogUtils;
import com.future.association.community.utils.ScreenUtils;
import com.future.association.community.view.WeiGuiActivity;
import com.future.association.databinding.ItemReplyDetailBinding;
import com.future.association.databinding.PopupTie1Binding;

import java.util.ArrayList;

import static com.future.association.R.*;
import static com.future.association.R.layout.*;

/**
 * Created by HX·罗 on 2017/7/3.
 */

public class TieReplyAdapter extends RecyclerView.Adapter<TieReplyAdapter.ViewHolder> {

    private Context context ;
    private ArrayList<TieReplyInfo> tieReplyInfos;
    private RecyclerView mRecycler;

    public TieReplyAdapter(Context context, ArrayList<TieReplyInfo> tieReplyInfos) {
        this.context = context;
        this.tieReplyInfos = tieReplyInfos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, item_reply_detail,null) ;
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
        if(position == tieReplyInfos.size()-1){
            holder.binding.setIsLast(true);
        }else{
            holder.binding.setIsLast(false);
        }
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return tieReplyInfos.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ItemReplyDetailBinding binding ;
        private PopupWindow popupWindow;
        private PopupTie1Binding popupTie1Binding;
        private View view;
        private int position ;

        public ViewHolder(final View itemView) {
            super(itemView);
            initView();
            initListener() ;
        }

        /**
         * 设置数据
         * @param position
         */
        public void setData(int position){
            binding.setReplyInfo(tieReplyInfos.get(position)) ;
            this.position = position ;
        }

        private void initView() {
            binding = DataBindingUtil.bind(itemView) ;
            view = View.inflate(context, popup_tie1,null);
            popupTie1Binding = DataBindingUtil.bind(view);
        }

        private void initListener() {
            binding.setClickListener(this);
            popupTie1Binding.setClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case id.iv_more:
                    showPopupWindow();
                    break ;
                case id.tv_del:
                    popupWindow.dismiss();
                    DialogUtils.showDialog4View(context, dialog_sure, id.tv_positive,
                            id.tv_nagtive, "删除该回复？",new DialogUtils.EditContentDialogListener() {
                        @Override
                        public void positive(String content) {
                            tieReplyInfos.remove(position) ;
                            notifyItemRemoved(position);
                        }

                        @Override
                        public void nagtive() {

                        }
                    });
                    break;
                case id.tv_weigui:
                    popupWindow.dismiss();
                    ActivityUtils.startActivityIntent(context, WeiGuiActivity.class);
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
            popupWindow.showAsDropDown(binding.ivMore,0,10);
        }
    }
}
