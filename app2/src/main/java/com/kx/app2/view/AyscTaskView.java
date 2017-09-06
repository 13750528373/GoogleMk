package com.kx.app2.view;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.kx.app2.R;
import com.kx.app2.utils.UiUtils;

/**
 * Created by KX on 2017/9/5.
 */

public abstract class AyscTaskView extends FrameLayout {

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


    public AyscTaskView(Context context) {
        this(context,null);
    }

    public AyscTaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        emptyView = View.inflate(getContext(), R.layout.pager_empty, null);
        addView(emptyView);
        emptyView.setVisibility(View.GONE);

        errorView = View.inflate(getContext(), R.layout.pager_error, null);
        addView(errorView);
        errorView.setVisibility(View.GONE);

        loadingView = View.inflate(getContext(), R.layout.pager_loading, null);
        addView(loadingView);
        loadingView.setVisibility(View.GONE);

        preExecute();

    }

    /**重写 第一阶段增加了扩展**/
    protected void preExecute() {
        //TODO
    }

    public void getDataFromServer() {
        currentState = STATE_LOADING;
        refreshUi();
        new Thread(new LoadDataTash()){}.start();
    }

    private void refreshUi() {
        loadingView.setVisibility(currentState == STATE_LOADING ? VISIBLE : GONE);
        errorView.setVisibility(currentState == STATE_ERROR ? VISIBLE : GONE);
        emptyView.setVisibility(currentState == STATE_EMPTY ? VISIBLE : GONE);
        if (currentState == STATE_SUCCESS) {
            if (successView == null) {
                successView = createSuccessView();
                addView(successView);
            }
            successView.setVisibility(VISIBLE);
        }

    }



    /**由baseFragment进来，抽象给子类处理，谁调用，谁具体实现，由子类执行连接服务器，获取数据的任务**/

    protected abstract View createSuccessView();

    private class LoadDataTash implements Runnable {

        @Override
        public void run() {
            SystemClock.sleep(1515);
            currentState = getServerData();
            safeRefreshUi();
        }
    }

    private void safeRefreshUi() {
        UiUtils.getHandler().post(new Runnable() {
            @Override
            public void run() {
                refreshUi();
            }
        });
    }

    protected abstract int getServerData();
}
