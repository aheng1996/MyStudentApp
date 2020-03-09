package com.example.mystudentapp.activity;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.mystudentapp.R;
import com.example.mystudentapp.base.BaseActivity;
import com.example.mystudentapp.base.BaseMeassage;
import com.example.mystudentapp.bean.User;
import com.example.mystudentapp.db.bean.CaiLiaoBiao;
import com.example.mystudentapp.db.ctrl.CaiLiaoCtrl;
import com.jelly.mango.ImageSelectListener;
import com.jelly.mango.Mango;
import com.jelly.mango.MultiplexImage;

import java.util.ArrayList;
import java.util.List;

public class InfoAddActivity extends BaseActivity implements View.OnClickListener, ImageSelectListener {
    //材料补充界面

    private ImageView ivBack;
    private ImageView iv_cailiao1, iv_cailiao2, iv_cailiao3;  //文体，能力，品德
    private TextView tv_xuehao;
    private TextView tv_name;
    private User user;
    private String path1, path2, path3;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_add);
        user = BaseMeassage.INSTANCE.getUser();
        initView();
        initListener();
        path1 = CaiLiaoCtrl.getPicPath(user.getBianHao(), 0);
        if (path1 != null) {
            Glide.with(this).load(path1).into(iv_cailiao1);  // 这做过了修改
        }
        path2 = CaiLiaoCtrl.getPicPath(user.getBianHao(), 1);
        if (path2 != null) {
            Glide.with(this).load(path2).into(iv_cailiao2);  // 这做过了修改
        }
        path3 = CaiLiaoCtrl.getPicPath(user.getBianHao(), 2);
        if (path3 != null) {
            Glide.with(this).load(path3).into(iv_cailiao3);  // 这做过了修改
        }
    }

    private void initListener() {
        ivBack.setOnClickListener(this);
        iv_cailiao1.setOnClickListener(this);
        iv_cailiao2.setOnClickListener(this);
        iv_cailiao3.setOnClickListener(this);

    }

    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        tv_xuehao = findViewById(R.id.tv_xuehao);
        iv_cailiao1 = findViewById(R.id.iv_cailiao1);
        iv_cailiao2 = findViewById(R.id.iv_cailiao2);
        iv_cailiao3 = findViewById(R.id.iv_cailiao3);
        tv_name = findViewById(R.id.tv_name);
        tv_xuehao.setText(user.getBianHao());
        tv_name.setText(user.getName());
    }

    private static final int PICK_PHOTO1 = 1;
    private static final int PICK_PHOTO2 = 2;
    private static final int PICK_PHOTO3 = 3;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_cailiao1:
                if (path1 == null) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    //Intent.ACTION_GET_CONTENT = "android.intent.action.GET_CONTENT"
                    intent.setType("image/*");
                    startActivityForResult(intent, PICK_PHOTO1); // 打开相册
                } else {
                    showToast("您已上传材料，请勿重复上传");
                }
                break;
            case R.id.iv_cailiao2:
                if (path2 == null) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    //Intent.ACTION_GET_CONTENT = "android.intent.action.GET_CONTENT"
                    intent.setType("image/*");
                    startActivityForResult(intent, PICK_PHOTO2); // 打开相册
                } else {
                    showToast("您已上传材料，请勿重复上传");
                }
                break;
            case R.id.iv_cailiao3:
                if (path3 == null) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    //Intent.ACTION_GET_CONTENT = "android.intent.action.GET_CONTENT"
                    intent.setType("image/*");
                    startActivityForResult(intent, PICK_PHOTO3); // 打开相册
                } else {
                    showToast("您已上传材料，请勿重复上传");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_PHOTO1:
                if (resultCode == RESULT_OK) { // 判断手机系统版本号
                    // 4.4及以上系统使用这个方法处理图片
                    handleImageOnKitKat(data, 1);
                }
                break;
            case PICK_PHOTO2:
                if (resultCode == RESULT_OK) { // 判断手机系统版本号
                    // 4.4及以上系统使用这个方法处理图片
                    handleImageOnKitKat(data, 2);
                }
                break;
            case PICK_PHOTO3:
                if (resultCode == RESULT_OK) { // 判断手机系统版本号
                    // 4.4及以上系统使用这个方法处理图片
                    handleImageOnKitKat(data, 3);
                }
                break;
            default:
                break;
        }

    }

    private void handleImageOnKitKat(Intent data, int type) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content: //downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }

        // 根据图片路径显示图片
        CaiLiaoBiao caiLiaoBiao = CaiLiaoCtrl.finde(user.getBianHao());
        if (caiLiaoBiao == null) {
            caiLiaoBiao = new CaiLiaoBiao();
            caiLiaoBiao.setXuehao(user.getBianHao());
        }
        switch (type) {
            case 1:
                path1 = imagePath;
                caiLiaoBiao.setWenti(imagePath);
                Glide.with(this).load(path1).into(iv_cailiao1);
                break;
            case 2:
                path2 = imagePath;
                caiLiaoBiao.setNengli(imagePath);
                Glide.with(this).load(path2).into(iv_cailiao2);
                break;
            case 3:
                path3 = imagePath;
                caiLiaoBiao.setPinde(imagePath);
                Glide.with(this).load(path3).into(iv_cailiao3);
                break;
        }
        CaiLiaoCtrl.save(caiLiaoBiao);

    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    @Override
    public void select(int index) {

    }
}
