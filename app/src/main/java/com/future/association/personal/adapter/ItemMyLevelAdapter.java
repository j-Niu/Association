package com.future.association.personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.future.association.R;
import com.future.association.personal.entity.MyLevel;

import java.util.List;

public class ItemMyLevelAdapter extends BaseListAdapter {


    public ItemMyLevelAdapter(Context mContext, List mList, LayoutInflater mInflater) {
        super(mContext, mList, mInflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_my_level, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((MyLevel.MyLevels) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(MyLevel.MyLevels entity, ViewHolder holder) {
        holder.tvMyZyV1.setText(entity.level);
        holder.tvMyZy1.setText(entity.level_name);
        holder.tvMyOrg3.setText(entity.jifen);
    }

    protected class ViewHolder {
        private TextView tvMyZyV1;
        private TextView tvMyZy1;
        private TextView tvMyOrg3;

        public ViewHolder(View view) {
            tvMyZyV1 = (TextView) view.findViewById(R.id.tvMyZyV1);
            tvMyZy1 = (TextView) view.findViewById(R.id.tvMyZy1);
            tvMyOrg3 = (TextView) view.findViewById(R.id.tvMyOrg3);
        }
    }
}