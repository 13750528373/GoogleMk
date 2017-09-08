package com.kx.app2.base;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by KX on 2017/9/6.
 */
public abstract class BaseHolder<T>{


    public BaseHolder(View convertView) {
        ButterKnife.bind(this,convertView);
    }


    public abstract void bindData(T t);
}
