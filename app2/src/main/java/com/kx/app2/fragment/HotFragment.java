package com.kx.app2.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kx.app2.base.AyscTaskView;
import com.kx.app2.base.BaseFragment;
import com.kx.app2.protocol.HotProtocol;
import com.kx.app2.utils.UiUtils;
import com.kx.app2.view.FlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by KX on 2017/9/11.
 */
public class HotFragment extends BaseFragment {

    private List<String> mDatas = new ArrayList<>();
    private Random random = new Random();

    @Override
    protected AyscTaskView.Result doInbackground() {
        //耗时操作
        try {
            SystemClock.sleep(2000);
            HotProtocol hotProtocol = new HotProtocol();
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("index", "0");
            hotProtocol.setParams(hashMap);
            mDatas = hotProtocol.loadData();
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
        FlowLayout flowLayout = new FlowLayout(UiUtils.getContext());
        flowLayout.setPadding(10, 10, 10, 10);
        for (int i = 0; i < mDatas.size(); i++) {
            final TextView textView = new TextView(UiUtils.getContext());
            textView.setText(mDatas.get(i));
            textView.setPadding(5, 5, 5, 5);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(20);
            //随机背景颜色
            int a = 255;
            int r = 150 + random.nextInt(100);
            int g = 150 + random.nextInt(100);
            int b = 150 + random.nextInt(100);
//            textView.setTextColor(Color.argb(a, r, g, b));
//            textView.setBackgroundResource(R.drawable.hot_item);
            //用代码写drawable
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius(10);
            gradientDrawable.setColor(Color.argb(a, r, g, b));


            GradientDrawable gradientDrawable2 = new GradientDrawable();
            gradientDrawable2.setCornerRadius(10);
            gradientDrawable2.setColor(Color.GRAY);


            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, gradientDrawable2);  //按下的，灰色
            stateListDrawable.addState(new int[]{}, gradientDrawable);  //普通的，彩色
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UiUtils.getContext(), textView.getText(), Toast.LENGTH_SHORT).show();
                }
            });

            textView.setBackgroundDrawable(stateListDrawable);
            flowLayout.addView(textView);
        }
        return flowLayout;
    }
}
