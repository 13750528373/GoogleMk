package com.kx.app2.utils;

import android.app.Application;

/**
 * Created by lidongzhi on 2017/9/5.
 */

public class GpApplication extends Application {

    //应用程序的入口,初始化操作
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化推送、地图
//        MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(this,"asdfadfasdf","dfasdfadfasf"));
        UiUtil.init(this);
    }
}
