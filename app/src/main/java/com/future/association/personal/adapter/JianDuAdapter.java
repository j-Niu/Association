package com.future.association.personal.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.future.association.R;
import com.future.association.personal.entity.MyJianDu;
import com.future.association.supervice.view.SuperviceDetailActivity;
import com.future.baselib.utils.CommonUtils;

import java.util.List;

/**
 * Created by javakam on 2017/7/10 0010.
 */
public class JianDuAdapter extends BaseListAdapter {
    public JianDuAdapter(Context mContext, List mList, LayoutInflater mInflater) {
        super(mContext, mList, mInflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_my_jiandu, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((MyJianDu.MyJianDus) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(final MyJianDu.MyJianDus entity, ViewHolder holder) {
        Glide.with(mContext).load(entity.getImage()).into(holder.imgMyJD);
//        holder.imgMyJD.setImageResource(R.drawable.ic_birds);
        holder.tvMyJDTitle.setText(entity.getTitle());
        holder.tvMyJDCreatetime.setText(entity.getCreate_time());
        holder.tvMyJDType.setText(entity.getType());
        holder.tvMyJDAddress.setText(entity.getAddress());
        holder.tvMyJDContent.setText(entity.getReason());
        holder.linearJiandu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳往详情页面
                if (!CommonUtils.isFastDoubleClick()) {
                    Intent intent = new Intent(mContext, SuperviceDetailActivity.class);
                    intent.putExtra("id", entity.getId());
                    mContext.startActivity(intent);
                }
            }
        });
    }

    protected class ViewHolder {
        private LinearLayout linearJiandu;
        private ImageView imgMyJD;
        private TextView tvMyJDTitle;
        private TextView tvMyJDCreatetime;
        private TextView tvMyJDType;
        private TextView tvMyJDAddress;
        private TextView tvMyJDContent;

        public ViewHolder(View view) {
            linearJiandu = (LinearLayout) view.findViewById(R.id.linearJiandu);
            imgMyJD = (ImageView) view.findViewById(R.id.img_myJD);
            tvMyJDTitle = (TextView) view.findViewById(R.id.tv_myJD_title);
            tvMyJDCreatetime = (TextView) view.findViewById(R.id.tv_myJD_createtime);
            tvMyJDType = (TextView) view.findViewById(R.id.tv_myJD_type);
            tvMyJDAddress = (TextView) view.findViewById(R.id.tv_myJD_address);
            tvMyJDContent = (TextView) view.findViewById(R.id.tv_myJD_content);
        }
    }
}