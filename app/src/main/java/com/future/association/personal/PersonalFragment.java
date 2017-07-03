package com.future.association.personal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.future.association.R;
import com.future.baselib.view.ActionSheetDialog;

/**
 * https://github.com/j-Niu/Association
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends Fragment {

    ActionSheetDialog sheetDialog;

    public PersonalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal, container, false);

        view.findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getContext(), LoginActivity.class));
                sheetDialog = new ActionSheetDialog(getActivity());
                sheetDialog.builder().setTitle("选取图片")
                        .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Toast.makeText(getActivity(), "拍照" + which, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addSheetItem("相册", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Toast.makeText(getActivity(), "相册" + which, Toast.LENGTH_SHORT).show();
                            }
                        });
                sheetDialog.show();
            }
        });

        return view;
    }

}
