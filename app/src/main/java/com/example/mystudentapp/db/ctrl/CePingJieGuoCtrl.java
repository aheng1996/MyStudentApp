package com.example.mystudentapp.db.ctrl;

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
        LitePal.deleteAllAsync(ZongHeCeping.class);
        //添加
        List<Student> list = LitePal.where("1=1").find(Student.class);
        List<ZongHeCeping> myLIst = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ZongHeCeping zongHeCeping = new ZongHeCeping();
            myLIst.add(zongHeCeping);
            zongHeCeping.setXxingming(list.get(i).getName());
            zongHeCeping.setXuehao(list.get(i).getXueHao());
            String xueHao = list.get(i).getXueHao();
            {
                List<HuPing> huPingsList = LitePal.where("xuehao = ?", xueHao).find(HuPing.class);
                double number = 0;
                for (int j = 0; j < huPingsList.size(); j++) {
                    number = number + huPingsList.get(i).getJiBenFen() + huPingsList.get(i).getJiangli() + huPingsList.get(i).getPinde();
                }
                number = number / huPingsList.size();
                zongHeCeping.setHuping(number);
            }
            {
                List<XiaoZu> huPingsList = LitePal.where("xuehao = ?", xueHao).find(XiaoZu.class);
                double number = 0;
                for (int j = 0; j < huPingsList.size(); j++) {
                    number = number + huPingsList.get(i).getJiBenFen() + huPingsList.get(i).getJianglifen() + huPingsList.get(i).getNenglifen();
                }
                number = number / huPingsList.size();
                zongHeCeping.setXiaozu(number);
            }
            {
                List<TeacherPingjia> huPingsList = LitePal.where("xuehao = ?", xueHao).find(TeacherPingjia.class);
                double number = 0;
                for (int j = 0; j < huPingsList.size(); j++) {
                    number = number + huPingsList.get(i).getJiBenFen() + huPingsList.get(i).getYuanShi() + huPingsList.get(i).getBianXian();
                }
                number = number / huPingsList.size();
                zongHeCeping.setLaoshi(number);
            }
            zongHeCeping.setZongfen(zongHeCeping.getHuping() * 0.30 + zongHeCeping.getXiaozu() * 0.20 + zongHeCeping.getLaoshi() * 0.50);
        }
        LitePal.saveAll(myLIst);
        //设置分数
        List<ZongHeCeping> chengjiLIst = LitePal.where("1=1 ORDER BY zongfen").find(ZongHeCeping.class);
        double zongfen = 200;
        int mingCi = 0;
        for (int i = 0; i < chengjiLIst.size(); i++) {
            double myFen = chengjiLIst.get(i).getZongfen();
            if (myFen == zongfen) {
                chengjiLIst.get(i).setPaiming(mingCi);
            } else if (myFen < zongfen) {
                mingCi = i + 1;

            }
        }
    }
}
