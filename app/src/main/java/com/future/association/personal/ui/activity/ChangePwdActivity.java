package com.future.association.personal.ui.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.future.association.R;
import com.future.association.community.utils.TextUtil;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.StatusUtils;

public class ChangePwdActivity extends BaseActivity {

    private EditText edtMyOldPwd, edtMyNewPwd, edtMyNewPwdAgaing;
    private Button btMyConfirm;
    private Toast toast;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        StatusUtils.setStatusbarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_change_pwd);
    }

    @Override
    protected void initView() {
        setTitle("修改密码");
        setTitleLeft(R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edtMyOldPwd = (EditText) findViewById(R.id.edtMyOldPwd);
        edtMyNewPwd = (EditText) findViewById(R.id.edtMyNewPwd);
        edtMyNewPwdAgaing = (EditText) findViewById(R.id.edtMyNewPwdAgaing);
        btMyConfirm = (Button) findViewById(R.id.btMyConfirm);
        btMyConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyCode();

            }
        });
    }

    private void verifyCode() {
        String str1 = edtMyOldPwd.getText().toString().trim();
        String str2 = edtMyNewPwd.getText().toString().trim();
        String str3 = edtMyNewPwdAgaing.getText().toString().trim();
        if (TextUtil.isEmpty(str1) || str1.length() < 6) {
            toast = Toast.makeText(ChangePwdActivity.this, "原登录密码有误", Toast.LENGTH_SHORT);
        } else if (TextUtil.isEmpty(str2) || str2.length() < 6) {
            toast = Toast.makeText(ChangePwdActivity.this, "请输入正确的新密码", Toast.LENGTH_SHORT);
        } else if (TextUtil.isEmpty(str3) || str3.length() < 6 || !str2.equalsIgnoreCase(str3)) {
            toast = Toast.makeText(ChangePwdActivity.this, "两次新密码输入不一致", Toast.LENGTH_SHORT);
        }

        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastView = (LinearLayout) toast.getView();
        toastView.setLayoutParams(new LinearLayout.LayoutParams(140, 90));
        ImageView imageCodeProject = new ImageView(ChangePwdActivity.this);
        imageCodeProject.setImageResource(R.drawable.ic_clear_press);
        toastView.addView(imageCodeProject, 0);
        toast.show();
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

}
