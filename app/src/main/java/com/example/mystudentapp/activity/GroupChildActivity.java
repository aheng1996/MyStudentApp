package com.example.mystudentapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mystudentapp.R;
import com.example.mystudentapp.base.BaseActivity;
import com.example.mystudentapp.db.bean.ChengjiBiao;
import com.example.mystudentapp.db.bean.XiaoZu;
import com.example.mystudentapp.db.ctrl.CaiLiaoCtrl;
import com.example.mystudentapp.db.ctrl.ChengjiCtrl;
import com.example.mystudentapp.db.ctrl.XiaoZuCtrl;

public class GroupChildActivity extends BaseActivity implements View.OnClickListener {
    //小组互评子页面

    private String id, name, xuehao;  //id，name，xuehao，这三个字符串是被评价人的id，name，xuehao

    private String myXuehao; //这个myXuehao 字符串是评价人的学号；
    private TextView tvTitle, tvSave, tv_fenshu;

    private ImageView ivBack;
    private ImageView iv_cailiao1;
    private ImageView iv_cailiao2;
    private ImageView iv_cailiao3;
    private XiaoZu xiaozu;

    private TextView tvWenTi,tvNengLi,tvPinde;


    private EditText etJiangli, etJiBen, etNengli,et_neng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_child);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        xuehao = getIntent().getStringExtra("xueHao");
        myXuehao = getIntent().getStringExtra("myXueHao");

        initView();
        initListener();
        String path1 = CaiLiaoCtrl.getPicPath(xuehao, 0);
        String path2 = CaiLiaoCtrl.getPicPath(xuehao, 1);
        String path3 = CaiLiaoCtrl.getPicPath(xuehao, 2);

        if (path1 != null) {
            Glide.with(this).load(path1).into(iv_cailiao1);
        }
        if (path2 != null) {
            Glide.with(this).load(path2).into(iv_cailiao2);
        }
        if (path3 != null) {
            Glide.with(this).load(path3).into(iv_cailiao3);
        }
        String fenshu = ChengjiCtrl.selectOne(xuehao).getFenshu();
        if (fenshu==null){
            fenshu="";
        }
        tv_fenshu.setText(fenshu);
    }

    private void initListener() {
        ivBack.setOnClickListener(this);
        tvSave.setOnClickListener(this);

        tvWenTi.setOnClickListener(this);
        tvNengLi.setOnClickListener(this);
        tvPinde.setOnClickListener(this);

    }

    private void initView() {
        tvTitle = findViewById(R.id.tv_title);
        iv_cailiao1 = findViewById(R.id.iv_cailiao1);
        iv_cailiao2 = findViewById(R.id.iv_cailiao2);
        iv_cailiao3 = findViewById(R.id.iv_cailiao3);
        tv_fenshu = findViewById(R.id.tv_fenshu);
        tvTitle.setText("评价" + name);

        ivBack = findViewById(R.id.iv_back);
        tvSave = findViewById(R.id.tv_save);
        etJiangli = findViewById(R.id.et_jiangli);
        et_neng = findViewById(R.id.et_neng);
        etJiBen = findViewById(R.id.et_jiben);
        etNengli = findViewById(R.id.et_nengli);

        tvWenTi=findViewById(R.id.tv_wenti);
        tvNengLi=findViewById(R.id.tv_nengli);
        tvPinde=findViewById(R.id.tv_pinde);



    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_save:
                save();
                break;

            case R.id.tv_wenti:
                //文体查看 0，能力是1，品德是2

                Intent intent = new Intent(this, LookPhotoActivity.class);
                intent.putExtra("type", 0);
                intent.putExtra("xh", xuehao);

                startActivity(intent);
                break;
            case R.id.tv_nengli:
                Intent intent1 = new Intent(this, LookPhotoActivity.class);
                intent1.putExtra("type", 1);
                intent1.putExtra("xh", xuehao);

                startActivity(intent1);
                break;
            case R.id.tv_pinde:
                Intent intent2 = new Intent(this, LookPhotoActivity.class);
                intent2.putExtra("type", 2);
                intent2.putExtra("xh", xuehao);

                startActivity(intent2);
                break;

        }

    }

    private void save() {
        xiaozu = new XiaoZu();
        //传进去3个分 ，被评价人的学号.我的学号
        xiaozu.setJianglifen(Double.parseDouble(et_neng.getText().toString()));
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
