package com.example.mystudentapp.db.ctrl;

import android.util.Log;

import com.example.mystudentapp.db.bean.HuPing;
import com.example.mystudentapp.db.bean.Student;
import com.example.mystudentapp.db.bean.TeacherPingjia;
import com.example.mystudentapp.db.bean.XiaoZu;
import com.example.mystudentapp.db.bean.ZongHeCeping;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class CePingJieGuoCtrl {
    public static void generation() {
        //删除
        LitePal.deleteAll(ZongHeCeping.class);
        //添加
        List<Student> list = LitePal.where("1=1").find(Student.class);
        List<ZongHeCeping> myLIst = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ZongHeCeping zongHeCeping = new ZongHeCeping();

            zongHeCeping.setXxingming(list.get(i).getName());
            zongHeCeping.setXuehao(list.get(i).getXueHao());
            String xueHao = list.get(i).getXueHao();
            {
                List<HuPing> huPingsList = LitePal.where("xuehao = ?", xueHao).find(HuPing.class);
                double number = 0;
                for (int j = 0; j < huPingsList.size(); j++) {
                    number = number + huPingsList.get(j).getJiBenFen() + huPingsList.get(j).getJiangli() + huPingsList.get(j).getPinde();
                }
                if (huPingsList.size()>0){
                    number = number / huPingsList.size();
                }
                zongHeCeping.setHuping(number);
            }
            {
                List<XiaoZu> huPingsList = LitePal.where("xuehao = ?", xueHao).find(XiaoZu.class);
                double number = 0;
                for (int j = 0; j < huPingsList.size(); j++) {
                    number = number + huPingsList.get(j).getJiBenFen() + huPingsList.get(j).getJianglifen() + huPingsList.get(j).getNenglifen();
                }
                if (huPingsList.size()>0){
                    number = number / huPingsList.size();
                }
                zongHeCeping.setXiaozu(number);
            }
            {
                List<TeacherPingjia> huPingsList = LitePal.where("xuehao = ?", xueHao).find(TeacherPingjia.class);
                double number = 0;
                for (int j = 0; j < huPingsList.size(); j++) {
                    number = number + huPingsList.get(j).getJiBenFen() + huPingsList.get(j).getYuanShi() + huPingsList.get(j).getBianXian();
                }
                if (huPingsList.size()>0){
                    number = number / huPingsList.size();
                }
                zongHeCeping.setLaoshi(number);
            }
            zongHeCeping.setZongfen(zongHeCeping.getHuping() * 0.30 + zongHeCeping.getXiaozu() * 0.20 + zongHeCeping.getLaoshi() * 0.50);
            myLIst.add(zongHeCeping);
        }
        LitePal.saveAll(myLIst);
        //设置分数
        List<ZongHeCeping> chengjiLIst = LitePal.where("1=1 ORDER BY zongfen desc").find(ZongHeCeping.class);
        double zongfen = 200;
        int mingCi = 0;
        for (int i = 0; i < chengjiLIst.size(); i++) {
            double myFen = chengjiLIst.get(i).getZongfen();
            if (myFen == zongfen) {
                chengjiLIst.get(i).setPaiming(mingCi);
            } else if (myFen < zongfen) {
                mingCi = i + 1;
                zongfen = myFen;
                chengjiLIst.get(i).setPaiming(mingCi);
            } else {
                Log.e(">>>>>>>>>>>", zongfen + "generation: ????" + myFen);
            }
        }
        LitePal.saveAll(chengjiLIst);
    }

    public static List<ZongHeCeping> select() {
        //设置分数
        return LitePal.where("1=1 ORDER BY zongfen desc").find(ZongHeCeping.class);
    }
}
