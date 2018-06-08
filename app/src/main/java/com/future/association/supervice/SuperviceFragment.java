package com.future.association.supervice;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.future.association.R;
import com.future.association.common.EventCode;
import com.future.association.databinding.FragmentSuperviceBinding;
import com.future.association.supervice.viewmodel.SuperviceViewModel;
import com.future.baselib.activity.BaseFragment;
import com.future.baselib.entity.MessageEvent;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuperviceFragment extends BaseFragment {


    private SuperviceViewModel mViewModel;

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
        setTitle(R.string.superice);
        FragmentSuperviceBinding superviceBinding = DataBindingUtil.bind(view);
        mViewModel = new SuperviceViewModel(this, superviceBinding);
        superviceBinding.setViewModel(mViewModel);
        setTitleRight(R.drawable.bianji, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.publish();
            }
        });
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveEvent(MessageEvent event) {
        if (event.getCode() == EventCode.SUPERICE_LIST_REFRESH){
            mViewModel.refresh();
        }
    }



}
