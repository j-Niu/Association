package com.future.association.personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.future.association.R;
import com.future.association.personal.entity.BeanMyResponse;

import java.util.List;

/**
 * Created by javakam on 2017/7/11 0011.
 */
public class HuiYingAdapter extends BaseListAdapter {

    public HuiYingAdapter(Context mContext, List mList, LayoutInflater mInflater) {
        super(mContext, mList, mInflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_my_response, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((BeanMyResponse) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(BeanMyResponse item, ViewHolder holder) {

        holder.tvMyTitle.setText(item.getTitle());
        holder.tvMyHYBelong.setText(item.getBelongTo());
        holder.tvMyHYTime.setText(item.getTime());
    }

    protected class ViewHolder {
        private TextView tvMyTitle;
        private TextView tvMyHYBelong;
        private TextView tvMyHYTime;

        public ViewHolder(View view) {
            tvMyTitle = (TextView) view.findViewById(R.id.tvMyTitle);
            tvMyHYBelong = (TextView) view.findViewById(R.id.tvMyHYBelong);
            tvMyHYTime = (TextView) view.findViewById(R.id.tvMyHYTime);
        }
    }
}
