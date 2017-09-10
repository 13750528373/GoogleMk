package com.kx.googlemk.activity;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.kx.googlemk.utils.UIUtils;

/**
 * Created by KX on 2017/9/4.
 */

public class MyApplication extends Application {


    private static Context mContext;
    private static Handler mHandler;
    private static Thread mMainThread;
    private static int mMainThreadId;


    @Override
    public void onCreate() {
        super.onCreate();

        UIUtils.init(this);


        mContext = this;
        mHandler = new Handler();
        mMainThread = Thread.currentThread();
        mMainThreadId = Process.myTid();


    }

    public static Context getContext() {
        return mContext;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**确保任务一定是在UI线程运行**/
    public static  void runInMainThread(Runnable runnable){
        if(Process.myTid() == getMainThreadId()){ //获取调用此方法所在的线程
            runnable.run();  //runnable就是在主线程中要去执行的任务,则直接运行即可
        }else{
            getHandler().post(runnable);//如果上诉的runnable运行在子线程中,将其传递到主线程中去做执行
        }
    }




}
