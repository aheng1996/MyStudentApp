package com.example.mystudentapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.db.bean.Student;
import com.example.mystudentapp.db.bean.ZongHeCeping;

import java.util.ArrayList;
import java.util.List;

public class ZongHeCepingAdater extends BaseAdapter {
    private List<ZongHeCeping> list;
    private Context context =null;


    public ZongHeCepingAdater(Context context){
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
            view = LayoutInflater.from(context).inflate(R.layout.item_zongheceing,viewGroup,false);
            viewHolder = new ViewHolder();

            viewHolder.xuehao=view.findViewById(R.id.xuehao);
            viewHolder.huping=view .findViewById(R.id.huping);
            viewHolder.laoshi=view .findViewById(R.id.laoshi);
            viewHolder.xiaozu=view .findViewById(R.id.xiaozu);
            viewHolder.wenti=view .findViewById(R.id.wenti);
            viewHolder.zongfen=view .findViewById(R.id.zongfen);
            viewHolder.paiming=view .findViewById(R.id.paiming);

            view .setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view .getTag();
        }
        viewHolder.xuehao.setText(list.get(i).getXuehao());
        viewHolder.huping.setText(list.get(i).getXueye()+"");
        viewHolder.laoshi.setText(list.get(i).getNengli()+"");
        viewHolder.xiaozu.setText(list.get(i).getWenti()+"");
        viewHolder.wenti.setText(list.get(i).getPinde()+"");
        viewHolder.zongfen.setText(list.get(i).getZongfen()+"");
        viewHolder.paiming.setText(list.get(i).getPaiming()+"");
        return view ;
    }

    public void setList(List<ZongHeCeping>list){
        this.list = list;
        notifyDataSetChanged();
    }

    private class ViewHolder {
        TextView xuehao;
        TextView huping;
        TextView laoshi;
        TextView xiaozu;
        TextView wenti;
        TextView zongfen;
        TextView paiming;


    }
}
