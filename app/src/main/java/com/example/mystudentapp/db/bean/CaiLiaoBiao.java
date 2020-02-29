package com.example.mystudentapp.db.bean;

import org.litepal.crud.LitePalSupport;

public class CaiLiaoBiao extends LitePalSupport {
    private int id;
    private String xuehao;
    private String path;
    private int fenshu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getXuehao() {
        return xuehao;
    }

    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getFenshu() {
        return fenshu;
    }

    public void setFenshu(int fenshu) {
        this.fenshu = fenshu;
    }
}
