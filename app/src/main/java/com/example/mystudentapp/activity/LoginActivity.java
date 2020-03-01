package com.example.mystudentapp.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.base.BaseActivity;
import com.example.mystudentapp.bean.User;
import com.example.mystudentapp.db.bean.Student;
import com.example.mystudentapp.db.bean.Teacher;
import com.example.mystudentapp.db.ctrl.LoginCtrl;
import com.example.mystudentapp.db.ctrl.StudentCtrl;
import com.example.mystudentapp.utils.PermissionUtil;

import java.util.List;

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
                test();
//                login();
                break;

        }
    }

    private void test() {
        List<Student> user = StudentCtrl.selectAll();
//        StudentCtrl.delete();
        Log.e(">>>>>>>>>>", "test: " + user.size());
    }


    private void login() {
        User user = LoginCtrl.login(etUserName.getText().toString(), etPassWord.getText().toString());
        if (user != null) {
            if (user.getType() == 1) {
                goToActivity(AdminActivity.class);
            } else {
                tvLogin.setText(user.getName());
            }
        }
//        goToActivity(HomeActivity.class);

    }
}
