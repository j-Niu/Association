package com.future.association.supervice.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
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
        final ImageView imageView = baseViewHolder.getView(R.id.iv);
        String image = item.getImage();
        image = image.replace("\\/", File.separator);
        Glide.with(mContext).load(image)
                .listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object o, Target<Drawable> target, boolean b) {
                imageView.setVisibility(View.GONE);
                return true;
            }

            @Override
            public boolean onResourceReady(Drawable drawable, Object o, Target<Drawable> target, DataSource dataSource, boolean b) {
                imageView.setVisibility(View.VISIBLE);
                return false;
            }


        })
                .into(imageView);
        baseViewHolder.setText(R.id.title,item.getTitle())
                .setText(R.id.time,item.getTime());
    }
}
