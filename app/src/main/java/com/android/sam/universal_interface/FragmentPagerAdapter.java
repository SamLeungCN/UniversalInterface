package com.android.sam.universal_interface;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by Sam on 2018-03-27.
 * E-mail:729717222@qq.com
 */

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    public FragmentPagerAdapter(FragmentManager fm,
                                List<Fragment> fragments) {
        super(fm);
        this.fragmentList = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        if (fragmentList == null) {
            return 0;
        }
        return fragmentList.size();
    }
}
