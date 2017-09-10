package com.kx.app2.fragment;

import android.view.View;
import android.widget.ListView;

import com.kx.app2.R;
import com.kx.app2.adapter.HomeAdapter;
import com.kx.app2.base.AyscTaskView;
import com.kx.app2.base.BaseFragment;
import com.kx.app2.bean.ApkItem;
import com.kx.app2.bean.HomeBean;
import com.kx.app2.holder.HomePicHolder;
import com.kx.app2.protocol.HomeProtocol;
import com.kx.app2.utils.UiUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by KX on 2017/9/5.
 */
public class HomeFragment extends BaseFragment {


    List<ApkItem> mData = new ArrayList<>();

    @Override
    protected AyscTaskView.Result doInbackground() {


        HomeProtocol homeProtocol = new HomeProtocol();
        HashMap<String, String> p = new HashMap<>();
        p.put("index", String.valueOf(0));
        homeProtocol.setParams(p);
        try {
            HomeBean homeBeen = homeProtocol.loadData();
            //数据
            mData = homeBeen.list;
            mPics = homeBeen.picture;

            if (mData.size() == 0) {
                return AyscTaskView.Result.EMPTE;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return AyscTaskView.Result.ERROR;
        }

        return AyscTaskView.Result.SUCCESS;
    }

    private List<String> mPics;

    @Override
    protected View onPostExecute() {

        ListView listView = new ListView(UiUtils.getContext());
        HomeAdapter homeAdapter = new HomeAdapter(R.layout.item_app_info, UiUtils.getContext(), mData);
        listView.setAdapter(homeAdapter);

        View picView = View.inflate(UiUtils.getContext(), R.layout.item_home_picture, null);
        HomePicHolder homePicHolder = new HomePicHolder(picView);
        homePicHolder.bindData(mPics);
        listView.addHeaderView(picView);


        return listView;
    }




}
