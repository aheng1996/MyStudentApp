package com.example.mystudentapp.db.ctrl;

import com.example.mystudentapp.db.bean.CaiLiaoBiao;
import com.example.mystudentapp.db.bean.ChengjiBiao;

import org.litepal.LitePal;

import java.util.List;

public class CaiLiaoCtrl {
    /**
     * @param xueHao
     * @param type   0 文体 1 能力 2 品德
     * @return
     */
    public static String getPicPath(String xueHao, int type) {
        List<CaiLiaoBiao> list = LitePal.where("xuehao=?", xueHao).find(CaiLiaoBiao.class);
        if (list != null && list.size() > 0) {
            switch (type) {
                case 0:
                    return list.get(0).getWenti();
                case 1:
                    return list.get(0).getNengli();
                case 2:
                    return list.get(0).getPinde();
            }
            return null;
        } else {
            return null;
        }
    }

    public static void save(CaiLiaoBiao caiLiaoBiao) {
        caiLiaoBiao.save();
    }
    public static CaiLiaoBiao finde(String xueHao) {
       return LitePal.where("xuehao=?", xueHao).findFirst(CaiLiaoBiao.class);
    }
}
