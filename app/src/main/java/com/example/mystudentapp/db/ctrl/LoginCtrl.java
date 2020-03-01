package com.example.mystudentapp.db.ctrl;

import com.example.mystudentapp.bean.User;
import com.example.mystudentapp.db.bean.Student;
import com.example.mystudentapp.db.bean.Teacher;

import org.litepal.LitePal;

public class LoginCtrl {
    /**
     * @param name
     * @param pass
     * @return 0 用户名或密码错误
     */
    public static User login(String name, String pass) {
        if (name == null || name.length() < 1) {
            return null;
        }
        User user = new User();
        if ("admin".equals(name) && "123456".equals(pass)) {
            user.setType(1);
            user.setBianHao("admin");
            user.setName("管理员");
            user.setXiaoZu(false);
            return user;
        } else if (name.length() < 10) {
            Teacher userBean = LitePal.where("zhanghao=? and mima= ?", name, pass).findFirst(Teacher.class);
            if (userBean == null) {
                return null;
            } else {
                user.setType(2);
                user.setBianHao(userBean.getZhanghao());
                user.setName(userBean.getName());
                user.setXiaoZu(false);
                return user;
            }
        } else {
            Student userBean = LitePal.where("xueHao= ? and mima= ?", name, pass).findFirst(Student.class);
            if (userBean == null) {
                return null;
            } else {
                user.setType(3);
                user.setBianHao(userBean.getXueHao());
                user.setName(userBean.getName());
                user.setXiaoZu(userBean.isXiaoZu());
                return user;
            }
        }
    }
}
