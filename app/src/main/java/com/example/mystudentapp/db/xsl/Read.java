package com.example.mystudentapp.db.xsl;

import android.util.Log;

import com.example.mystudentapp.db.bean.ChengjiBiao;
import com.example.mystudentapp.db.bean.Student;
import com.example.mystudentapp.db.bean.Teacher;
import com.example.mystudentapp.db.bean.TiCe;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.litepal.LitePal;
import org.litepal.crud.callback.SaveCallback;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Read {
    public void student(String  path) {
        List<Student> list = new ArrayList<>();
        try {
            File file = new File(path);
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            HSSFSheet st = getSheet(0, in);
            //从规定位置，开始逐行读取内容
            int i = 0;
            for (int rowIndex = 1; rowIndex < 100; rowIndex++) {
                HSSFRow row = st.getRow(rowIndex);
                if (getItem(row, 0).length() > 5) {
                    Student student = new Student();
                    student.setName(getItem(row, 1));
                    student.setXueHao(getItem(row, 0));
                    student.setMima(getItem(row, 2));
                    if (i < 5) {
                        i++;
                        student.setXiaoZu(true);
                    } else {
                        student.setXiaoZu(false);
                    }
                    list.add(student);
                }
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LitePal.deleteAllAsync(Student.class, "1=1");
        LitePal.saveAllAsync(list).listen(new SaveCallback() {
            @Override
            public void onFinish(boolean success) {
                Log.e(">>>>>", "student" + success);
            }
        });
    }
    public void teacher(String  path) {
        List<Teacher> list = new ArrayList<>();
        try {
            File file = new File(path);
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            HSSFSheet st = getSheet(0, in);
            //从规定位置，开始逐行读取内容
            int i = 0;
            for (int rowIndex = 1; rowIndex < 100; rowIndex++) {
                HSSFRow row = st.getRow(rowIndex);
                if (getItem(row, 0).length() > 5) {
                    Teacher student = new Teacher();
                    student.setName(getItem(row, 1));
                    student.setZhanghao(getItem(row, 0));
                    student.setMima(getItem(row, 2));
                    list.add(student);
                }
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LitePal.deleteAllAsync(Teacher.class, "1=1");
        LitePal.saveAllAsync(list).listen(new SaveCallback() {
            @Override
            public void onFinish(boolean success) {
                Log.e(">>>>>", "teacher" + success);
            }
        });
    }
    public void TiCe(String  path) {
        List<TiCe> list = new ArrayList<>();
        try {
            File file = new File(path);
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            HSSFSheet st = getSheet(0, in);
            //从规定位置，开始逐行读取内容
            int i = 0;
            for (int rowIndex = 1; rowIndex < 100; rowIndex++) {
                HSSFRow row = st.getRow(rowIndex);
                if (getItem(row, 0).length() > 5) {
                    TiCe student = new TiCe();
                    student.setXuehao(getItem(row, 0));
                    student.setXingming(getItem(row, 1));
                    student.setFenshu(getItem(row, 2));
                    list.add(student);
                }
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LitePal.deleteAllAsync(TiCe.class, "1=1");
        LitePal.saveAllAsync(list).listen(new SaveCallback() {
            @Override
            public void onFinish(boolean success) {
                Log.e(">>>>>", "tice" + success);
            }
        });
    }
    public void xuexi(String  path) {
        List<ChengjiBiao> list = new ArrayList<>();
        try {
            File file = new File(path);
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            HSSFSheet st = getSheet(0, in);
            //从规定位置，开始逐行读取内容
            int i = 0;
            for (int rowIndex = 1; rowIndex < 100; rowIndex++) {
                HSSFRow row = st.getRow(rowIndex);
                if (getItem(row, 0).length() > 5) {
                    ChengjiBiao student = new ChengjiBiao();
                    student.setXuehao(getItem(row, 0));
                    student.setXingming(getItem(row, 1));
                    student.setFenshu(getItem(row, 2));
                    list.add(student);
                }
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LitePal.deleteAllAsync(ChengjiBiao.class, "1=1");
        LitePal.saveAllAsync(list).listen(new SaveCallback() {
            @Override
            public void onFinish(boolean success) {
                Log.e(">>>>>", "xuexi" + success);
            }
        });
    }
    private HSSFSheet getSheet(int p, BufferedInputStream in) throws IOException {
        POIFSFileSystem fs = new POIFSFileSystem(in);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        return wb.getSheetAt(p);
    }

    private static String getItem(HSSFRow row, int p) {
        if (row == null) {
            return "";
        }
        if (row.getCell(p) != null) {
            row.getCell(p).setCellType(CellType.STRING);
            return row.getCell(p).getStringCellValue();
        }
        return "";
    }

}
