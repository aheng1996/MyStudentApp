package com.example.mystudentapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.adapter.StudentHuPinAdater;
import com.example.mystudentapp.base.BaseActivity;
import com.example.mystudentapp.base.BaseMeassage;
import com.example.mystudentapp.bean.User;
import com.example.mystudentapp.db.bean.Student;
import com.example.mystudentapp.db.ctrl.HuPingCtrl;

import java.util.ArrayList;
import java.util.List;

public class StudentEvaluateActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    //学生互评界面
    //出listview 点击item  跳转页面


    private ImageView ivBack;
    private User user;
    private ListView lvStudent;  //除自己以为的学生列表
    private List<Student> list;
    private StudentHuPinAdater adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_evaluate);
        user= BaseMeassage.INSTANCE.getUser();

        initView();
        initData();
        initListener();
    }

    private void initData() {

        list=new ArrayList<>();
        list= HuPingCtrl.selectOther(user.getBianHao());
        adapter.setList(list);

    }

    private void initListener() {
        ivBack.setOnClickListener(this);
        lvStudent.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
      //走逻辑跳转，进入评价界面
        //跳列表详情
        Intent intent = new Intent(this, StudentChildActivity.class);
        intent.putExtra("id", list.get(i).getId()+"");
        intent.putExtra("name",list.get(i).getName());
        intent.putExtra("xueHao",list.get(i).getXueHao());
        intent.putExtra("myXueHao",user.getBianHao());
        startActivity(intent);


    }
}
