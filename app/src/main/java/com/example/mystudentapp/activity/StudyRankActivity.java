package com.example.mystudentapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.adapter.StudyRankAdapter;
import com.example.mystudentapp.base.BaseActivity;
import com.example.mystudentapp.db.bean.ChengjiBiao;
import com.example.mystudentapp.db.ctrl.ChengjiCtrl;

import java.util.ArrayList;
import java.util.List;

public class StudyRankActivity extends BaseActivity implements View.OnClickListener {
    //学习成绩界面
    private ImageView ivBack;
    private ListView lvStudyRank;
    private StudyRankAdapter adapter;

    private List<ChengjiBiao> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_rank);

        initView();
        initData();
        initListener();
    }

    private void initData() {
       list= ChengjiCtrl.select();
       adapter.setList(list);


    }

    private void initListener() {
        ivBack.setOnClickListener(this);


    }

    private void initView() {
        ivBack=findViewById(R.id.iv_back);
        lvStudyRank=findViewById(R.id.lv_study_rank);
        adapter=new StudyRankAdapter(this);
        lvStudyRank.setAdapter(adapter);

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