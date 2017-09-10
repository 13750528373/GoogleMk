package com.kx.app2.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by KX on 2017/9/6.
 */

public abstract class BaseFragment extends Fragment {
    public AyscTaskView commonView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (commonView == null) {
            commonView = new AyscTaskView(getContext()) {

                @Override
                protected void preExecute() {
                    super.preExecute();
                }
                //当前是基类页面，只能让具体来执行,实例对象，设置Adapter
                @Override
                protected View createSuccessView() {
                    return onPostExecute();
                }
                @Override
                protected Result getServerData() {
                    return doInbackground();
                }

            };
        }
        return commonView;
    }

    //请求服务器后，刷新UI的方法，刷数据
    protected abstract AyscTaskView.Result doInbackground();

    //当前是基类页面，只能让具体来执行,实例对象，设置Adapter
    protected abstract View onPostExecute();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        commonView.getDataFromServer();
    }

    public void loadData(){
        commonView.getDataFromServer();
    }

}
