package com.example.mystudentapp.db.ctrl;

import android.util.Log;

import com.example.mystudentapp.db.bean.ChengjiBiao;
import com.example.mystudentapp.db.bean.HuPing;
import com.example.mystudentapp.db.bean.Student;
import com.example.mystudentapp.db.bean.TeacherPingjia;
import com.example.mystudentapp.db.bean.XiaoZu;
import com.example.mystudentapp.db.bean.ZongHeCeping;


import org.litepal.LitePal;

import java.text.DecimalFormat;
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
                    if (huPingsList.get(j).getJiBenFen() > 0) {
                        number = number + huPingsList.get(j).getJiBenFen();
                    }
                    if (+huPingsList.get(j).getJiangli() > 0) {
                        number = number + +huPingsList.get(j).getJiangli();
                    }
                    if (huPingsList.get(j).getPinde() > 0) {
                        number = number + +huPingsList.get(j).getPinde();
                    }
                }
                if (huPingsList.size() > 0) {
                    number = number / huPingsList.size();
                }
                number = ((int) (number * 100)) * 0.01;
                zongHeCeping.setNengli(getDouble(number));
            }
            {
                List<XiaoZu> huPingsList = LitePal.where("xuehao = ?", xueHao).find(XiaoZu.class);
                double number1 = 0;
                double number2 = 0;
                for (int j = 0; j < huPingsList.size(); j++) {
                    if (huPingsList.get(j).getJiBenFen() > 0) {
                        number1 = number1 + huPingsList.get(j).getJiBenFen();
                    }
                    if (huPingsList.get(j).getNenglifen() > 0) {
                        number2 = number2 + huPingsList.get(j).getNenglifen();
                    }
                }
                number1 = ((int) (number1 * 100)) * 0.01;
                number2 = ((int) (number2 * 100)) * 0.01;
                zongHeCeping.setWenti(getDouble(number1));
                zongHeCeping.setPinde(getDouble(number2));
            }
            {
                List<TeacherPingjia> huPingsList = LitePal.where("xuehao = ?", xueHao).find(TeacherPingjia.class);
                double number = 0;
                for (int j = 0; j < huPingsList.size(); j++) {
                    number = number + huPingsList.get(j).getJiBenFen() + huPingsList.get(j).getYuanShi() + huPingsList.get(j).getBianXian();
                }
                if (huPingsList.size() > 0) {
                    number = number / huPingsList.size();
                }
                List<ChengjiBiao> chengjiBiaoList = LitePal.where("xuehao=?", xueHao).find(ChengjiBiao.class);
                if (chengjiBiaoList.size() > 0) {
                    number = number * 0.2 + Double.parseDouble(chengjiBiaoList.get(0).getFenshu()) * 0.8;
                }
                number = ((int) (number * 100)) * 0.01;
                zongHeCeping.setXueye(getDouble(number));
            }
            double number = zongHeCeping.getXueye() * 0.50 + zongHeCeping.getNengli() * 0.20 + zongHeCeping.getWenti() * 0.10 + zongHeCeping.getPinde() * 0.20;
            number = ((int) (number * 100)) * 0.01;
            zongHeCeping.setZongfen(getDouble(number));
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

    private static double getDouble(double b) {
        DecimalFormat df = new DecimalFormat("#.00");
        String str = df.format(b);
        return Double.parseDouble(str);
    }
}
