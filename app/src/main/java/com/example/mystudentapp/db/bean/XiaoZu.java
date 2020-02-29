package com.example.mystudentapp.db.bean;

import org.litepal.crud.LitePalSupport;

public class XiaoZu extends LitePalSupport {
    private int id;
    private String xuehao;
    private double jiBenFen;
    private double jianglifen;
    private double nenglifen;
    private double zongFen;
    private int number;

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

    public double getJianglifen() {
        return jianglifen;
    }

    public void setJianglifen(double jianglifen) {
        this.jianglifen = jianglifen;
    }

    public double getNenglifen() {
        return nenglifen;
    }

    public void setNenglifen(double nenglifen) {
        this.nenglifen = nenglifen;
    }

    public double getZongFen() {
        return zongFen;
    }

    public void setZongFen(double zongFen) {
        this.zongFen = zongFen;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
