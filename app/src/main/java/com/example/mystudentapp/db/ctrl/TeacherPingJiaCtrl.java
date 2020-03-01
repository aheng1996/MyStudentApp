package com.example.mystudentapp.db.ctrl;


import com.example.mystudentapp.db.bean.Student;
import com.example.mystudentapp.db.bean.TeacherPingjia;

import org.litepal.LitePal;

import java.util.List;

public class TeacherPingJiaCtrl {
    /**
     * 互评
     *
     * @return
     */
    public static List<Student> selectOther(String xueHao) {
        List<Student> list = LitePal.where("1=1").find(Student.class);
        List<TeacherPingjia> listHuPing = LitePal.where("pingJiaRen = ? ", xueHao).find(TeacherPingjia.class);
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
    public static void save(TeacherPingjia huPing) {
        if (huPing.getId() > 0) {
            huPing.save();
        } else {
            List<TeacherPingjia> list = LitePal.where("xuehao = ? and pingJiaRen= ? ", huPing.getXuehao(), huPing.getPingJiaRen()).find(TeacherPingjia.class);
            if (list == null || list.size() == 0) {
                huPing.save();
            } else if (list.size() == 1) {
                huPing.setId(list.get(0).getId());
                huPing.save();
            } else {
                LitePal.deleteAll(TeacherPingjia.class, "xuehao = ? and pingJiaRen= ? ", huPing.getXuehao(), huPing.getPingJiaRen());
                huPing.save();
            }
        }
    }
}
