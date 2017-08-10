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
import com.future.association.personal.entity.MyTiezi;
import com.future.association.personal.ui.activity.TzDetailActivity;

import java.util.List;

/**
 * Created by javakam on 2017/7/11 0011.
 */
public class TieziAdapter extends BaseListAdapter {

    public TieziAdapter(Context mContext, List mList, LayoutInflater mInflater) {
        super(mContext, mList, mInflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_my_tiezi, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((MyTiezi.MyTiezis) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(final MyTiezi.MyTiezis item, ViewHolder holder) {

        holder.tvMyTitle.setText(item.title);
        holder.tvMyHYBelong.setText(item.name);
        holder.tvMyHYHuifu.setText(item.huifu_num);
        holder.tvMyHYTime.setText(item.create_time);
        /*
         public String id;
        public String title;
        public String create_time;
        public String name;
        public String huifu_num;
         */
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
//                bundle.putParcelable("tieInfo", tieInfos.get(position));
//                bundle.putParcelable("plateInfo", plateInfo);
//                bundle.putString("jifen", userPlateInfo.getJifen());
//                bundle.putSerializable("tieInfo", item);
//                bundle.putSerializable("plateInfo", "");
//                bundle.putSerializable("jifen", "3000");
//                Intent intent = new Intent(mContext, TieDetailActivity.class);
//                intent.setFlags(121);
//                mContext.startActivity(intent, bundle);
                bundle.putString("tzid", item.id);
                ActivityUtils.startActivityIntent(mContext, TzDetailActivity.class, bundle);
            }
        });
    }

    protected class ViewHolder {
        private LinearLayout layout;
        private TextView tvMyTitle;
        private TextView tvMyHYBelong;
        private TextView tvMyHYHuifu;
        private TextView tvMyHYTime;

        public ViewHolder(View view) {
            tvMyTitle = (TextView) view.findViewById(R.id.tvMyTitle);
            tvMyHYBelong = (TextView) view.findViewById(R.id.tvMyHYBelong);
            tvMyHYHuifu = (TextView) view.findViewById(R.id.tvMyHYHuifu);
            tvMyHYTime = (TextView) view.findViewById(R.id.tvMyHYTime);
            layout = (LinearLayout) tvMyTitle.getParent();
        }
    }
}
