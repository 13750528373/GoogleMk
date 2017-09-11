package com.kx.app2.fragment;

import android.graphics.Color;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.kx.app2.base.AyscTaskView;
import com.kx.app2.base.BaseFragment;
import com.kx.app2.protocol.RecommendProtocol;
import com.kx.app2.utils.StellarMap;
import com.kx.app2.utils.UiUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by KX on 2017/9/11.
 */
public class RecommendFragment extends BaseFragment{
    private List<String> mDatas = new ArrayList<>();
    @Override
    protected AyscTaskView.Result doInbackground() {
        //耗时操作
        try {
            SystemClock.sleep(2000);
            RecommendProtocol recommendProtocol = new RecommendProtocol();
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("index", "0");
            recommendProtocol.setParams(hashMap);
            mDatas = recommendProtocol.loadData();
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
        StellarMap stellarMap = new StellarMap(UiUtils.getContext());
        stellarMap.setRegularity(10, 10);
        stellarMap.setInnerPadding(10, 10, 10, 10);
        stellarMap.setAdapter(new RecommendAdapter());
        //设置adapter以后有数据了，才能选中页面
        stellarMap.setGroup(0, true);
        return stellarMap;
    }
    private class RecommendAdapter implements StellarMap.Adapter {
        int PAGE_SIZE = 15;
        Random random = new Random();

        //一共几页
        @Override
        public int getGroupCount() {
            if (mDatas.size() % PAGE_SIZE == 0) {
                return mDatas.size() / PAGE_SIZE;
            } else {
                return mDatas.size() / PAGE_SIZE + 1;
            }
        }

        //第groupIndex页中的孩子个数
        @Override
        public int getCount(int groupIndex) {
            if (mDatas.size() % PAGE_SIZE == 0) {
                return PAGE_SIZE;
            } else {
                if (groupIndex == getGroupCount() - 1) {
                    //最后一页
                    return mDatas.size() % PAGE_SIZE;
                } else {
                    return PAGE_SIZE;
                }
            }
        }

        //第groupIndex页中第position个
        @Override
        public View getView(int groupIndex, int position, View convertView) {
            //数据？？？
            int realPosition = groupIndex * PAGE_SIZE + position;
            String text = mDatas.get(realPosition);
            TextView textView = new TextView(UiUtils.getContext());
            textView.setText(text);
            //TODO:文本的颜色，大小
            textView.setTextSize(15 + random.nextInt(10));
            int a = 255;
            int r = 50 + random.nextInt(100);
            int g = 100 + random.nextInt(100);
            int b = 150 + random.nextInt(100);
            textView.setTextColor(Color.argb(a, r, g, b));
            return textView;
        }

        //使用pan动画切换到下一页
        @Override
        public int getNextGroupOnPan(int groupIndex, float degree) {
            groupIndex++;
            if (groupIndex == 3) {
                groupIndex = 0;
            }
            return groupIndex;
        }

        //使用zoom动画切换到下一页
        @Override
        public int getNextGroupOnZoom(int groupIndex, boolean isZoomIn) {
            groupIndex++;
            if (groupIndex == 3) {
                groupIndex = 0;
            }
            return groupIndex;
        }
    }
}
