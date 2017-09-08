package com.kx.app2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kx.app2.R;
import com.kx.app2.adapter.AppAdapter;
import com.kx.app2.base.BaseFragment;
import com.kx.app2.bean.ApkItem;
import com.kx.app2.utils.UiUtils;
import com.kx.app2.base.AyscTaskView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by KX on 2017/9/5.
 */
public class AppFragemt extends BaseFragment {
    ArrayList<ApkItem> mData = new ArrayList<>();



    @Override
    protected AyscTaskView.Result doInbackground() {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://10.0.2.2:8080/GooglePlayServer/app?index=0").build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                String jsonStr = body.string();
                Gson gson = new Gson();
                mData = gson.fromJson(jsonStr, new TypeToken<List<ApkItem>>() {
                }.getType());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return AyscTaskView.Result.SUCCESS;
    }

    @Override
    protected View onPostExecute() {

        ListView listView = new ListView(UiUtils.getContext());
        AppAdapter homeAdapter = new AppAdapter(R.layout.item_app_info, UiUtils.getContext(), mData);
        listView.setAdapter(homeAdapter);
        return listView;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


}
