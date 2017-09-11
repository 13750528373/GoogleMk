package com.kx.app2.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KX on 2017/9/11.
 */

public class FlowLayout extends ViewGroup {

    private Line currentLine;
    private List<Line> lines = new ArrayList<>();
    public int mVerticalSpace = 10;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //13.抹掉之前测量的痕迹
        lines.clear();
        currentLine = null;

        //onMeasure会执行2到5次
        Log.e("flowlayout", "onMeasure");
        //6.获取宽高信息
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int maxWidth = width - getPaddingLeft() - getPaddingRight();
        //7.测量孩子
        int childCount = this.getChildCount();
        for(int i=0;i<childCount;i++){
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            //8.把孩子添加到line里
            if(currentLine == null){
                //第一行
                currentLine = new Line(maxWidth);
                lines.add(currentLine);
                //创建line时是第一个孩子
                currentLine.addChild(child);
            }else{
                if(currentLine.canAddChild(child)){
                    //剩余空间足够
                    currentLine.addChild(child);
                }else{
                    currentLine = new Line(maxWidth);
                    lines.add(currentLine);
                    //创建line时是第一个孩子
                    currentLine.addChild(child);
                }
            }
        }

        //9.决定自己宽高,高度需要知道有多少行
        int height = getPaddingTop();
        for(Line line : lines){
            height += line.mHeight;
            height += mVerticalSpace;
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e("flowlayout", "onLayout");
        //10.逐行布局，发挥line的作用
        l = getPaddingLeft();
        t = getPaddingTop();
        for(Line line : lines){
            line.layout(l,t);
            t  += line.mHeight;
            t  += mVerticalSpace;
        }
    }

    public class Line {
        private int maxWidth;
        private int usedWidth;
        private int mSpace = 10; //不同孩子之间要有空隙
        private int mHeight;

        //1.定义line，line需要保存孩子，因此定义集合
        public List<View> mViews = new ArrayList<>();

        public Line(int maxWidth) {
            this.maxWidth = maxWidth;
        }

        //2.添加孩子的方法
        public void addChild(View child){
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            //5.添加后需要更新信息
            usedWidth += childWidth;
            //第一个孩子不需要间距space
            if(mViews.size() > 0) {
                usedWidth += mSpace;
            }

            mHeight = mHeight > childHeight? mHeight : childHeight;

            mViews.add(child);
        }

        //3.是否能添加成功
        public boolean canAddChild(View child){
            if(mViews.size() == 0){
                return true;
            }
            //4.需要知道剩余空间（最大空间 减去 已用空间）
            int childWidth = child.getMeasuredWidth();
            if(childWidth > (maxWidth - usedWidth - mSpace)){
                return false;
            }
            return true;
        }

        public void layout(int l, int t) {
            //14.将剩余无法填充的空间平分给当前所有的孩子
            int avg = (maxWidth - usedWidth) / mViews.size();


            //right和bottom不需要传进来，可以计算得到
            for(int i=0;i<mViews.size();i++){
                View child = mViews.get(i);
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();

                //15.让孩子重新测量，要更新宽度+avg
                int childWidthSpec = MeasureSpec.makeMeasureSpec(childWidth + avg, MeasureSpec.EXACTLY);
                int childHeightSpec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
                child.measure(childWidthSpec,childHeightSpec);
                childWidth = child.getMeasuredWidth();

                int left = l;
                int top = t;
                int right = left + childWidth;
                int bottom = t + childHeight;
                child.layout(left,top,right,bottom);
                //11.累加left值
                l += childWidth;
                l += mSpace;
            }
        }
    }
}