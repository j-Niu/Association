package com.future.association.supervice.viewmodel;

import android.content.Intent;
import android.databinding.DataBindingUtil;
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
import com.future.association.databinding.SuperviceHeadBinding;
import com.future.association.supervice.SupericeApi;
import com.future.association.supervice.adapter.SuperviceAdapter;
import com.future.association.supervice.adapter.SuperviceHeadAdapter;
import com.future.association.supervice.model.SupericeList;
import com.future.association.supervice.model.SupericerTypeList;
import com.future.association.supervice.view.SuperviceApplyActivity;
import com.future.association.supervice.view.SuperviceDetailActivity;
import com.future.baselib.utils.CommonUtils;
import com.future.baselib.utils.HttpRequest;

/**
 * Created by rain on 2017/7/12.
 */

public class SuperviceViewModel {
    private final Fragment fragment;
    private final FragmentSuperviceBinding binding;
    public final ObservableField<SuperviceAdapter> adapter = new ObservableField<>();
    public final ObservableField<SuperviceHeadAdapter> headAdapter = new ObservableField<>();
    public final ObservableArrayList<SupericeList.SupericeListInfo> items = new ObservableArrayList<>();
    public final ObservableArrayList<SupericerTypeList.SupericerTypeInfo> headItems = new ObservableArrayList<>();
    private int PAHE;


    public SuperviceViewModel(Fragment fragment, FragmentSuperviceBinding binding) {
        this.fragment = fragment;
        this.binding = binding;
        initView();
        initData();
    }


    private void initView() {
        binding.superviceRv.setLayoutManager(new LinearLayoutManager(fragment.getActivity()));
        SuperviceAdapter superviceAdapter = new SuperviceAdapter(R.layout.supervice_item, null);
        superviceAdapter.setOnLoadMoreListener(loadMoreListener, binding.superviceRv);
        View head = getHead();
        superviceAdapter.addHeaderView(head, 0);
        adapter.set(superviceAdapter);
        initListener();
    }

    private void initData() {
        getSupericeType();
        getSupericeList();
    }

    private void getSupericeList() {
        //查询监督列表
        ++PAHE;
        SupericeApi.getInstance().getSupericeList(fragment.getActivity(), String.valueOf(PAHE))
                .setListener(new HttpRequest.OnNetworkListener<SupericeList>() {
                    @Override
                    public void onSuccess(SupericeList response) {
                        if (response == null || response.getList() == null) {
                            adapter.get().loadMoreEnd();
                        } else {
                            if (response.getList().size() < 20) {
                                adapter.get().loadMoreEnd();
                            }
                            items.addAll(response.getList());
                        }
                    }

                    @Override
                    public void onFail(String message) {

                    }
                }).start(new SupericeList());
    }

    private void getSupericeType() {
        String[] types = {
                "网购",
                "旅游",
                "家电",
                "数码产品",
                "电信",
                "汽车",
                "游戏",
                "快递",
                "其他",
        };
        int[] imgs = {
                R.drawable.superice_type1,
                R.drawable.superice_type2,
                R.drawable.superice_type3,
                R.drawable.superice_type4,
                R.drawable.superice_type5,
                R.drawable.superice_type6,
                R.drawable.superice_type7,
                R.drawable.superice_type8,
                R.drawable.superice_type9,
        };

        for (int i = 0; i < 9; i++) {
            SupericerTypeList.SupericerTypeInfo typeInfo = new SupericerTypeList.SupericerTypeInfo();
            typeInfo.setId(String.valueOf(i+1));
            typeInfo.setHangye(types[i]);
            typeInfo.setRes(imgs[i]);
            headItems.add(typeInfo);
        }
        //获取监督分类
//        SupericeApi.getInstance().getSupericeTypeList(fragment.getActivity(), MyApp.getUserToken())
//                .setListener(new HttpRequest.OnNetworkListener<SupericerTypeList>() {
//                    @Override
//                    public void onSuccess(SupericerTypeList response) {
//                        Log.d("rain", response.toString());
//                        headItems.clear();
//                        headItems.addAll(response.getList());
//                    }
//
//                    @Override
//                    public void onFail(String message) {
//                        Log.d("rain", message);
//                    }
//                }).start(new SupericerTypeList());
    }

    public void initListener() {
        binding.superviceRv.addOnItemTouchListener(detailListener);
    }

    private View getHead() {
        View view = View.inflate(fragment.getActivity(), R.layout.supervice_head, null);
        SuperviceHeadBinding superviceHeadBinding = DataBindingUtil.bind(view);
        superviceHeadBinding.setViewModel(this);
        superviceHeadBinding.superviceHeadRv.setLayoutManager(new GridLayoutManager(fragment.getActivity(), 3));
        SuperviceHeadAdapter adapter = new SuperviceHeadAdapter(R.layout.supervice_head_item, null);
        headAdapter.set(adapter);
        superviceHeadBinding.superviceHeadRv.addOnItemTouchListener(applyListener);
        return view;
    }

    private RecyclerView.OnItemTouchListener detailListener = new OnItemClickListener() {
        @Override
        public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
            //跳往详情页面
            if (!CommonUtils.isFastDoubleClick()) {
                Intent intent = new Intent(fragment.getActivity(), SuperviceDetailActivity.class);
                SupericeList.SupericeListInfo item = (SupericeList.SupericeListInfo) baseQuickAdapter.getItem(i);
                intent.putExtra("id", item.getId());
                fragment.getActivity().startActivity(intent);
            }
        }
    };

    private RecyclerView.OnItemTouchListener applyListener = new OnItemClickListener() {
        @Override
        public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
            //跳往申请页面
            if (!CommonUtils.isFastDoubleClick()) {
                Intent intent = new Intent(fragment.getActivity(), SuperviceApplyActivity.class);
                String hangye = "";
                try {
                    hangye = ((SupericerTypeList.SupericerTypeInfo) baseQuickAdapter.getItem(i)).getHangye();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                intent.putExtra("type", hangye);
                fragment.getActivity().startActivity(intent);
            }
        }
    };

    private BaseQuickAdapter.RequestLoadMoreListener loadMoreListener = new BaseQuickAdapter.RequestLoadMoreListener() {
        @Override
        public void onLoadMoreRequested() {
            getSupericeList();
        }
    };
}
