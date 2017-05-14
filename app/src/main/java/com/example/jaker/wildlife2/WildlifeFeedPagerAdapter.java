package com.example.jaker.wildlife2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by jaker on 14/05/2017.
 */

public class WildlifeFeedPagerAdapter extends FragmentPagerAdapter
{
    private ArrayList<Fragment> fragments;

    public WildlifeFeedPagerAdapter(android.support.v4.app.FragmentManager fm, ArrayList<Fragment> fragments)
    {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Updates";
            case 1:
                return "Latest";
            case 2:
                return "Collections";
        }
        return null;
    }
}
