package com.kx.app2.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kx.app2.view.AyscTaskView;

/**
 * Created by KX on 2017/9/6.
 */

public abstract class BaseFragment extends Fragment {
    private AyscTaskView commonView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        commonView = new AyscTaskView(getContext()) {

            @Override
            protected void preExecute() {
                super.preExecute();

            }

            @Override
            protected int getServerData() {
                return doInbackground();
            }

            @Override
            protected View createSuccessView() {
                return onPostExecute();
            }

            


        };
        return commonView;
    }

    //请求服务器后，刷新UI的方法
    protected abstract int doInbackground();

    //当前是基类页面，只能让具体来执行
    protected abstract View onPostExecute() ;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        commonView.getDataFromServer();
    }
}
