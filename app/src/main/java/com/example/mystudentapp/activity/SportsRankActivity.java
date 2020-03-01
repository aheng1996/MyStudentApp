package com.example.mystudentapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.adapter.SportsRankAdapter;
import com.example.mystudentapp.adapter.StudyRankAdapter;
import com.example.mystudentapp.base.BaseActivity;
import com.example.mystudentapp.db.bean.ChengjiBiao;
import com.example.mystudentapp.db.bean.TiCe;
import com.example.mystudentapp.db.ctrl.ChengjiCtrl;
import com.example.mystudentapp.db.ctrl.TiCeCtrl;

import java.util.ArrayList;
import java.util.List;

public class SportsRankActivity extends BaseActivity implements View.OnClickListener {

    //体育成绩界面
    private ImageView ivBack;
    private ListView lvStudyRank;
    private SportsRankAdapter adapter;

    private List<TiCe> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_rank);


        initView();
        initData();
        initListener();
    }
    private void initData() {
        list= TiCeCtrl.select();
        adapter.setList(list);


    }
    private void initListener() {
        ivBack.setOnClickListener(this);

    }

    private void initView() {
        ivBack=findViewById(R.id.iv_back);
        lvStudyRank=findViewById(R.id.lv_study_rank);
        adapter=new SportsRankAdapter(this);
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
