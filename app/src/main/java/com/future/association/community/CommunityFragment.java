package com.future.association.community;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.future.association.R;
import com.future.baselib.activity.BaseFragment;

/**
 * 社区Fragment
 * A simple {@link Fragment} subclass.
 */
public class CommunityFragment extends BaseFragment {


    public CommunityFragment() {
        // Required empty public constructor
    }


    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Nullable
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_community;
    }


    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {

    }

}
