package com.example.mystudentapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mystudentapp.R;

public class InputInfoActivity extends AppCompatActivity {
    //管理员录入学生信息，录入老师信息 管理员账号密码都是admin
    //老师账号 1到10；
    //学生账号 2016014990 是这种的；
    //初始密码都是123456

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_info);
    }
}
