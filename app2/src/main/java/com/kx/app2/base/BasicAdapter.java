package com.kx.app2.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.kx.app2.R;
import com.kx.app2.holder.LoadMoreHolder;
import com.kx.app2.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KX on 2017/9/6.
 */

public abstract class BasicAdapter<T> extends BaseAdapter {

    public static final int TYPE_LOAD_MORE = 0;
    public static final int TYPE_NORMAL = 1;
    private int PAGESIZE = 20;

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getCount() - 1) {
            return TYPE_LOAD_MORE;
        } else {
            return TYPE_NORMAL;
        }
    }

    private int layoutId;
    private Context mContext;
    private List<T> mData = new ArrayList<>();

    public BasicAdapter(int layoutId, Context context, List<T> data) {
        this.layoutId = layoutId;
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        if (mData != null) {
            return mData.size() + 1;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mData != null) {
            return mData.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder = null;
        if (convertView == null) {
            if (position == getCount() - 1) {
                convertView = View.inflate(mContext, R.layout.item_load_more, null);
                holder = new LoadMoreHolder(convertView);
            } else {
                convertView = View.inflate(mContext, layoutId, null);
                holder = oncreateHolder(convertView);
            }
            convertView.setTag(holder);
        } else {
            holder = (BaseHolder) convertView.getTag();
        }

        if (position == getCount() - 1) {
            if (supportLoadMore()) {
                loadMore(holder);
            }else {
                holder.bindData(LoadMoreHolder.STATE_NONE);
            }
        } else {
            T t = mData.get(position);
            onBindViewHolder(holder, t);
        }


        return convertView;
    }

    protected boolean supportLoadMore() {
        return  false;
    }

    boolean isLoadingMore = false;

    private void loadMore(BaseHolder holder) {
        holder.bindData(LoadMoreHolder.STATE_LOAD_MORE);
//        new Thread(new LoadMoreTask(holder)).start();
       ThreadManager.getNormalPool().execute(new LoadMoreTask(holder));
    }


    /**
     * 数据t
     **/
    protected abstract void onBindViewHolder(BaseHolder holder, T t);


    protected abstract BaseHolder oncreateHolder(View v);


    private class LoadMoreTask implements Runnable {

        private BaseHolder holder;

        public LoadMoreTask(BaseHolder holder) {
            this.holder = holder;
        }

        @Override
        public void run() {
            if(isLoadingMore){
                return;
            }
            isLoadingMore = true;


            List<T> moreData = null;
            int state = LoadMoreHolder.STATE_LOAD_MORE;
            try {
                moreData = getMoreData();
                if (moreData != null) {
                    mData.addAll(moreData);
                    if (moreData.size() < PAGESIZE) {
                        state = LoadMoreHolder.STATE_NONE;
                    }
                } else {
                    state = LoadMoreHolder.STATE_NONE;
                }


            } catch (Exception e) {
                e.printStackTrace();
                state = LoadMoreHolder.STATE_RETRY;
            }

            mData.addAll(moreData); //添加数据

            final int finalState = state;

            UiUtils.postTask(new Runnable() {
                @Override
                public void run() {
                    holder.bindData(finalState); //LoadMoreHolder
                    if (finalState != LoadMoreHolder.STATE_RETRY) {
                        notifyDataSetChanged();//刷新UI
                    }

                    isLoadingMore =false;
                }
            });


        }
    }

    /**
     * 使用抽象方法，由具体adapter加载更多数据
     **/
    protected abstract List<T> getMoreData() throws Exception;
}

