package com.example.mystudentapp.db.bean;

import org.litepal.crud.LitePalSupport;

public class CaiLiaoBiao extends LitePalSupport {
    private int id;
    private String xuehao;
    private String wenti;
    private String nengli;
    private String pinde;
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

    public String getWenti() {
        return wenti;
    }

    public void setWenti(String wenti) {
        this.wenti = wenti;
    }

    public String getNengli() {
        return nengli;
    }

    public void setNengli(String nengli) {
        this.nengli = nengli;
    }

    public String getPinde() {
        return pinde;
    }

    public void setPinde(String pinde) {
        this.pinde = pinde;
    }

    public int getFenshu() {
        return fenshu;
    }

    public void setFenshu(int fenshu) {
        this.fenshu = fenshu;
    }
}
