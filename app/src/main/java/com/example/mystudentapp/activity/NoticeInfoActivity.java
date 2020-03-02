package com.example.mystudentapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.base.BaseActivity;

public class NoticeInfoActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvTitle;
    private TextView tvMessage;
    private ImageView ivBack;

    private String title,message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_info);
        title = getIntent().getStringExtra("title");
        message = getIntent().getStringExtra("message");

        initView();
        initListener();

    }

    private void initListener() {
        ivBack.setOnClickListener(this);
    }

    private void initView() {
        tvMessage=findViewById(R.id.tv_message);
        tvTitle=findViewById(R.id.tv_title);

        //赋值
        tvTitle.setText(title);
        tvMessage.setText(message);

        ivBack=findViewById(R.id.iv_back);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;

        }
    }
}
