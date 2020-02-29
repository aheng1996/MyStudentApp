package com.example.mystudentapp.db.bean;

import org.litepal.crud.LitePalSupport;

public class ZongHeCeping extends LitePalSupport {
    private int id;
    private String xuehao;
    private String xxingming;
    private double huping;
    private double xiaozu;
    private double laoshi;
    private double zongfen;
    private int paiming;

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

    public String getXxingming() {
        return xxingming;
    }

    public void setXxingming(String xxingming) {
        this.xxingming = xxingming;
    }

    public double getHuping() {
        return huping;
    }

    public void setHuping(double huping) {
        this.huping = huping;
    }

    public double getXiaozu() {
        return xiaozu;
    }

    public void setXiaozu(double xiaozu) {
        this.xiaozu = xiaozu;
    }

    public double getLaoshi() {
        return laoshi;
    }

    public void setLaoshi(double laoshi) {
        this.laoshi = laoshi;
    }

    public double getZongfen() {
        return zongfen;
    }

    public void setZongfen(double zongfen) {
        this.zongfen = zongfen;
    }

    public int getPaiming() {
        return paiming;
    }

    public void setPaiming(int paiming) {
        this.paiming = paiming;
    }
}
