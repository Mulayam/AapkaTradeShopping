package com.aapkatrade.shopping.discover;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter
{
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs)
    {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        for (int i = 0; i < mNumOfTabs; i++)
        {
            if (i == position) {
                CategoriesFragment appFragment = new CategoriesFragment();
                Bundle args = new Bundle();
                args.putString("name", "Position" + i);
                appFragment.setArguments(args);
                return appFragment;

            }

        }
        return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}