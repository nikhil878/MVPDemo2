package com.mvpdemo;

import android.app.Application;

import com.mvpdemo.data.database.DatabaseManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseManager.initDataManager(this);
    }
}
