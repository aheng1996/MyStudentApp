package com.example.mystudentapp.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.adapter.StudentHuPinAdater;
import com.example.mystudentapp.base.BaseActivity;
import com.example.mystudentapp.base.BaseMeassage;
import com.example.mystudentapp.bean.User;
import com.example.mystudentapp.db.bean.Student;
import com.example.mystudentapp.db.ctrl.XiaoZuCtrl;

import java.util.ArrayList;
import java.util.List;

public class GroupEvaluateActivity extends BaseActivity implements View.OnClickListener {
    //小组互评界面

    private ImageView ivBack;
    private User user;
    private ListView lvStudent;  //除自己以为的学生列表
    private List<Student> list;
    private StudentHuPinAdater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_evaluate);
        user= BaseMeassage.INSTANCE.getUser();
        initView();
        initListener();
    }
    private void initData() {

        list=new ArrayList<>();
        list= XiaoZuCtrl.selectOther(user.getBianHao());
        adapter.setList(list);

    }
    @Override
    protected void onResume() {
        super.onResume();
        initData();

    }
    private void initListener() {
        ivBack.setOnClickListener(this);

    }

    private void initView() {
        ivBack=findViewById(R.id.iv_back);
        lvStudent=findViewById(R.id.lv_student);
        adapter=new StudentHuPinAdater(this);
        lvStudent.setAdapter(adapter);


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
