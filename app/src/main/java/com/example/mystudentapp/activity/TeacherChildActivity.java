package com.example.mystudentapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.base.BaseActivity;
import com.example.mystudentapp.db.bean.TeacherPingjia;
import com.example.mystudentapp.db.ctrl.TeacherPingJiaCtrl;

public class TeacherChildActivity extends BaseActivity implements View.OnClickListener {
    //教师评价子页面
    private String id,name,xuehao;  //id，name，xuehao，这三个字符串是被评价人的id，name，xuehao

    private String myXuehao; //这个myXuehao 字符串是评价人的学号；

    private TextView tvTitle,tvSave;
    private ImageView ivBack;
    private TeacherPingjia teacherPingjia;

    private EditText etJiangli,etJiBen,etBiaoXian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_child);
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        xuehao = getIntent().getStringExtra("xueHao");
        myXuehao=getIntent().getStringExtra("myXueHao");  //接受的其实是教师编号

        initView();
        initListener();

    }

    private void initListener() {
        ivBack.setOnClickListener(this);
        tvSave.setOnClickListener(this);
    }

    private void initView() {
        tvTitle=findViewById(R.id.tv_title);
        tvTitle.setText("评价"+name);
        ivBack=findViewById(R.id.iv_back);
        tvSave=findViewById(R.id.tv_save);
        etJiangli=findViewById(R.id.et_jiangli);
        etJiBen=findViewById(R.id.et_jiben);
        etBiaoXian=findViewById(R.id.et_biaoxian);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_save:
                save();
                break;

        }
    }

    private void save() {
        teacherPingjia=new TeacherPingjia();
        //传进去3个分 ，被评价人的学号.我的学号
        teacherPingjia.setBianXian(Double.parseDouble(etBiaoXian.getText().toString()));
        teacherPingjia.setJiBenFen(Double.parseDouble(etJiBen.getText().toString()));
        teacherPingjia.setYuanShi(Double.parseDouble(etJiangli.getText().toString()));  //原始就是原始奖励分

        //打印个学号，看看学号是否为空

        teacherPingjia.setXuehao(xuehao);
        teacherPingjia.setPingJiaRen(myXuehao);

        TeacherPingJiaCtrl.save(teacherPingjia);

        showToast("评价成功");
        onBackPressed();
    }
}
