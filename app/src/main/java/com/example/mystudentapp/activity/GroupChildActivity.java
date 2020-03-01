package com.example.mystudentapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.base.BaseActivity;
import com.example.mystudentapp.db.bean.XiaoZu;
import com.example.mystudentapp.db.ctrl.XiaoZuCtrl;

public class GroupChildActivity extends BaseActivity implements View.OnClickListener {
    //小组互评子页面

    private String id,name,xuehao;  //id，name，xuehao，这三个字符串是被评价人的id，name，xuehao

    private String myXuehao; //这个myXuehao 字符串是评价人的学号；
    private TextView tvTitle,tvSave;

    private ImageView ivBack;
    private XiaoZu xiaozu;


    private EditText etJiangli,etJiBen,etNengli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_child);

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
        etNengli=findViewById(R.id.et_nengli);

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
       xiaozu=new XiaoZu();
        //传进去3个分 ，被评价人的学号.我的学号
        xiaozu.setJianglifen(Double.parseDouble(etJiangli.getText().toString()));
        xiaozu.setJiBenFen(Double.parseDouble(etJiBen.getText().toString()));
        xiaozu.setNenglifen(Double.parseDouble(etNengli.getText().toString()));

        //打印个学号，看看学号是否为空

        xiaozu.setXuehao(xuehao);
        xiaozu.setPingJiaRen(myXuehao);

        XiaoZuCtrl.save(xiaozu);

        showToast("评价成功");
        onBackPressed();
    }
}
