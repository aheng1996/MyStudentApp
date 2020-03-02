package com.example.mystudentapp.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.adapter.StudentHuPinAdater;
import com.example.mystudentapp.adapter.ZongHeCepingAdater;
import com.example.mystudentapp.base.BaseFragment;
import com.example.mystudentapp.db.bean.Student;
import com.example.mystudentapp.db.bean.ZongHeCeping;
import com.example.mystudentapp.db.ctrl.CePingJieGuoCtrl;
import com.example.mystudentapp.db.ctrl.XiaoZuCtrl;

import java.util.List;


public class ResultFragment extends BaseFragment {
    private ListView lvStudent;  //除自己以为的学生列表
    private List<ZongHeCeping> list;
    private ZongHeCepingAdater adapter;

    public ResultFragment() {
        // Required empty public constructor
    }


    public static ResultFragment newInstance() {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, "");
        args.putString(ARG_PARAM2, "");
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_result, container, false);
        lvStudent = inflate.findViewById(R.id.lv_student);
        adapter = new ZongHeCepingAdater(getContext());
        lvStudent.setAdapter(adapter);
        list = CePingJieGuoCtrl.select();
        adapter.setList(list);
        return inflate;
    }


}
