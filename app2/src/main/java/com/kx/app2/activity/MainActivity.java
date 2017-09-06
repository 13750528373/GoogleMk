package com.kx.app2.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.kx.app2.R;
import com.kx.app2.fragment.AppFragemt;
import com.kx.app2.fragment.HomeFragment;
import com.kx.app2.fragment.OtherFragment;
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
        mTitle = UiUtils.getStringArray(R.array.pagers);

        mVp.setAdapter(new kAdapter(getSupportFragmentManager()));
        mTablayout.setupWithViewPager(mVp);
    }


    public class kAdapter extends FragmentPagerAdapter{

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
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new AppFragemt();
                    break;
                default:
                    fragment = new OtherFragment();
                    break;
            }

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


}
