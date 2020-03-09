package com.example.mystudentapp.activity;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mystudentapp.R;
import com.example.mystudentapp.base.BaseActivity;
import com.example.mystudentapp.base.BaseMeassage;
import com.example.mystudentapp.db.bean.ZongHeCeping;
import com.example.mystudentapp.db.ctrl.CePingJieGuoCtrl;
import com.example.mystudentapp.db.xsl.Read;
import com.example.mystudentapp.utils.FileUtil;
import com.example.mystudentapp.utils.FileUtils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class AdminActivity extends BaseActivity implements View.OnClickListener, Read.SaveBack {
    //管理员界面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.student).setOnClickListener(this);
        findViewById(R.id.teacher).setOnClickListener(this);
        findViewById(R.id.tice).setOnClickListener(this);
        findViewById(R.id.xuexi).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.tv_generation).setOnClickListener(this);
        findViewById(R.id.tv_out).setOnClickListener(this);
        findViewById(R.id.tv_notice).setOnClickListener(this);
    }

    private int type = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.student:
                type = 0;
                getFIle();
                break;
            case R.id.teacher:
                type = 1;
                getFIle();
                break;
            case R.id.tice:
                type = 2;
                getFIle();
                break;
            case R.id.xuexi:
                type = 3;
                getFIle();
                break;
            case R.id.tv_generation:
//                showLandingDialog("正在生成成绩");
                CePingJieGuoCtrl.generation();
//                hideDialog();
                Toast.makeText(this, "生成成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_out:
                generation();
                break;
            case R.id.tv_notice:
                goToActivity(NoticeActivity.class);
                break;
        }
    }

    private void generation() {
        List<ZongHeCeping> lk = CePingJieGuoCtrl.select();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        HSSFRow row = sheet.createRow(0);
//        HSSFCellStyle style = wb.createCellStyle();
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("学号");
//        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("姓名");
//        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("学业成绩(50%)");
//        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("能力表现(20%)");
//        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("文体表现(10%)");
//        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("品德表现(20%)");
//        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("总分");
        cell = row.createCell(7);
        cell.setCellValue("排名");
//        cell.setCellStyle(style);
        for (int i = 0; i < lk.size(); i++) {
            //创建行
            row = sheet.createRow(i + 1);
            ZongHeCeping ti = lk.get(i);
            //创建单元格并且添加数据
            row.createCell(0).setCellValue(ti.getXuehao());
            row.createCell(1).setCellValue(ti.getXxingming());
            row.createCell(2).setCellValue(ti.getXueye());
            row.createCell(3).setCellValue(ti.getNengli());
            row.createCell(4).setCellValue(ti.getWenti());
            row.createCell(5).setCellValue(ti.getPinde());
            row.createCell(6).setCellValue(ti.getZongfen());
            row.createCell(7).setCellValue(ti.getPaiming());
        }
        try {
            String path = FileUtils.path + File.separator + "学生成绩.xls";
            File fileDir = new File(FileUtils.path);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fout = new FileOutputStream(path);
            wb.write(fout);
            fout.close();
            Toast.makeText(this, "导出成功，文件位置为/sdcard/bs/学生成绩.xls", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Read read;
    private String path;

    private void getFIle() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            path = FileUtils.path + "/use/" + System.currentTimeMillis() + ".xls";
//            File file1 = new File(FileUtils.path + "/use");
//            if (!file1.exists()) {
//                file1.mkdir();
//            }
//            File file = new File(path);
//            if (!file.exists()) {
//                try {
//                    file.createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            Uri uri = FileProvider.getUriForFile(this, "com.example.mystudentapp.fileprovider", file);
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        }
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        this.startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            // 用户未选择任何文件，直接返回
            return;
        }
        Uri uri = data.getData(); // 获取用户选择文件的URI
//        String path = FileUtil.getFilePathByUri(this, uri);
        String path = FileUtil.getRealFilePathFromUri(this, uri);
        Log.e(">>>>>>>>>>>>>", "student: " + path);
        if (path == null) {
            path = uri.getPath();
        }

        if (read == null) {
            read = new Read(this);
        }
        switch (type) {
            case 0:
                read.student(path);
                break;
            case 1:
                read.teacher(path);
                break;
            case 2:
                read.TiCe(path);
                break;
            case 3:
                read.xuexi(path);
                break;
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (null != cursor && cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
            cursor.close();
        }
        return res;
    }

    @Override
    public void onSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
