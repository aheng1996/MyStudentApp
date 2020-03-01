package com.example.mystudentapp.db.ctrl;

import com.example.mystudentapp.db.bean.HuPing;
import com.example.mystudentapp.db.bean.Student;

import org.litepal.LitePal;

import java.util.List;

public class HuPingCtrl {
    /**
     * 除自己以外的人()
     * 互评
     *
     * @return
     */
    public static List<Student> selectOther(String xueHao) {
        List<Student> list = LitePal.where("xueHao!=?", xueHao).find(Student.class);
        List<HuPing> listHuPing = LitePal.where("pingJiaRen = ? ", xueHao).find(HuPing.class);
        b:
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < listHuPing.size(); j++) {
                if (list.get(i).getXueHao().equals(listHuPing.get(j).getXuehao())) {
                    list.remove(i);
                    i--;
                    continue b;
                }
            }
        }
        return list;
    }
    public static void save(HuPing huPing) {
        if (huPing.getId() > 0) {
            huPing.save();
        } else {
            List<HuPing> list = LitePal.where("xuehao = ? and pingJiaRen= ? ", huPing.getXuehao(), huPing.getPingJiaRen()).find(HuPing.class);
            if (list == null || list.size() == 0) {
                huPing.save();
            } else if (list.size() == 1) {
                huPing.setId(list.get(0).getId());
                huPing.save();
            } else {
                LitePal.deleteAll(HuPing.class, "xuehao = ? and pingJiaRen= ? ", huPing.getXuehao(), huPing.getPingJiaRen());
                huPing.save();
            }
        }
    }

}
