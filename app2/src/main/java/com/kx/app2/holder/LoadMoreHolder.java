package com.kx.app2.holder;

import android.view.View;
import android.widget.LinearLayout;

import com.kx.app2.R;
import com.kx.app2.base.BaseHolder;

import butterknife.BindView;

/**
 * Created by KX on 2017/9/8.
 * 加载更多对应的holder
 */

public class LoadMoreHolder extends BaseHolder<Integer> {


    @BindView(R.id.item_loadmore_container_loading)
    LinearLayout mItemLoadmoreContainerLoading;
    @BindView(R.id.item_loadmore_container_retry)
    LinearLayout mItemLoadmoreContainerRetry;

    public static final int STATE_NONE = 0; //没有更多数据了
    public static final int STATE_LOAD_MORE = 1; //可以继续加载更多数据
    public static final int STATE_RETRY = 2; //加载失败，重试

    public LoadMoreHolder(View convertView) {
        super(convertView);
    }

    /**LoadMoreHolder  bindData**/
    @Override
    public void bindData(Integer integer) {
        switch (integer) {
            case STATE_NONE:
                mItemLoadmoreContainerLoading.setVisibility(View.GONE);
                mItemLoadmoreContainerRetry.setVisibility(View.GONE);
                break;
            case STATE_LOAD_MORE:
                mItemLoadmoreContainerLoading.setVisibility(View.VISIBLE);
                mItemLoadmoreContainerRetry.setVisibility(View.GONE);
                break;
            case STATE_RETRY:
                mItemLoadmoreContainerLoading.setVisibility(View.GONE);
                mItemLoadmoreContainerRetry.setVisibility(View.VISIBLE);
                break;
        }
    }


}
