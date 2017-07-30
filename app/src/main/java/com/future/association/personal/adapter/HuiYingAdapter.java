package com.future.association.personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.future.association.R;
import com.future.association.personal.entity.MyResponse;

import java.util.List;

/**
 * Created by javakam on 2017/7/11 0011.
 */
public class HuiYingAdapter extends BaseListAdapter {

    public HuiYingAdapter(Context mContext, List<MyResponse.MyResponses> mList, LayoutInflater mInflater) {
        super(mContext, mList, mInflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_my_response, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((MyResponse.MyResponses) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(MyResponse.MyResponses item, ViewHolder holder) {
        holder.tvMyTitle.setText(item.title);
        holder.tvMyHYBelong.setText(item.name);
        holder.tvMyHYTime.setText(item.create_time);
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
