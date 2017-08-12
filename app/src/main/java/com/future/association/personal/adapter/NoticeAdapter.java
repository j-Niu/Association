package com.future.association.personal.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.future.association.R;
import com.future.association.community.utils.ActivityUtils;
import com.future.association.personal.entity.MyNotice;
import com.future.association.personal.ui.activity.TzDetailActivity;

import java.util.List;

/**
 * Created by javakam on 2017/7/11 0011.
 */
public class NoticeAdapter extends BaseListAdapter {

    public NoticeAdapter(Context mContext, List mList, LayoutInflater mInflater) {
        super(mContext, mList, mInflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_my_notice, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((MyNotice.MyNotices) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(final MyNotice.MyNotices item, ViewHolder holder) {
        holder.tvMyTitle.setText(item.getTitle());
        holder.tvMyPartName.setText(item.getName());
        holder.tvMyLasttime.setText(item.getCreate_time());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("tzid", item.getId());
                ActivityUtils.startActivityIntent(mContext, TzDetailActivity.class, bundle);
            }
        });
    }

    protected class ViewHolder {
        private LinearLayout layout;
        private TextView tvMyTitle;
        private TextView tvMyPartName;
        private TextView tvMyLasttime;

        public ViewHolder(View view) {
            tvMyTitle = (TextView) view.findViewById(R.id.tvMyTitle);
            tvMyPartName = (TextView) view.findViewById(R.id.tvMyPartName);
            tvMyLasttime = (TextView) view.findViewById(R.id.tvMyLasttime);
            layout = (LinearLayout) tvMyTitle.getParent();
        }
    }
}