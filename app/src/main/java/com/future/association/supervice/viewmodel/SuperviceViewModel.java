package com.future.association.supervice.viewmodel;

import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.future.association.R;
import com.future.association.databinding.FragmentSuperviceBinding;
import com.future.association.supervice.adapter.SuperviceAdapter;
import com.future.association.supervice.adapter.SuperviceHeadAdapter;
import com.future.association.supervice.view.SuperviceApplyActivity;
import com.future.association.supervice.view.SuperviceDetailActivity;
import com.future.baselib.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rain on 2017/7/12.
 */

public class SuperviceViewModel {
    private final Fragment fragment;
    private final FragmentSuperviceBinding binding;
    private final ObservableField<SuperviceAdapter> adapter = new ObservableField<>();
    private final ObservableArrayList<String> items = new ObservableArrayList<>();

    public SuperviceViewModel(Fragment fragment, FragmentSuperviceBinding binding) {
        this.fragment = fragment;
        this.binding = binding;
        init();
    }

    private void init() {
        binding.superviceRv.setLayoutManager(new LinearLayoutManager(fragment.getActivity()));
        SuperviceAdapter superviceAdapter = new SuperviceAdapter(R.layout.supervice_item,null);
        View head = getHead();
        superviceAdapter.addHeaderView(head,0);
        adapter.set(superviceAdapter);

        //TODO 测试
//        List<String> test = new ArrayList<>();
        for (int i = 0; i < 70; i++) {
            items.add("电风扇 是的是的发士大夫是的是的方式的是的发的范德萨 士大夫是是 是的是的是的都是都是"+i);
        }
    }

    public void initListener(){
        binding.superviceRv.addOnItemTouchListener(detailListener);
    }

    private View getHead() {
        View view = View.inflate(fragment.getActivity(), R.layout.supervice_head, null);
        RecyclerView headRv = (RecyclerView) view.findViewById(R.id.supervice_head_rv);
        headRv.setLayoutManager(new GridLayoutManager(fragment.getActivity(),3));
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
                Intent intent = new Intent(fragment.getActivity(),SuperviceDetailActivity.class);
                fragment.getActivity().startActivity(intent);
            }
        }
    };

    private RecyclerView.OnItemTouchListener applyListener = new OnItemClickListener() {
        @Override
        public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
            //跳往申请页面
            if (!CommonUtils.isFastDoubleClick()){
                Intent intent = new Intent(fragment.getActivity(),SuperviceApplyActivity.class);
                fragment.getActivity().startActivity(intent);
            }
        }
    };
}
