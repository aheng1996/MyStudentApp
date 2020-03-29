package com.example.mystudentapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.base.BaseActivity;
import com.example.mystudentapp.base.BaseMeassage;
import com.example.mystudentapp.bean.User;
import com.example.mystudentapp.db.ctrl.LoginCtrl;
import com.example.mystudentapp.utils.PermissionUtil;



public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private EditText etUserName;
    private EditText etPassWord;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionUtil.INSTANCE.allPermission(this);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        etUserName = findViewById(R.id.et_user_name);
        etPassWord = findViewById(R.id.et_pwd);
        tvLogin = findViewById(R.id.tv_login);
        tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                login();
                break;

        }
    }



    private void login() {
        User user = LoginCtrl.login(etUserName.getText().toString(), etPassWord.getText().toString());
        if (user != null) {
            if (user.getType() == 1) {
                goToActivity(AdminActivity.class);
            } else {
//                tvLogin.setText(user.getName());\
                BaseMeassage.INSTANCE.setUser(user);
                finish();
                goToActivity(HomeActivity.class);

            }
        } else {
            showToast("用户名或密码错误！");
        }
//        goToActivity(HomeActivity.class);

    }
}
