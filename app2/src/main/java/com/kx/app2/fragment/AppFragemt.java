package com.kx.app2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kx.app2.R;
import com.kx.app2.adapter.AppAdapter;
import com.kx.app2.base.AyscTaskView;
import com.kx.app2.base.BaseFragment;
import com.kx.app2.bean.ApkItem;
import com.kx.app2.protocol.AppProtocol;
import com.kx.app2.utils.UiUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by KX on 2017/9/5.
 */
public class AppFragemt extends BaseFragment {
    List<ApkItem> mData = new ArrayList<>();



    @Override
    protected AyscTaskView.Result doInbackground() {


        try {
            AppProtocol appProtocol = new AppProtocol();
            Map<String,String> params = new HashMap<>();
            params.put("index","0");
            appProtocol.setParams(params);
            mData = appProtocol.loadData();
            if(mData.size() == 0){
                return AyscTaskView.Result.EMPTE;
            }

        }
         catch (Exception e) {
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
