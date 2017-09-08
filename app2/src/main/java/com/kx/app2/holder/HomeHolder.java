package com.kx.app2.holder;

import android.view.View;
import android.widget.TextView;

import com.kx.app2.R;
import com.kx.app2.base.BaseHolder;
import com.kx.app2.view.AppItem;

import butterknife.BindView;

/**
 * Created by KX on 2017/9/6.
 */

public class HomeHolder extends BaseHolder<AppItem> {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_downloadNum)
    TextView tvDownloadNum;

    public HomeHolder(View convertView) {
        super(convertView);
    }

    public void bindData(AppItem appItem) {
        tvName.setText(appItem.name);
        tvDownloadNum.setText("下载量:" + appItem.downloadNum);
    }
}