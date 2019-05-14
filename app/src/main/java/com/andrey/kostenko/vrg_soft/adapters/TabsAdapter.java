package com.andrey.kostenko.vrg_soft.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.andrey.kostenko.vrg_soft.fragments.ArticleEmailedListFragment;
import com.andrey.kostenko.vrg_soft.fragments.ArticleSavedListFragment;
import com.andrey.kostenko.vrg_soft.fragments.ArticleSharedListFragment;
import com.andrey.kostenko.vrg_soft.fragments.ArticleViewedListFragment;

public class TabsAdapter extends FragmentStatePagerAdapter {

    private String[] tabTitles = new String[]{"Most emailed", "Most shared", "Most viewed", "Saved"};

    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ArticleEmailedListFragment tab1 = new ArticleEmailedListFragment();
                return tab1;
            case 1:
                ArticleSharedListFragment tab2 = new ArticleSharedListFragment();
                return tab2;
            case 2:
                ArticleViewedListFragment tab3 = new ArticleViewedListFragment();
                return tab3;
            case 3:
                ArticleSavedListFragment tab4 = new ArticleSavedListFragment();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
