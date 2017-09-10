package com.kx.googlemk.utils;

import android.content.Context;
import android.os.Handler;

import com.kx.googlemk.activity.MyApplication;

/**
 * Created by KX on 2017/9/4.
 */

public class UIUtils {

    private static Context sContext;
    private static Handler sHandle;

    public static void init(MyApplication myApplication) {
        sContext = myApplication;
        sHandle = new Handler();
    }

    public static Context getContext() {
        return sContext;
    }

    public static Handler getHandle() {
        return sHandle;
    }

    public void postTask(Runnable task){
        sHandle.post(task);
    }

    public void postDelayTask(Runnable task,long time){
        sHandle.postDelayed(task,time);
    }

    public  void removeTash(Runnable task){
        sHandle.removeCallbacks(task);
    }

    public static  String[] getStringArray(int resId){
        return sContext.getResources().getStringArray(resId);
    }




}
