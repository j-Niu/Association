package com.future.association.personal;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.future.association.R;
import com.future.baselib.view.ActionSheetDialog;

/**
 * https://github.com/j-Niu/Association
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends MyBaseFragment {

    private ActionSheetDialog sheetDialog;
    private LinearLayout myLevel;

    public PersonalFragment() {
        // Required empty public constructor
    }

    public static PersonalFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(APR_POSITION, position);
        PersonalFragment fragment = new PersonalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
    }

    @NonNull
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        setTitle("我的");
//        mToolbar.setTitle("我的");
        myLevel = (LinearLayout) view.findViewById(R.id.linearMy2);
        myLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyLevelActivity.class);
            }
        });


//        view.findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                startActivity(new Intent(getContext(), LoginActivity.class));
//                sheetDialog = new ActionSheetDialog(getActivity());
//                sheetDialog.builder().setTitle("选取图片")
//                        .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() {
//                            @Override
//                            public void onClick(int which) {
//                                Toast.makeText(getActivity(), "拍照" + which, Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .addSheetItem("相册", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
//                            @Override
//                            public void onClick(int which) {
//                                Toast.makeText(getActivity(), "相册" + which, Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                sheetDialog.show();
//            }
//        });
    }

}