package com.future.association.supervice;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.future.association.R;
import com.future.baselib.activity.BaseFragment;
import com.future.baselib.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuperviceFragment extends BaseFragment {



    public SuperviceFragment() {
        // Required empty public constructor
    }


    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Nullable
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_supervice;
    }


    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView superviceRv = (RecyclerView) view.findViewById(R.id.supervice_rv);
        superviceRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        SuperviceAdapter superviceAdapter = new SuperviceAdapter(R.layout.supervice_item,null);
        superviceRv.addOnItemTouchListener(detailListener);
        View head = getHead();
        superviceAdapter.addHeaderView(head,0);
        //TODO 测试
        List<String> test = new ArrayList<>();
        for (int i = 0; i < 70; i++) {
            test.add("电风扇 是的是的发士大夫是的是的方式的是的发的范德萨 士大夫是是 是的是的是的都是都是"+i);
        }
        superviceAdapter.setNewData(test);
        superviceRv.setAdapter(superviceAdapter);

    }

    private View getHead() {
        View view = View.inflate(getActivity(), R.layout.supervice_head, null);
        RecyclerView headRv = (RecyclerView) view.findViewById(R.id.supervice_head_rv);
        headRv.setLayoutManager(new GridLayoutManager(getActivity(),3));
        SuperviceHeadAdapter adapter = new SuperviceHeadAdapter(R.layout.supervice_head_item,null);
        headRv.addOnItemTouchListener(applyListener);
        List<String> test = new ArrayList<>();
        //TODO 测试
        for (int i = 0; i < 9; i++) {
            test.add("测试"+i);
        }
        adapter.setNewData(test);
        headRv.setAdapter(adapter);
        return view;
    }
    private RecyclerView.OnItemTouchListener detailListener = new OnItemClickListener() {
        @Override
        public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
            //跳往详情页面
            if (!CommonUtils.isFastDoubleClick()){
                Intent intent = new Intent(getActivity(),SuperviceDetailActivity.class);
                startActivity(intent);
            }
        }
    };

    private RecyclerView.OnItemTouchListener applyListener = new OnItemClickListener() {
        @Override
        public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
            //跳往申请页面
            if (!CommonUtils.isFastDoubleClick()){
                Intent intent = new Intent(getActivity(),SuperviceApplyActivity.class);
                startActivity(intent);
            }
        }
    };
}
