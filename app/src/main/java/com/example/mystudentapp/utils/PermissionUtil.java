package com.example.mystudentapp.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public enum PermissionUtil {
    INSTANCE;
    public static final int PERMISSION_CODE = 102;
    private List<String> noPermissions;
    @NonNull
    private String[] permissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.INTERNET,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public boolean allPermission(@NonNull Activity context) {
        noPermissions = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            noPermissions.clear();
            for (int i = 0; i < permissions.length; i++) {
                int per = ContextCompat.checkSelfPermission(context, permissions[i]);
                if (per != PackageManager.PERMISSION_GRANTED) {
                    //认为没有获取到权限
                    noPermissions.add(permissions[i]);
                }
            }
            if (noPermissions.size() > 0) {
                for (String s : noPermissions) {
                    Log.e(">>>>>", s);
                }
                String[] permissionsA = noPermissions.toArray(new String[noPermissions.size()]);
                ActivityCompat.requestPermissions(context, permissionsA, PERMISSION_CODE);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public boolean hasPermission(@NonNull String permission, @NonNull Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int per = ContextCompat.checkSelfPermission(context, permission);
            if (per != PackageManager.PERMISSION_GRANTED) {
                //认为没有获取到权限
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public void getPermission(String perission, @NonNull Activity activity) {
        ActivityCompat.requestPermissions(activity, permissions, PERMISSION_CODE);
    }

    public boolean getallPermission(@NonNull Activity activity) {
        ActivityCompat.requestPermissions(activity, permissions, PERMISSION_CODE);
        return false;
    }

//    public void getPermission(String permissions) {
//        ActivityCompat.requestPermissions(context, permissions, PERMISSION_CODE);
//    }
}
