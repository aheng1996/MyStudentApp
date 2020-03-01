package com.example.mystudentapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.mystudentapp.R;
import com.example.mystudentapp.db.bean.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentHuPinAdater extends BaseAdapter {

    private List<Student> list;
    private Context context =null;


    public StudentHuPinAdater(Context context){
        this.context=context;
        list=new ArrayList<>();



    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view ==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_studenthupin,viewGroup,false);
            viewHolder = new ViewHolder();

            viewHolder.tvXuehao=view.findViewById(R.id.tv_xuehao);
            viewHolder.tvName=view .findViewById(R.id.tv_name);

            view .setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view .getTag();
        }
        viewHolder.tvXuehao.setText(list.get(i).getXueHao());
        viewHolder.tvName.setText(list.get(i).getName());


        return view ;
    }

    public void setList(List<Student>list){
        this.list = list;
        notifyDataSetChanged();
    }

    private class ViewHolder {
        TextView tvXuehao;
        TextView tvName;


    }
}
