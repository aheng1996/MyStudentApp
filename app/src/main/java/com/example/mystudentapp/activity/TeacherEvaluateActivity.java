package com.example.mystudentapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.base.BaseActivity;

public class TeacherEvaluateActivity extends BaseActivity implements View.OnClickListener {
    //教师评价界面

    private ImageView ivBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_evaluate);

        initView();
        initListener();
    }

    private void initListener() {
        ivBack.setOnClickListener(this);

    }

    private void initView() {
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
