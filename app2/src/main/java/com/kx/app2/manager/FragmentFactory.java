package com.kx.app2.manager;

import com.kx.app2.base.BaseFragment;
import com.kx.app2.fragment.AppFragemt;
import com.kx.app2.fragment.HomeFragment;
import com.kx.app2.fragment.OtherFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by KX on 2017/9/6.
 */

public class FragmentFactory  {
    Map<Integer,BaseFragment> mFragmentCache = new HashMap<>();

    private static FragmentFactory sInstance;

    public FragmentFactory() {
        super();
    }

    public static FragmentFactory getInstance() {
        if(sInstance == null){
            sInstance = new FragmentFactory();
        }
        return sInstance;
    }

    public BaseFragment createrFragment(int position){
        if(mFragmentCache.containsKey(position)){
            return mFragmentCache.get(position);
        }

        BaseFragment fragment = null;

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

        mFragmentCache.put(position,fragment);
        return fragment;
    }

}
