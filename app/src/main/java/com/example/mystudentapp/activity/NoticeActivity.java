package com.example.mystudentapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.adapter.NoticeAdapter;
import com.example.mystudentapp.base.BaseActivity;
import com.example.mystudentapp.db.bean.GongGao;
import com.example.mystudentapp.db.ctrl.GongGaoCtrl;

import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    //公告管理界面
    private ImageView ivBack,ivAdd;
    private ListView lvNotice;  //公告列表
    private NoticeAdapter adapter;
    private List<GongGao> list;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        initView();
        initListener();
    }

    private void initListener() {
        ivBack.setOnClickListener(this);
        ivAdd.setOnClickListener(this);
        lvNotice.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();

    }
    private void initData() {

        list=new ArrayList<>();
        list= GongGaoCtrl.select();
        adapter.setList(list);

    }

    private void initView() {
        ivAdd=findViewById(R.id.iv_add);
        ivBack=findViewById(R.id.iv_back);
        lvNotice=findViewById(R.id.lv_notice);
        adapter=new NoticeAdapter(this);
        lvNotice.setAdapter(adapter);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_add:
                goToActivity(AddNoticeActivity.class);
                break;
        }


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        //走逻辑跳转，进入公告详情界面

        Intent intent = new Intent(this, NoticeInfoActivity.class);
        intent.putExtra("id", list.get(i).getId()+"");
        intent.putExtra("title",list.get(i).getTitle());
        intent.putExtra("message",list.get(i).getMessage());
        startActivity(intent);


    }
}
