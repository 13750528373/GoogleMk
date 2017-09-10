package com.kx.googlemk.activity;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.kx.googlemk.fragment.BaseFragment;
import com.kx.googlemk.fragment.CommonView;

/**
 * Created by KX on 2017/9/5.
 */
public class OtherFragment extends BaseFragment {


    @Override
    protected int doInbackground() {
        return CommonView.STATE_ERROR;
    }

    @Override
    protected View onPostExecute() {
        TextView textView = new TextView(getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setText("OtherFragment---使用自定义组件后的效果");
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(20);
        return textView;
    }
}
