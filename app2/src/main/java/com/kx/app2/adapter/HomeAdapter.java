package com.kx.app2.adapter;

import android.content.Context;
import android.view.View;

import com.kx.app2.base.BaseHolder;
import com.kx.app2.base.BasicAdapter;
import com.kx.app2.bean.ApkItem;
import com.kx.app2.bean.HomeBean;
import com.kx.app2.holder.AppHolder;
import com.kx.app2.protocol.HomeProtocol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KX on 2017/9/6.
 */

public class HomeAdapter extends BasicAdapter<ApkItem> {

    List<ApkItem> mData;

    public HomeAdapter(int layoutId, Context context, List<ApkItem> data) {
        super(layoutId, context, data);
        this.mData = data;
    }


    @Override
    protected void onBindViewHolder(BaseHolder holder, ApkItem apkItem) {
        AppHolder appHolder = (AppHolder) holder;
        appHolder.bindData(apkItem);
    }




    @Override
    protected BaseHolder oncreateHolder(View v) {
        AppHolder appHolder = new AppHolder(v);
        return appHolder;
    }

    @Override
    protected List<ApkItem> getMoreData() throws Exception {
        HomeProtocol homeProtocol = new HomeProtocol();
        Map<String,String> m = new HashMap<>();
        m.put("index",mData.size()+"");
        homeProtocol.setParams(m);
        HomeBean homeBeen =  homeProtocol.loadData();

        return homeBeen.list;
    }


}
