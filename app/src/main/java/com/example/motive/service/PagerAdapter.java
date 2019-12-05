package com.example.motive.service;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.motive.patient.CheerVideoFragment;
import com.example.motive.patient.MainInfoFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MainInfoFragment tab1 = new MainInfoFragment();
                return tab1;
            case 1:
                CheerVideoFragment tab2 = new CheerVideoFragment();
                return tab2;
            case 2:
                CheerVideoFragment tab3 = new CheerVideoFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}