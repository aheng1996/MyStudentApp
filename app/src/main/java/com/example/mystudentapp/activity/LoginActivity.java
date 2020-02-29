package com.example.mystudentapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.base.BaseActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private EditText etUserName;
    private EditText etPassWord;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        etUserName=findViewById(R.id.et_user_name);
        etPassWord=findViewById(R.id.et_pwd);
        tvLogin=findViewById(R.id.tv_login);
        tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_login:

                login();
                break;

        }
    }

    private void login() {
        goToActivity(HomeActivity.class);

    }
}
