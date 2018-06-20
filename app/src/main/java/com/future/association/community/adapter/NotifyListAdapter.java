package com.future.association.community.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.future.association.R;
import com.future.association.community.model.MsgNotifyInfo;

import java.util.List;

public class NotifyListAdapter extends BaseQuickAdapter<MsgNotifyInfo, BaseViewHolder> {

    public NotifyListAdapter(int layoutResId, @Nullable List<MsgNotifyInfo> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, MsgNotifyInfo item) {
        helper.setText(R.id.tv_content,item.getTitle())
                .setText(R.id.tv_from,item.getFrom())
                .setText(R.id.tv_reply,item.getReplyFormat());

    }
}
