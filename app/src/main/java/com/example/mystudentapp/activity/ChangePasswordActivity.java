package com.example.mystudentapp.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.base.BaseActivity;
import com.example.mystudentapp.base.BaseMeassage;
import com.example.mystudentapp.bean.User;
import com.example.mystudentapp.db.ctrl.LoginCtrl;
import com.example.mystudentapp.utils.SPUtil;


public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener {
    private EditText etOldPwd, etNewPwd1, etNewPwd2;
    private TextView tvError;
    //推送测试
    //tuisongba
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
        initListener();
    }

    private void initListener() {
        ivBack.setOnClickListener(this);
        findViewById(R.id.tv_change).setOnClickListener(this);
    }

    private void initView() {
        ivBack = findViewById(R.id.iv_back);

        etOldPwd = findViewById(R.id.et_pwd);
        etNewPwd1 = findViewById(R.id.et_re_pwd);
        etNewPwd2 = findViewById(R.id.et_re_pwd2);
        tvError = findViewById(R.id.tv_error);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_change:
                change();
                break;
        }

    }

    private void change() {

        String oldPwd = etOldPwd.getText().toString();
        User user = LoginCtrl.login(BaseMeassage.INSTANCE.getUser().getBianHao(), oldPwd);
        if (0 == oldPwd.length()) {
            tvError.setText("请输入旧密码");
            return;
        }
        String newPwd1 = etNewPwd1.getText().toString();
        if (0 == newPwd1.length()) {
            tvError.setText("请输入新密码");
            return;
        }
        String newPwd2 = etNewPwd2.getText().toString();
        if (!newPwd1.equals(newPwd2)) {
            tvError.setText("新密码两次输入不一致，请重新输入");
            return;
        }
        if (newPwd1.length() < 6) {
            tvError.setText("密码不能少于6位，请重新输入");
            return;
        }
        if (newPwd1.length() > 24) {
            tvError.setText("密码不能超过24位，请重新输入");
            return;
        }
        tvError.setText("");
        if (user != null) {
            LoginCtrl.changePass(BaseMeassage.INSTANCE.getUser().getBianHao(), newPwd1, user.getType());
            showToast("修改密码成功");
            finish();
        } else {
            tvError.setText("旧密码输入错误");
        }


    }

}
