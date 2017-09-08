package com.kx.app2.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.kx.app2.R;
import com.kx.app2.base.BaseHolder;
import com.kx.app2.bean.ApkItem;
import com.kx.app2.utils.UiUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

/**
 * Created by KX on 2017/9/6.
 */


public class AppHolder extends BaseHolder<ApkItem> {
    @BindView(R.id.item_appinfo_iv_icon)
    ImageView mItemAppinfoIvIcon;
    @BindView(R.id.item_appinfo_tv_title)
    TextView mItemAppinfoTvTitle;
    @BindView(R.id.item_appinfo_rb_stars)
    RatingBar mItemAppinfoRbStars;
    @BindView(R.id.item_appinfo_tv_size)
    TextView mItemAppinfoTvSize;
    @BindView(R.id.item_appinfo_tv_des)
    TextView mItemAppinfoTvDes;


    public AppHolder(View convertView) {
        super(convertView);
    }

    public void bindData(ApkItem appItem) {
        String picUrl = "http://10.0.2.2:8080/GooglePlayServer/image?name=" + appItem.getIconUrl();
        Picasso.with(UiUtils.getContext()).load(picUrl).into(mItemAppinfoIvIcon);
        mItemAppinfoRbStars.setRating(appItem.getStars());
        mItemAppinfoTvSize.setText(android.text.format.Formatter.formatFileSize(UiUtils.getContext(),appItem.getSize()));
        mItemAppinfoTvDes.setText(appItem.getDes());
    }
}