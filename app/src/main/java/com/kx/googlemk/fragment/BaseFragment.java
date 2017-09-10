package com.kx.googlemk.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by KX on 2017/9/4.
 */

public abstract class BaseFragment extends Fragment {

    private CommonView mCommonView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCommonView = new CommonView(getContext()) {
            @Override
            public int getServerData() {
                return doInbackground();
            }

            @Override
            protected View createSuccessView() {
                return onPostExecute();
            }
        };

        return mCommonView;
    }

    /** 请求服务器后，刷新UI的方法**/
    protected abstract View onPostExecute();

    /** 当前是基类页面，只能让具体来执行**/
    protected abstract int doInbackground();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCommonView.getDataFromServer();
    }
}
