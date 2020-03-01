package com.example.mystudentapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * rainwatersampler
 *
 * @author sjw
 * @date 2018/9/19.
 */
public class SPUtil {
    private static SharedPreferences mSp;
    private static SharedPreferences getSharedPreferences(@NonNull Context context) {
        if (mSp == null) {
            mSp = context.getSharedPreferences("derun", Context.MODE_PRIVATE);
        }
        return mSp;
    }

    public static void putString(@NonNull Context context, String key, String path){
        getSharedPreferences(context).edit().putString(key,path).commit();
    }
    @Nullable
    public static String getString(@NonNull Context context, String key, String defValue){
        return getSharedPreferences(context).getString(key,defValue);
    }

    public static void setPic(Context context, String workId, int type, String path) {
        SPUtil.putString(context, workId +"A"+ type, path);
    }
    public static String getPic(Context context, String workId, int type) {
        return SPUtil.getString(context, workId + "A" + type, "");
    }

}
