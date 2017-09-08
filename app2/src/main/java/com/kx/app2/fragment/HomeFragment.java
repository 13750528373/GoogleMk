package com.kx.app2.fragment;

import android.view.View;
import android.widget.ListView;

import com.kx.app2.R;
import com.kx.app2.base.BaseFragment;
import com.kx.app2.utils.UiUtils;
import com.kx.app2.view.AppItem;
import com.kx.app2.base.AyscTaskView;
import com.kx.app2.adapter.HomeAdapter;

import java.util.ArrayList;

/**
 * Created by KX on 2017/9/5.
 */
public class HomeFragment extends BaseFragment {

    ArrayList<AppItem> mData = new ArrayList<>();




    @Override
    protected AyscTaskView.Result doInbackground() {
        for (int i = 0; i < 100; i++) {
            AppItem appItem = new AppItem();
            appItem.name = ""+i;
            appItem.downloadNum = i;
            mData.add(appItem);
        }

        return AyscTaskView.Result.SUCCESS;
    }

    @Override
    protected View onPostExecute() {

        ListView listView = new ListView(UiUtils.getContext());
        HomeAdapter homeAdapter = new HomeAdapter(R.layout.item_home, UiUtils.getContext(),mData);
        listView.setAdapter(homeAdapter);
        return listView;
    }
}
