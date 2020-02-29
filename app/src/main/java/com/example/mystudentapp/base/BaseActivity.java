package com.example.mystudentapp.base;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mystudentapp.R;
import com.example.mystudentapp.view.SpotsDialog;
import com.gyf.immersionbar.ImmersionBar;


public class BaseActivity extends AppCompatActivity {

    private String desUserName;
    private String desPassword;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        isCloseSeekBar();
        //将activity加入到AppManager堆栈中
        //沉浸式状态栏
        ImmersionBar.with(this).statusBarDarkFont(true,0.2f)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.white)

                .init();
        ActivityManager.INSTANCE.addActivity(this);
    }

    public void showToast(String msg) {
        if (msg == null) {
            return;
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }



    public void showToasttiaoshi(String msg) {
        if (false) {
            return;
        }
        if (msg == null) return;
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.INSTANCE.removeActivity(this);

    }

    public void goToActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);

        startActivity(intent);
    }

    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

    }



    public void setSeekBarColor(int color) {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(color);
        }
    }

    private SpotsDialog spotsDialog;

    public void showLandingDialog(String msg) {
        if (spotsDialog == null) {
            spotsDialog = (SpotsDialog) new SpotsDialog.Builder()
                    .setContext(this).build();
        }
        spotsDialog.setMessage(msg);
        spotsDialog.show();
    }

    public void hideDialog() {
        if (spotsDialog != null) {
            spotsDialog.dismiss();
        }

    }

    public void backClick(View view) {
        onBackPressed();
    }

//    @Override
//    public void relogin() {
//        sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
//        desUserName = sharedPreferences.getString("name", "");
//        desPassword = sharedPreferences.getString("pwd", "");
//        UrlNet.login(desUserName, desPassword).build().execute(callback);
//    }
//    private StringCallback callback = new StringCallback() {
//        @Override
//        public void onError(Call call, Exception e, int id) {
////            Log.e("<<<<<<<<<<<<","登录error");
//            hideDialog();
//            showToast("网络错误");
//            gotoLoginActivity();
//            e.printStackTrace();
//        }
//
//        @Override
//        public void onResponse(String response, int id) {
//
//            Log.e("<<<<<<<????","s="+response);
//            hideDialog();
//            try {
//                JSONObject jsonObject = new JSONObject(response);
//                String b = jsonObject.optString("result");
//                if (b.equals("true")) {
//
//                    //使用SharedPreferences来保存用户名和密码
//                    SharedPreferences sp = getSharedPreferences("info",MODE_PRIVATE);
//                    SharedPreferences.Editor ed = sp.edit();
//                    ed.putString("name",desUserName);
//                    ed.putString("pwd",desPassword);
//                    ed.commit();
//
//                } else {
//                    //失败
//                    String msg = jsonObject.optString("message");
//                    showToast(msg + "错误");
////                    String _sid = jsonObject.optString("sessionid");
//
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//                hideDialog();
//            }
//
//
//        }
//    };
//
//    private void gotoLoginActivity() {
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
//        ActivityManager.INSTANCE.finishActivity(this);
//    }


}