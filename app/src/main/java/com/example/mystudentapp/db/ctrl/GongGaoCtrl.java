package com.example.mystudentapp.db.ctrl;

import com.example.mystudentapp.db.bean.ChengjiBiao;
import com.example.mystudentapp.db.bean.GongGao;

import org.litepal.LitePal;

import java.util.List;

public class GongGaoCtrl {
    public static List<GongGao> select() {
        return LitePal.where("1=1").find(GongGao.class);
    }

    public static void add(GongGao gongGao) {
        gongGao.setId(0);
        gongGao.save();
    }

    public static void delete(int id) {
        LitePal.delete(GongGao.class, id);
    }
}
