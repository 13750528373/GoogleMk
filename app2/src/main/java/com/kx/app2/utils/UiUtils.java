package com.kx.app2.utils;

import android.content.Context;
import android.os.Handler;

/**
 * Created by KX on 2017/9/5.
 */
public class UiUtils {

    private static Context sContext = null;
    private static Handler sHandler = null;

    public static void init(MyApplication myApplication) {
        sContext = myApplication;
        sHandler = new Handler();
    }


    public static Context getContext() {
        return sContext;
    }

    public static Handler getHandler() {
        return sHandler;
    }


    public static void postTask(Runnable task) {
        sHandler.post(task);
    }

    public static void postDelayTask(Runnable task, long time) {
        sHandler.postDelayed(task, time);
    }

    public static void removeTask(Runnable task) {
        sHandler.removeCallbacks(task);
    }

    public static String[] getStringArray(int resId) {
        return sContext.getResources().getStringArray(resId);
    }


}
