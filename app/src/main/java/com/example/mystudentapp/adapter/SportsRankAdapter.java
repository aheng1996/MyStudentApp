package com.example.mystudentapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.db.bean.ChengjiBiao;
import com.example.mystudentapp.db.bean.TiCe;

import java.util.ArrayList;
import java.util.List;

public class SportsRankAdapter extends BaseAdapter {
    private List<TiCe> list;
    private Context context =null;
    public SportsRankAdapter(Context context){
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
            view = LayoutInflater.from(context).inflate(R.layout.item_rank,viewGroup,false);
            viewHolder = new ViewHolder();

            viewHolder.tvXuehao=view.findViewById(R.id.tv_xuehao);
            viewHolder.tvName=view .findViewById(R.id.tv_name);
            viewHolder.tvRank=view .findViewById(R.id.tv_rank);

            view .setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view .getTag();
        }
        viewHolder.tvXuehao.setText(list.get(i).getXuehao());
        viewHolder.tvName.setText(list.get(i).getXingming());
        viewHolder.tvRank.setText(list.get(i).getFenshu());
        return view ;
    }

    public void setList(List<TiCe>list){
        this.list = list;
        notifyDataSetChanged();
    }

    private class ViewHolder {
        TextView tvXuehao;
        TextView tvName;
        TextView tvRank;

    }
}
