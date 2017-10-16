package com.example.android.recyclerviewexample.screen.showlist;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import com.example.android.recyclerviewexample.screen.wordList
        .WordListFragment;

/**
 * Created by VinhTL on 11/10/2017.
 */

public class ShowListPagerAdapter extends FragmentStatePagerAdapter {
    private final int TAB_NUMBER = 3;

    public ShowListPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    
    @Override
    public Fragment getItem(int position) {
        return new WordListFragment();
    }
    
    @Override
    public int getCount() {
        return TAB_NUMBER;
    }
}
