package com.jay.mapper;

import android.app.Application;

/**
 * Created by wanjian on 2017/3/8.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FloatToolsManager.init(this);
    }
}
