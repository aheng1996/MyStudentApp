package com.example.mystudentapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.base.BaseActivity;
import com.example.mystudentapp.bean.User;
import com.example.mystudentapp.db.bean.HuPing;
import com.example.mystudentapp.db.ctrl.HuPingCtrl;

public class StudentChildActivity extends BaseActivity implements View.OnClickListener {
    //学生互评子页面
    private String id,name,xuehao;  //id，name，xuehao，这三个字符串是被评价人的id，name，xuehao

    private String myXuehao; //这个myXuehao 字符串是评价人的学号；

    private TextView tvTitle,tvSave;
    private ImageView ivBack;

    private HuPing huPing;
    private EditText etJiangli,etJiBen,etPinDe;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_child);
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        xuehao = getIntent().getStringExtra("xueHao");
        myXuehao=getIntent().getStringExtra("myXueHao");
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
        etPinDe=findViewById(R.id.et_pinde);

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
        huPing=new HuPing();
        //传进去3个分 ，被评价人的学号.我的学号
        huPing.setJiangli(Double.parseDouble(etJiangli.getText().toString()));
        huPing.setJiBenFen(Double.parseDouble(etJiBen.getText().toString()));
        huPing.setPinde(Double.parseDouble(etPinDe.getText().toString()));

        //打印个学号，看看学号是否为空

        huPing.setXuehao(xuehao);
        huPing.setPingJiaRen(myXuehao);

        HuPingCtrl.save(huPing);

        showToast("评价成功");
        onBackPressed();


    }
}
