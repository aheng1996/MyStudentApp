package com.example.mystudentapp.db.bean;

import org.litepal.crud.LitePalSupport;

public class HuPing extends LitePalSupport {
    private int id;
    private String pingJiaRen;
    private String xuehao;
    private double jiBenFen;
    private double pinde;
    private double jiangli;
    private double zongFen;
    private int number;

    public double getZongFen() {
        return zongFen;
    }

    public void setZongFen(double zongFen) {
        this.zongFen = zongFen;
    }

    public String getPingJiaRen() {
        return pingJiaRen;
    }

    public void setPingJiaRen(String pingJiaRen) {
        this.pingJiaRen = pingJiaRen;
    }

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

    public double getJiBenFen() {
        return jiBenFen;
    }

    public void setJiBenFen(double jiBenFen) {
        this.jiBenFen = jiBenFen;
    }

    public double getPinde() {
        return pinde;
    }

    public void setPinde(double pinde) {
        this.pinde = pinde;
    }

    public double getJiangli() {
        return jiangli;
    }

    public void setJiangli(double jiangli) {
        this.jiangli = jiangli;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
