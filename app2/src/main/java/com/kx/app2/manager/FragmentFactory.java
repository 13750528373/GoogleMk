package com.kx.app2.manager;

import com.kx.app2.base.BaseFragment;
import com.kx.app2.fragment.AppFragemt;
import com.kx.app2.fragment.CategotyFragment;
import com.kx.app2.fragment.GameFragemt;
import com.kx.app2.fragment.HomeFragment;
import com.kx.app2.fragment.HotFragment;
import com.kx.app2.fragment.RecommendFragment;
import com.kx.app2.fragment.SubjectFragment;

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
            case 2:
                fragment = new GameFragemt();
                break;
            case 3:
                fragment = new SubjectFragment();
                break;
            case 4:
                fragment = new RecommendFragment();
                break;
            case 5:
                fragment = new CategotyFragment();
                break;
            case 6:
                fragment = new HotFragment();
                break;

        }

        mFragmentCache.put(position,fragment);
        return fragment;
    }

}
