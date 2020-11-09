package com.example.foody.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.foody.list_store;
import com.example.foody.tab_nodata;

public class PageAdapter extends FragmentStatePagerAdapter {

    private int  numTab;
    public PageAdapter(@NonNull FragmentManager fm, int numTab) {
        super(fm);
        this.numTab = numTab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Log.d("tab1","asda");
                return new list_store();
            case 1:
                Log.d("tab1","asda");
                return new tab_nodata();
            default:
                return new tab_nodata();
        }
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return numTab;
    }
}