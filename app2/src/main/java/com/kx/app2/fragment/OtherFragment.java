package com.kx.app2.fragment;

import android.os.SystemClock;
import android.view.View;

import com.kx.app2.base.BaseFragment;
import com.kx.app2.base.AyscTaskView;

/**
 * Created by KX on 2017/9/5.
 */
public class OtherFragment extends BaseFragment{
    @Override
    protected AyscTaskView.Result doInbackground() {
        SystemClock.sleep(1515);
        return AyscTaskView.Result.ERROR;
    }

    @Override
    protected View onPostExecute() {
        return null;
    }
}
