package com.kx.app2.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KX on 2017/9/6.
 */

public abstract class BasicAdapter<T> extends BaseAdapter {

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
            return mData.size();
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
        if (convertView  ==null){
            convertView = View.inflate(mContext,layoutId,null);

            holder = oncreateHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (BaseHolder) convertView.getTag();
        }
        T t = mData.get(position);
        onBindViewHolder(holder,t);
        
        
        return convertView;
    }

    /**数据t **/
    protected abstract void onBindViewHolder(BaseHolder holder, T t);


    protected abstract BaseHolder oncreateHolder(View v);





}

