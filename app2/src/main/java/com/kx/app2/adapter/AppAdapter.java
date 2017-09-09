package com.kx.app2.adapter;

import android.content.Context;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kx.app2.base.BaseHolder;
import com.kx.app2.base.BasicAdapter;
import com.kx.app2.bean.ApkItem;
import com.kx.app2.holder.AppHolder;
import com.kx.app2.utils.Constans;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by KX on 2017/9/6.
 */

public class AppAdapter extends BasicAdapter<ApkItem> {


    private final List<ApkItem> mData;

    public AppAdapter(int layoutId, Context context, List<ApkItem> data) {
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

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(Constans.HOST + "app?index=" + mData.size()).build();
        Response response = okHttpClient.newCall(request).execute();

        if(response.isSuccessful()){
            ResponseBody body = response.body();
            String jsonStr = body.string();
            Gson gson = new Gson();
            return gson.fromJson(jsonStr,new TypeToken<List<ApkItem>>(){}.getType());
        }


        return null;
    }

    @Override
    protected boolean supportLoadMore() {
        return true;
    }
}
