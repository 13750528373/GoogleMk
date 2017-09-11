package com.kx.app2.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kx.app2.R;
import com.kx.app2.adapter.SubjectAdapter;
import com.kx.app2.base.AyscTaskView;
import com.kx.app2.base.BaseFragment;
import com.kx.app2.bean.SubjectBean;
import com.kx.app2.protocol.SubjectProtocol;
import com.kx.app2.utils.UiUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by KX on 2017/9/11.
 */
public class SubjectFragment extends BaseFragment {


    private List<SubjectBean> mDatas = new ArrayList<>();


    @Override
    protected AyscTaskView.Result doInbackground() {
        try {
            SystemClock.sleep(2000);
            SubjectProtocol subjectProtocol = new SubjectProtocol();
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("index", "0");
            subjectProtocol.setParams(hashMap);
            mDatas = subjectProtocol.loadData();
            if (mDatas == null || mDatas.size() == 0) {
                return AyscTaskView.Result.EMPTE;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AyscTaskView.Result.ERROR;
        }
        return AyscTaskView.Result.SUCCESS;

    }

    @Override
    protected View onPostExecute() {
        ListView listView = new ListView(UiUtils.getContext());
        SubjectAdapter subjectAdapter = new SubjectAdapter(R.layout.item_subject, UiUtils.getContext(), mDatas);
        listView.setAdapter(subjectAdapter);
        return listView;

    }

}
