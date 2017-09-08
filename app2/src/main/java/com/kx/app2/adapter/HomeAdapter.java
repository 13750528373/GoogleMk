package com.kx.app2.adapter;

import android.content.Context;
import android.view.View;

import com.kx.app2.base.BaseHolder;
import com.kx.app2.base.BasicAdapter;
import com.kx.app2.holder.HomeHolder;
import com.kx.app2.view.AppItem;

import java.util.List;

/**
 * Created by KX on 2017/9/6.
 */

public class HomeAdapter extends BasicAdapter<AppItem> {


    public HomeAdapter(int layoutId, Context context, List<AppItem> data) {
        super(layoutId, context, data);
    }



    @Override
    protected void onBindViewHolder(BaseHolder holder, AppItem appItem) {
        HomeHolder homeHolder = (HomeHolder) holder;
        homeHolder.bindData(appItem);
    }


    @Override
    protected BaseHolder oncreateHolder(View v) {
        HomeHolder homeHolder = new HomeHolder(v);
        return homeHolder;
    }




}
