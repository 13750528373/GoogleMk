package com.kx.app2.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.kx.app2.base.BaseFragment;
import com.kx.app2.utils.UiUtils;
import com.kx.app2.view.AyscTaskView;

/**
 * Created by KX on 2017/9/5.
 */
public class AppFragemt extends BaseFragment {
    @Override
    protected int doInbackground() {
        return AyscTaskView.STATE_EMPTY;
    }

    @Override
    protected View onPostExecute() {
        TextView textView = new TextView(UiUtils.getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setText("APP页面---使用自定义组件后的效果");
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(20);
        return textView;

    }
}
