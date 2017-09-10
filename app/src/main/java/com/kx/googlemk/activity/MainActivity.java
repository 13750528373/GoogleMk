package com.kx.googlemk.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.kx.googlemk.R;
import com.kx.googlemk.fragment.AppFragment;
import com.kx.googlemk.fragment.HomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.drawlayout)
    DrawerLayout mDrawlayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.tablayout)
    android.support.design.widget.TabLayout mTablayout;

    private String[] mTitle = {"首页", "应用", "游戏", "专题", "推荐", "分类", "排行"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolBar();


        mVp.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager()));
        mTablayout.setupWithViewPager(mVp);

    }


    private class MainFragmentPagerAdapter extends FragmentPagerAdapter {

        public MainFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (mTitle != null) {
                return mTitle[position];
            }
            return super.getPageTitle(position);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new AppFragment();
                    break;
                default:
                    fragment = new OtherFragment();
                    break;
            }


            return fragment;
        }

        @Override
        public int getCount() {
            if (mTitle != null) {
                return mTitle.length;
            }
            return 0;
        }
    }




    /**
     * 创建上面的导航栏，左边的抽屉
     **/
    private void initToolBar() {
        //1支持toolbar  //step2.创建抽屉开关，关联所有对象//step3.添加监听，控制开与关//同步状态，图标
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawlayout, mToolbar, R.string.open, R.string.close);
        mDrawlayout.addDrawerListener(toggle);
        toggle.syncState();
    }


}
