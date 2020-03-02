package com.example.mystudentapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.base.BaseActivity;
import com.example.mystudentapp.db.bean.GongGao;
import com.example.mystudentapp.db.ctrl.GongGaoCtrl;

public class AddNoticeActivity extends BaseActivity implements View.OnClickListener {
    //添加公告

    private ImageView ivBack;
    private TextView tvSave;
    private EditText etTitle,etMessage;
    private GongGao gongGao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);
        initView();
        initListener();
    }

    private void initListener() {
        ivBack.setOnClickListener(this);
        tvSave.setOnClickListener(this);
    }

    private void initView() {
        ivBack=findViewById(R.id.iv_back);
        tvSave=findViewById(R.id.tv_save);
        etMessage=findViewById(R.id.et_message);
        etTitle=findViewById(R.id.et_title);

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
        gongGao=new GongGao();
        gongGao.setTitle(etTitle.getText().toString());
        gongGao.setMessage(etMessage.getText().toString());

        GongGaoCtrl.add(gongGao);
        showToast("公告添加成功");
        onBackPressed();

    }
}
