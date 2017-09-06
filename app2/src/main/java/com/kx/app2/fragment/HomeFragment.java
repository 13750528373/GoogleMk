package com.kx.app2.fragment;

import android.view.View;

import com.kx.app2.base.BaseFragment;
import com.kx.app2.view.AyscTaskView;

/**
 * Created by KX on 2017/9/5.
 */
public class HomeFragment extends BaseFragment {


    @Override
    protected int doInbackground() {
        return AyscTaskView.STATE_LOADING;
    }

    @Override
    protected View onPostExecute() {
        return null;
    }
}
