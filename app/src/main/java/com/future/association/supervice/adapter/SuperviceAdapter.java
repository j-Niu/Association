package com.future.association.supervice.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.future.association.R;
import com.future.association.supervice.model.SupericeList;

import java.io.File;
import java.util.List;

/**
 * Created by rain on 2017/7/7.
 */

public class SuperviceAdapter extends BaseQuickAdapter<SupericeList.SupericeListInfo,BaseViewHolder> {
    public SuperviceAdapter(@LayoutRes int layoutResId, @Nullable List<SupericeList.SupericeListInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SupericeList.SupericeListInfo item) {
        ImageView imageView = baseViewHolder.getView(R.id.iv);
        String image = item.getImage();
        image = image.replace("\\/", File.separator);
        Glide.with(mContext).load(image)
                .apply(new RequestOptions().error(R.drawable.ic_logo)
                        .placeholder(R.drawable.ic_logo))
                .into(imageView);
        baseViewHolder.setText(R.id.title,item.getTitle())
                .setText(R.id.time,item.getTime());
    }
}
