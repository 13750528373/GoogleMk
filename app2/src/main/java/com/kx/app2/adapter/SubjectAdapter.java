package com.kx.app2.adapter;

import android.content.Context;
import android.view.View;

import com.kx.app2.base.BaseHolder;
import com.kx.app2.base.BasicAdapter;
import com.kx.app2.bean.SubjectBean;
import com.kx.app2.holder.SubjectHolder;

import java.util.List;

/**
 * Created by KX on 2017/9/11.
 */
public class SubjectAdapter extends BasicAdapter<SubjectBean>{



    public SubjectAdapter(int layoutId, Context context, List<SubjectBean> data) {
        super(layoutId, context, data);
    }

    @Override
    protected void onBindViewHolder(BaseHolder holder, SubjectBean subjectBean) {
        SubjectHolder subjectHolder = (SubjectHolder) holder;
        subjectHolder.bindData(subjectBean);

    }

    @Override
    protected BaseHolder oncreateHolder(View v) {
        return new SubjectHolder(v);
    }

    @Override
    protected List<SubjectBean> getMoreData() throws Exception {
        return null;
    }


}
