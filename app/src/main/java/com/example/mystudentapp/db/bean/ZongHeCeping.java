package com.example.mystudentapp.db.bean;

import org.litepal.crud.LitePalSupport;

public class ZongHeCeping extends LitePalSupport {
    private int id;
    private String xuehao;
    private String xxingming;
    private double xueye;
    private double nengli;
    private double wenti;
    private double pinde;
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

    public double getXueye() {
        return xueye;
    }

    public void setXueye(double xueye) {
        this.xueye = xueye;
    }

    public double getNengli() {
        return nengli;
    }

    public void setNengli(double nengli) {
        this.nengli = nengli;
    }

    public double getWenti() {
        return wenti;
    }

    public void setWenti(double wenti) {
        this.wenti = wenti;
    }

    public double getPinde() {
        return pinde;
    }

    public void setPinde(double pinde) {
        this.pinde = pinde;
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
