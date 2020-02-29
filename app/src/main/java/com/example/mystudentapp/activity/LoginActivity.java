package com.example.mystudentapp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.base.BaseActivity;
import com.example.mystudentapp.db.ctrl.LoginCtrl;
import com.example.mystudentapp.db.xsl.Read;
import com.example.mystudentapp.utils.PermissionUtil;

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private EditText etUserName;
    private EditText etPassWord;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionUtil.INSTANCE.allPermission(this);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        etUserName = findViewById(R.id.et_user_name);
        etPassWord = findViewById(R.id.et_pwd);
        tvLogin = findViewById(R.id.tv_login);
        tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
//                test();
                login();
                break;

        }
    }

    private void test() {
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
        etUserName.setText(path);
        new Read().xuexi(path);
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

    private void login() {
        tvLogin.setText(LoginCtrl.login(etUserName.getText().toString(), etPassWord.getText().toString()).getName());
//        goToActivity(HomeActivity.class);

    }
}
