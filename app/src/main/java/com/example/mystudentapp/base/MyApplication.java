package com.example.mystudentapp.base;

import android.app.Application;
import android.util.Log;

import org.litepal.LitePalApplication;

public class MyApplication extends LitePalApplication {
    private static MyApplication instance;
    public static MyApplication getGlobalContext() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
