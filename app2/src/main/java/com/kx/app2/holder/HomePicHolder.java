package com.kx.app2.holder;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kx.app2.R;
import com.kx.app2.base.BaseHolder;
import com.kx.app2.utils.Constans;
import com.kx.app2.utils.UiUtils;
import com.kx.app2.view.ViewPageCompat;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

/**
 * Created by KX on 2017/9/11.
 */
public class HomePicHolder extends BaseHolder<List<String>> {

    @BindView(R.id.item_home_picture_pager)
    ViewPageCompat mItemHomePicturePager;
    @BindView(R.id.item_home_picture_container_indicator)
    LinearLayout mItemHomePictureContainerIndicator;
    private List<String> mPics;
    private AutoScrollTask mAutoScrollTask;

    public HomePicHolder(View convertView) {
        super(convertView);
    }

    @Override
    public void bindData(List<String> pics) {

        this.mPics = pics;
        mItemHomePicturePager.setAdapter(new PicAdapter());

        //添加小圆点，打造指示器
        for (int i = 0; i < pics.size(); i++) {
            View point = new View(UiUtils.getContext());
            point.setBackgroundResource(R.drawable.indicator_normal);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(12, 12);
            layoutParams.leftMargin = 12;
            layoutParams.bottomMargin = 6;
            mItemHomePictureContainerIndicator.addView(point, layoutParams);
        }
        mItemHomePicturePager.addOnPageChangeListener(listener);

        int index = Integer.MAX_VALUE / 2;
        index -= index % mPics.size();
        mItemHomePicturePager.setCurrentItem(index);

        //开始自动滚动
        if(mAutoScrollTask == null){
            mAutoScrollTask = new AutoScrollTask();
        }
//        mAutoScrollTask.start();

        mItemHomePicturePager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //按下的时候，停止自动滚动
                //松开的时候，重新执行自动滚动
                return false;
            }
        });

        mItemHomePicturePager.setPageTransformer(true, new DepthPageTransformer());
    }

    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            position = position % mPics.size();
            for (int i = 0; i < mPics.size(); i++) {
                View point = mItemHomePictureContainerIndicator.getChildAt(i);
                if (position == i) {
                    point.setBackgroundResource(R.drawable.indicator_selected);
                } else {
                    point.setBackgroundResource(R.drawable.indicator_normal);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    private class PicAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            if (mPics != null) {
                return Integer.MAX_VALUE;
            }
            return 0;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position % mPics.size();
            ImageView imageView = new ImageView(UiUtils.getContext());
            String url = Constans.IMAGE + mPics.get(position);
            Log.e("url", url);
            Picasso.with(UiUtils.getContext()).load(url).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    private class AutoScrollTask implements Runnable {

        public void start(){
            UiUtils.postDelayTask(this, 2000);
        }

        public void stop(){
            UiUtils.removeTask(this);
        }

        @Override
        public void run() {
            int currentItem = mItemHomePicturePager.getCurrentItem();
            currentItem++;
            mItemHomePicturePager.setCurrentItem(currentItem);
            UiUtils.postDelayTask(this, 2000);
        }
    }

    public class DepthPageTransformer
            implements ViewPager.PageTransformer
    {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }







}
