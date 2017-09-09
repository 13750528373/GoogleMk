package com.kx.app2.adapter;

import android.content.Context;
import android.view.View;

import com.kx.app2.base.BaseHolder;
import com.kx.app2.base.BasicAdapter;
import com.kx.app2.bean.ApkItem;
import com.kx.app2.holder.AppHolder;
import com.kx.app2.protocol.GameProtocol2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KX on 2017/9/6.
 */

public class GameAdapter extends BasicAdapter<ApkItem> {


    private  List<ApkItem> mData;

    public GameAdapter(int layoutId, Context context, List<ApkItem> data) {
        super(layoutId, context, data);
        this.mData = data;
    }



    @Override
    protected void onBindViewHolder(BaseHolder holder, ApkItem appItem) {
        AppHolder appHolder = (AppHolder) holder;
        appHolder.bindData(appItem);
    }


    @Override
    protected BaseHolder oncreateHolder(View v) {

        return new AppHolder(v);
    }

    @Override
    protected List<ApkItem> getMoreData() throws Exception {
        GameProtocol2 gameProtocol2 = new GameProtocol2();
        Map<String,String> m = new HashMap<>();
        m.put("index",mData.size()+"");
        gameProtocol2.setParams(m);
//        return new GameProtocol().loadData(mData.size()+"");
        return gameProtocol2.loadData();

    }

    @Override
    protected boolean supportLoadMore() {
        return true;
    }
}
