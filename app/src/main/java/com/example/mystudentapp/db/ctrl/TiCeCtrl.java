package com.example.mystudentapp.db.ctrl;

import com.example.mystudentapp.db.bean.TiCe;

import org.litepal.LitePal;

import java.util.List;

public class TiCeCtrl {
    public static List<TiCe> select() {
        return LitePal.where("1=1").find(TiCe.class);
    }
}
