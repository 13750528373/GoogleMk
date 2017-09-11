package com.kx.app2.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.kx.app2.R;


/**
 * 保证它的宽高可以适配所有手机
 */
public class RatioLayout extends FrameLayout {

    private float mRatio;
    private int mode;
    public static final int MODE_WIDTH = 0;
    public static final int MODE_HEIGHT = 1;

    public RatioLayout(Context context) {
        this(context, null);
    }

    public RatioLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.RatioLayout);

        mRatio = typedArray.getFloat(R.styleable.RatioLayout_mRatio, 2.42f);
        mode = typedArray.getInteger(R.styleable.RatioLayout_relative_mode, MODE_WIDTH); //默认是固定宽度，动态高度
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //TODO:计算正确的高度，保证宽高比为2.42，除以2.42
        //宽度已经是屏幕宽度了，如何取出？
        //Mode:EXACTLY(matchparent, 60dp)   AT_MOST(wrapcontent)   UNSPECIFIED
        int totalWidth = MeasureSpec.getSize(widthMeasureSpec);

        int totalHeight = MeasureSpec.getSize(heightMeasureSpec);  //90dp

        if (mode == MODE_WIDTH) {
            //当前是matchparent
            int height = (int) (totalWidth / 2.42);
            //step2.测量孩子
            int childWidth = totalWidth - getPaddingLeft() - getPaddingRight();
            int childHeight = height - getPaddingBottom() - getPaddingTop();
            int childWidthspec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
            int childHeightspec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
            measureChildren(childWidthspec, childHeightspec);

            //step3.最终确定宽高
            setMeasuredDimension(totalWidth, height);
        } else if (mode == MODE_HEIGHT) {
            //此模式width是计算
            int width = (int) (totalHeight * 2.42);
            int childWidth = width - getPaddingLeft() - getPaddingRight();
            int childHeight = totalHeight - getPaddingBottom() - getPaddingTop();
            int childWidthspec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
            int childHeightspec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
            measureChildren(childWidthspec, childHeightspec);

            //step3.最终确定宽高
            setMeasuredDimension(width, totalHeight);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

}
