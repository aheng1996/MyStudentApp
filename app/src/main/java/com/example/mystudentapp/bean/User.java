package com.example.mystudentapp.bean;

public class User {
//         * 1 管理员
//     * 2 老师
//     * 3 学生
    private int type;
    private String bianHao; //编号
    private String name; //姓名
    private boolean isXiaoZu; //是否小组
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBianHao() {
        return bianHao;
    }

    public void setBianHao(String bianHao) {
        this.bianHao = bianHao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isXiaoZu() {
        return isXiaoZu;
    }

    public void setXiaoZu(boolean xiaoZu) {
        isXiaoZu = xiaoZu;
    }
}
