package com.kx.app2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kx.app2.R;
import com.kx.app2.adapter.GameAdapter;
import com.kx.app2.base.AyscTaskView;
import com.kx.app2.base.BaseFragment;
import com.kx.app2.bean.ApkItem;
import com.kx.app2.protocol.GameProtocol2;
import com.kx.app2.utils.UiUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by KX on 2017/9/5.
 */
public class GameFragemt extends BaseFragment {
    List<ApkItem> mData = new ArrayList<>();


    @Override
    protected AyscTaskView.Result doInbackground() {

        try {
            GameProtocol2 gameProtocol2 = new GameProtocol2();
            Map<String,String> m = new HashMap<>();
            m.put("index","0");
            gameProtocol2.setParams(m);

            mData = gameProtocol2.loadData();
            if (mData == null || mData.size() == 0) {
                return AyscTaskView.Result.EMPTE;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return AyscTaskView.Result.SUCCESS;
    }

    @Override
    protected View onPostExecute() {

        ListView listView = new ListView(UiUtils.getContext());
        GameAdapter gameAdapter = new GameAdapter(R.layout.item_app_info, UiUtils.getContext(), mData);
        listView.setAdapter(gameAdapter);
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
