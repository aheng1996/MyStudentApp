package com.example.mystudentapp.db.bean;

import org.litepal.crud.LitePalSupport;

public class TeacherPingjia extends LitePalSupport {
    private int id;
    private String pingJiaRen;
    private String xuehao;
    private double jiBenFen;
    private double yuanShi;
    private double bianXian;
    private double zongFen;
    private int number;

    public String getPingJiaRen() {
        return pingJiaRen;
    }

    public void setPingJiaRen(String pingJiaRen) {
        this.pingJiaRen = pingJiaRen;
    }

    public double getZongFen() {
        return zongFen;
    }

    public void setZongFen(double zongFen) {
        this.zongFen = zongFen;
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

    public double getYuanShi() {
        return yuanShi;
    }

    public void setYuanShi(double yuanShi) {
        this.yuanShi = yuanShi;
    }

    public double getBianXian() {
        return bianXian;
    }

    public void setBianXian(double bianXian) {
        this.bianXian = bianXian;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
