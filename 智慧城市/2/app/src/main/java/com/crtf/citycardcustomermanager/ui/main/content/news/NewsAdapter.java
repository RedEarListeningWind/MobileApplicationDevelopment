package com.crtf.citycardcustomermanager.ui.main.content.news;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class NewsAdapter extends FragmentPagerAdapter {
private int itemCount;

    public NewsAdapter(@NonNull FragmentManager fm,int itemCount) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.itemCount = itemCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return NewsFragment.newInstance();
    }

    @Override
    public int getCount() {
        return itemCount;
    }
}
