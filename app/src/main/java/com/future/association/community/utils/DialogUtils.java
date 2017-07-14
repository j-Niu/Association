package com.future.association.community.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.future.association.R;
import com.future.association.databinding.DialogResultBinding;
import com.future.association.databinding.DialogSelectBinding;

/**
 * Description:显示指定组件的对话框,并跳转至指定的Activity
 */
public class DialogUtils {


    public interface ItemSelectedListener{
        void select(int position) ;
    }

    public interface EditContentDialogListener {
        void positive(String content);//确定

        void nagtive();//取消
    }

    public static void showDialog4View(Context context, int res, int positiveId, int nagtiveId,String title, final EditContentDialogListener listener){
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);
        View view = View.inflate(context,res,null) ;
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        if(positiveId != -1){
            View positive = view.findViewById(positiveId) ;
            positive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.positive("");
                    dialog.dismiss();
                }
            });
        }
        if(nagtiveId != -1){
            View nagtive = view.findViewById(nagtiveId) ;
            nagtive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.nagtive();
                    dialog.dismiss();
                }
            });
        }
        dialog.show();
        dialog.setContentView(view);
    }

    /**
     * 选择列表
     * @param context
     * @param title
     * @param items
     * @param listener
     */
    public static void showSelectDialog(Context context,String title,String[] items,final ItemSelectedListener listener){
        final AlertDialog dialog = new AlertDialog.Builder(context).create();

        View view = View.inflate(context,R.layout.dialog_select,null) ;
        DialogSelectBinding binding = DataBindingUtil.bind(view) ;
        binding.setTitle(title);
        binding.lvList.setAdapter(new ArrayAdapter(context,R.layout.item_dialog_select,items));
        binding.lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.select(position);
                dialog.dismiss();
            }
        });
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes() ;
        params.width = view.getMeasuredWidth() ;
        dialogWindow.setAttributes(params);
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        dialog.setContentView(view);
    }

    /**
     * 结果
     * @param context
     * @param isSuccess
     */
    public static void showResultDialog(Context context,boolean isSuccess){
        View view = View.inflate(context,R.layout.dialog_result,null) ;
        DialogResultBinding binding = DataBindingUtil.bind(view) ;
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams params = dialogWindow.getAttributes() ;
        params.width = 176 ;
        dialogWindow.setAttributes(params);
        dialogWindow.setGravity(Gravity.CENTER);
        dialog.show();
        dialog.setContentView(view);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        },1500) ;
    }

}