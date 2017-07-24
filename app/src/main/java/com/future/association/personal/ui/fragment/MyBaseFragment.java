package com.future.association.personal.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.future.baselib.activity.BaseFragment;

/**
 * Created by javakam on 2017/7/3 0003.
 */
public abstract class MyBaseFragment extends BaseFragment {
    public static final String APR_POSITION = "position";
    private int position;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.position = getArguments().getInt(APR_POSITION);
    }

//    @Override
//    public void onPause() {
//        StatusUtils.setStatusbarColor(getActivity(), getResources().getColor(R.color.color_00000000));
//        super.onPause();
//    }
}