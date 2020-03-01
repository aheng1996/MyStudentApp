package com.example.mystudentapp.base;

import android.provider.CalendarContract;

import com.example.mystudentapp.bean.User;

import java.util.List;

public enum  BaseMeassage {

    INSTANCE;

    /**
     * 人员选择
     */
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
