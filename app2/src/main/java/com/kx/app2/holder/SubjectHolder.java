package com.kx.app2.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kx.app2.R;
import com.kx.app2.base.BaseHolder;
import com.kx.app2.bean.SubjectBean;
import com.kx.app2.utils.Constans;
import com.kx.app2.utils.UiUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

/**
 * Created by KX on 2017/9/11.
 */
public class SubjectHolder extends BaseHolder<SubjectBean>{

    @BindView(R.id.item_subject_iv_icon)
    ImageView mItemSubjectIvIcon;
    @BindView(R.id.item_subject_tv_title)
    TextView mItemSubjectTvTitle;

    public SubjectHolder(View convertView) {
        super(convertView);
    }

    @Override
    public void bindData(SubjectBean subjectBean) {
        Picasso.with(UiUtils.getContext()).load(Constans.IMAGE + subjectBean.url).into(mItemSubjectIvIcon);
        mItemSubjectTvTitle.setText(subjectBean.des);
    }
}
