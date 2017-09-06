package com.kx.app2.utils;

import android.app.Application;

/**
 * Created by KX on 2017/9/5.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UiUtils.init(this);
    }
}
