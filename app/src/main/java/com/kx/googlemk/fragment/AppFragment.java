package com.kx.googlemk.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by KX on 2017/9/5.
 */

public class AppFragment extends BaseFragment{


    @Override
    protected int doInbackground() {
        return CommonView.STATE_EMPTY;
    }

    @Override
    protected View onPostExecute() {
        TextView textView = new TextView(getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setText("APP页面---使用自定义组件后的效果");
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(20);
        return textView;
    }

}
