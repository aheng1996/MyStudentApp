package com.example.mystudentapp.db.ctrl;

import com.example.mystudentapp.db.bean.CaiLiaoBiao;
import com.example.mystudentapp.db.bean.ChengjiBiao;

import org.litepal.LitePal;

import java.util.List;

public class CaiLiaoCtrl {
    public static String getPicPath(String xueHao) {
        List<CaiLiaoBiao> list = LitePal.where("xuehao=?", xueHao).find(CaiLiaoBiao.class);
        if (list != null && list.size() > 0) {
            return list.get(0).getPath();
        } else {
            return null;
        }
    }
    public static void save(CaiLiaoBiao caiLiaoBiao) {
        caiLiaoBiao.save();
    }
}
