package com.example.mystudentapp.db.ctrl;

import com.example.mystudentapp.db.bean.ChengjiBiao;
import com.example.mystudentapp.db.bean.TiCe;

import org.litepal.LitePal;

import java.util.List;

public class ChengjiCtrl {
    public static List<ChengjiBiao> select() {
        return LitePal.where("1=1").find(ChengjiBiao.class);
    }
}
