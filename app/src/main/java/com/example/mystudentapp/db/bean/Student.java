package com.example.mystudentapp.db.bean;

import org.litepal.crud.LitePalSupport;

public class Student extends LitePalSupport {
    private int id;
    private String name;
    private String xueHao;
    private String mima;
    private boolean isXiaoZu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXueHao() {
        return xueHao;
    }

    public void setXueHao(String xueHao) {
        this.xueHao = xueHao;
    }

    public String getMima() {
        return mima;
    }

    public void setMima(String mima) {
        this.mima = mima;
    }

    public boolean isXiaoZu() {
        return isXiaoZu;
    }

    public void setXiaoZu(boolean xiaoZu) {
        isXiaoZu = xiaoZu;
    }
}
