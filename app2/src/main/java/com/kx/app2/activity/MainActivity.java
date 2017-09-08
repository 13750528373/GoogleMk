package com.kx.app2.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.kx.app2.R;
import com.kx.app2.base.BaseFragment;
import com.kx.app2.manager.FragmentFactory;
import com.kx.app2.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawerlayout;

    private String[] mTitle = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolBar();
        initData();


    }

    private void initData() {
        mTitle = UiUtils.getStringArray(R.array.pagers);
        mVp.setAdapter(new kAdapter(getSupportFragmentManager()));
        mVp.addOnPageChangeListener(onPagerChangeListener1);
        mTablayout.setupWithViewPager(mVp);
    }

    ViewPager.OnPageChangeListener onPagerChangeListener1 = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            BaseFragment baseFragment = FragmentFactory.getInstance().createrFragment(position);
            baseFragment.loadData();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private boolean isFirshEnter = true;

    //StatePagerAdapter
    public class kAdapter extends FragmentStatePagerAdapter{

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
            if(isFirshEnter){
                onPagerChangeListener1.onPageSelected(0);
                isFirshEnter = false;
            }

        }

        public kAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(mTitle != null){
                return mTitle[position];
            }
            return super.getPageTitle(position);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = FragmentFactory.getInstance().createrFragment(position);

            return fragment;
        }

        @Override
        public int getCount() {
            if(mTitle != null){
                return mTitle.length;
            }
            return 0;
        }
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerlayout, mToolbar, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void loadData(){

    }
}
