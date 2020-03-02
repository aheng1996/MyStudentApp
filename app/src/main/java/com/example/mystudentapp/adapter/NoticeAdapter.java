package com.example.mystudentapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.db.bean.GongGao;

import java.util.ArrayList;
import java.util.List;

public class NoticeAdapter extends BaseAdapter {

    private List<GongGao> list;
    private Context context =null;

    public NoticeAdapter(Context context){
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
            view = LayoutInflater.from(context).inflate(R.layout.item_notice,viewGroup,false);
            viewHolder = new ViewHolder();

            viewHolder.tvTitle=view.findViewById(R.id.tv_title);

            view .setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view .getTag();
        }
        viewHolder.tvTitle.setText(list.get(i).getTitle());


        return view ;
    }

    public void setList(List<GongGao>list){
        this.list = list;
        notifyDataSetChanged();
    }

    private class ViewHolder {
        TextView tvTitle;


    }
}
