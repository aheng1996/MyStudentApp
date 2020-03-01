package com.example.mystudentapp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.mystudentapp.R;
import com.example.mystudentapp.base.BaseActivity;
import com.example.mystudentapp.db.xsl.Read;

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
        }
    }

    private Read read;

    private void getFIle() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
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
        String path = getRealPathFromURI(uri);
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
