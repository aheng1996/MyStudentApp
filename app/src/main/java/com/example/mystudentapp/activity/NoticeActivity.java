package com.example.mystudentapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mystudentapp.R;
import com.example.mystudentapp.base.BaseActivity;

public class NoticeActivity extends BaseActivity {

    //公告管理界面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
    }
}