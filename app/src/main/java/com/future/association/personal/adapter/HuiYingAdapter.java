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
import com.future.association.personal.entity.MyResponse;
import com.future.association.personal.ui.activity.TzDetailActivity;

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

    private void initializeViews(final MyResponse.MyResponses item, ViewHolder holder) {
        holder.tvMyTitle.setText(item.title);
        holder.tvMyHYBelong.setText(item.name);
        holder.tvMyHYTime.setText(item.create_time);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("tzid", item.tiezi_id);
                ActivityUtils.startActivityIntent(mContext, TzDetailActivity.class, bundle);
            }
        });

    }

    protected class ViewHolder {
        private LinearLayout layout;
        private TextView tvMyTitle;
        private TextView tvMyHYBelong;
        private TextView tvMyHYTime;

        public ViewHolder(View view) {
            tvMyTitle = (TextView) view.findViewById(R.id.tvMyTitle);
            tvMyHYBelong = (TextView) view.findViewById(R.id.tvMyHYBelong);
            tvMyHYTime = (TextView) view.findViewById(R.id.tvMyHYTime);
            layout= (LinearLayout) tvMyTitle.getParent();
        }
    }
}
