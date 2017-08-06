package com.future.association.personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.future.association.R;
import com.future.association.personal.entity.MyNotification;

import java.util.List;

/**
 * Created by javakam on 2017/7/11 0011.
 */
public class TongzhiAdapter extends BaseListAdapter {

    public TongzhiAdapter(Context mContext, List mList, LayoutInflater mInflater) {
        super(mContext, mList, mInflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_my_tongzhi, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((MyNotification.MyNotifications) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(final MyNotification.MyNotifications item, ViewHolder holder) {
        holder.tvMyTitle.setText(item.getTitle());
        holder.tvMyTongzhiTime.setText(item.getFrom());
//        holder.layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("notifyInfo", item);
//                ActivityUtils.startActivityIntent(mContext, NotifyDetailActivity.class, bundle);
//            }
//        });
    }

    protected class ViewHolder {
        private LinearLayout layout;
        private TextView tvMyTitle;
        private TextView tvMyTongzhiTime;

        public ViewHolder(View view) {
            tvMyTitle = (TextView) view.findViewById(R.id.tvMyTitle);
            tvMyTongzhiTime = (TextView) view.findViewById(R.id.tvMyTongzhiTime);
            layout = (LinearLayout) tvMyTitle.getParent();
        }
    }
}