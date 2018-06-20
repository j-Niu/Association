package com.future.association.supervice.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.future.association.R;
import com.future.association.supervice.model.SupericeList;

import java.util.List;

/**
 * Created by rain on 2017/7/7.
 */

public class SuperviceAdapter extends BaseQuickAdapter<SupericeList.SupericeListInfo, BaseViewHolder> {
    public SuperviceAdapter(@LayoutRes int layoutResId, @Nullable List<SupericeList.SupericeListInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SupericeList.SupericeListInfo item) {
        final ImageView imageView = baseViewHolder.getView(R.id.iv);
        //2017.8.17需求变更为不显示图片
//        String image = item.getImage();
//        image = image.replace("\\/", File.separator);
//        if (!TextUtil.isEmpty(image) && image.startsWith("http")) {
//            imageView.setVisibility(View.VISIBLE);
//            Glide.with(mContext).load(image)
//                    .apply(GlideUtils.defaultImg())
//                    .into(imageView);
//        } else {
//            imageView.setVisibility(View.GONE);
//        }
//            imageView.setVisibility(View.GONE);

        baseViewHolder.setText(R.id.title, item.getTitle())
                .setText(R.id.superice_time, item.getCreate_time())
        .setText(R.id.superice_nature,item.getHangye())
        .setText(R.id.superice_type,item.getNature());
    }
}
