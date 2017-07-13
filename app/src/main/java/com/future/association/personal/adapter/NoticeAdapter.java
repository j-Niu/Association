package com.future.association.personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.future.association.R;
import com.future.association.personal.entity.BeanNotice;

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
        initializeViews((BeanNotice) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(BeanNotice item, ViewHolder holder) {
        holder.tvMyTitle.setText(item.getTitle());
        holder.tvMyPartName.setText(item.getPartName());
        holder.tvMyLasttime.setText(item.getLasttime());
    }

    protected class ViewHolder {
        private TextView tvMyTitle;
        private TextView tvMyPartName;
        private TextView tvMyLasttime;

        public ViewHolder(View view) {
            tvMyTitle = (TextView) view.findViewById(R.id.tvMyTitle);
            tvMyPartName = (TextView) view.findViewById(R.id.tvMyPartName);
            tvMyLasttime = (TextView) view.findViewById(R.id.tvMyLasttime);
        }
    }
}