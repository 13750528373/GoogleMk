package com.kx.googlemk.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by KX on 2017/9/4.
 */

public class MyAdapter extends FragmentPagerAdapter {

    private String[] stringArray;


    public MyAdapter(FragmentManager fm) {
        super(fm);
      //TODO
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
