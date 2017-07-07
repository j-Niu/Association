package com.future.association.community.presenter;

import com.future.association.community.contract.BannerContract;
import com.future.association.community.contract.TieDetailContract;
import com.future.association.community.model.BannerInfo;
import com.future.association.community.model.TieReplyInfo;
import com.future.association.community.utils.TextUtil;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/5.
 */

public class TieDetailPresenter implements TieDetailContract.IPresenter {

    private TieDetailContract.IView iView;
    private int pageSize = 20;//每页20条数据

    public TieDetailPresenter(TieDetailContract.IView iView) {
        this.iView = iView;
    }

    @Override
    public void getData(int currentPage) {
        ArrayList<TieReplyInfo> tieReplyInfos = new ArrayList<>();
        tieReplyInfos.add(new TieReplyInfo("回复人", "v1", "浙江省杭州市西湖区", "评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文"));
        tieReplyInfos.add(new TieReplyInfo("回复人", "v1", "浙江省杭州市西湖区", "评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文"));
        tieReplyInfos.add(new TieReplyInfo("回复人", "v1", "浙江省杭州市西湖区", "评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文"));
        tieReplyInfos.add(new TieReplyInfo("回复人", "v1", "浙江省杭州市西湖区", "评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文"));
        tieReplyInfos.add(new TieReplyInfo("回复人", "v1", "浙江省杭州市西湖区", "评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文"));
        tieReplyInfos.add(new TieReplyInfo("回复人", "v1", "浙江省杭州市西湖区", "评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文"));
        tieReplyInfos.add(new TieReplyInfo("回复人", "v1", "浙江省杭州市西湖区", "评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文"));
        tieReplyInfos.add(new TieReplyInfo("回复人", "v1", "浙江省杭州市西湖区", "评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文评论正文"));
        iView.setData(tieReplyInfos);
    }

    @Override
    public void sendReply() {
        String replyContent = iView.getReplyContent();
        if (!TextUtil.isEmpty(replyContent)) {
            iView.replyResult(true, new TieReplyInfo("王五", "v10", "四川省成都市高新区", replyContent));
        }
    }

}
