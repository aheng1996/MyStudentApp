package com.example.mystudentapp.db.ctrl;

import com.example.mystudentapp.db.bean.ChengjiBiao;
import com.example.mystudentapp.db.bean.Student;

import org.litepal.LitePal;

import java.util.List;

public class StudentCtrl {
    /**
     * 小组
     * 教师
     * @return
     */
    public static List<Student> selectAll() {
        return LitePal.where("1=1").find(Student.class);
    }
    public static void delete() {
        LitePal.deleteAllAsync(Student.class, "1=1");
    }
    public static List<Student> selectOther(String xueHao) {
        return LitePal.where("xueHao!=?",xueHao).find(Student.class);
    }
}
