package com.kx.app2.adapter;

import android.content.Context;
import android.view.View;

import com.kx.app2.base.BaseHolder;
import com.kx.app2.base.BasicAdapter;
import com.kx.app2.bean.ApkItem;
import com.kx.app2.holder.AppHolder;

import java.util.List;

/**
 * Created by KX on 2017/9/6.
 */

public class AppAdapter extends BasicAdapter<ApkItem> {


    public AppAdapter(int layoutId, Context context, List<ApkItem> data) {
        super(layoutId, context, data);
    }



    @Override
    protected void onBindViewHolder(BaseHolder holder, ApkItem appItem) {
        AppHolder appHolder = (AppHolder) holder;
        appHolder.bindData(appItem);
    }


    @Override
    protected BaseHolder oncreateHolder(View v) {
        AppHolder appHolder = new AppHolder(v);
        return appHolder;
    }




}
