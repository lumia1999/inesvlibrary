package com.inesv.library.application;

import android.app.Application;

import com.inesv.library.util.LogUtil;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.isShowLog(true);
    }
}
