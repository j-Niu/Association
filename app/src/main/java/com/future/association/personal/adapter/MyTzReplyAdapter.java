package com.future.association.personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.future.association.R;
import com.future.association.personal.CircleImageView;
import com.future.association.personal.entity.MyTzReplyInfo;

import java.util.List;

public class MyTzReplyAdapter extends BaseListAdapter {

    public MyTzReplyAdapter(Context mContext, List mList, LayoutInflater mInflater) {
        super(mContext, mList, mInflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_tz_reply_detail, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((MyTzReplyInfo.MyTzReplyInfos) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(MyTzReplyInfo.MyTzReplyInfos entity, ViewHolder holder) {
        Glide.with(mContext).asBitmap().load(entity.getAvatar_url()).into(holder.civHead);
        holder.tvRealName.setText(entity.getReal_name());
        holder.tvClass.setText(entity.getLevel());
        holder.ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.tvAddress.setText(entity.getAddress());
        holder.tvContent.setText(entity.getContent());
    }

    protected class ViewHolder {
        private CircleImageView civHead;
        private TextView tvRealName;
        private TextView tvClass;
        private ImageView ivMore;
        private TextView tvAddress;
        private TextView tvContent;

        public ViewHolder(View view) {
            civHead = (CircleImageView) view.findViewById(R.id.civ_head);
            tvRealName = (TextView) view.findViewById(R.id.tv_real_name);
            tvClass = (TextView) view.findViewById(R.id.tv_class);
            ivMore = (ImageView) view.findViewById(R.id.iv_more);
            tvAddress = (TextView) view.findViewById(R.id.tv_address);
            tvContent = (TextView) view.findViewById(R.id.tv_content);
        }
    }
}