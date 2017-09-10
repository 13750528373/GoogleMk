package com.kx.googlemk.fragment;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.kx.googlemk.R;
import com.kx.googlemk.utils.UIUtils;

/**
 * Created by KX on 2017/9/5.
 */
public abstract class CommonView extends FrameLayout {

    public static final int STATE_NONE = 0; //第一阶段
    public static final int STATE_LOADING = 1;  //第二阶段
    public static final int STATE_ERROR = 2;    //2.5阶段
    public static final int STATE_EMPTY = 3;    //第三阶段
    public static final int STATE_SUCCESS = 4;//第三阶段

    private int currentState = STATE_NONE;

    private View emptyView;
    private View errorView;
    private View loadingView;
    private View successView;

    public CommonView(Context context) {
        this(context, null);
    }

    public CommonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        emptyView = View.inflate(getContext(), R.layout.pager_empty, null);
        errorView = View.inflate(getContext(), R.layout.pager_error, null);
        loadingView = View.inflate(getContext(), R.layout.pager_loading, null);

        addView(emptyView);
        addView(errorView);
        addView(loadingView);

        emptyView.setVisibility(GONE);
        errorView.setVisibility(GONE);
        loadingView.setVisibility(GONE);

    }


    public void getDataFromServer() {
        currentState = STATE_LOADING;
        refreshUI();//1.状态改变后刷新界面，展示loadingView
        new Thread(new LoadDataTask()).start();   //2.开启子线程，连接服务器
    }


    private void refreshUI() {
        emptyView.setVisibility(currentState == STATE_EMPTY ? VISIBLE : GONE);
        errorView.setVisibility(currentState == STATE_ERROR ? VISIBLE : GONE);
        loadingView.setVisibility(currentState == STATE_LOADING ? VISIBLE : GONE);
        if(currentState == STATE_SUCCESS){
            if(successView == null){
                successView = createSuccessView();
                addView(successView);
            }
            successView.setVisibility(VISIBLE);
        }
    }



    private class LoadDataTask implements Runnable {
        @Override
        public void run() {
            SystemClock.sleep(1515);
            currentState = getServerData();
            saseRefreshUI();
        }
    }

    private void saseRefreshUI() {
        UIUtils.getHandle().post(new Runnable() {
            @Override
            public void run() {
                refreshUI();
            }
        });
    }


    /**由子类执行连接服务器，获取数据的任务**/
    public abstract int getServerData();

    /**由子类决定，加载成功后的UI效果**/
    protected abstract View createSuccessView();
}
