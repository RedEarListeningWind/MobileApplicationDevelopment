package com.crtf.citycardcustomermanager.ui.main.content;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.crtf.citycardcustomermanager.R;

public class ContentAdapter extends FragmentPagerAdapter {

    private Context context;

    // 首页、全部服务、新闻、活动、个人中心
    private int[] fragmentContent = {
            R.layout.fragment_content_home_page,
            R.layout.fragment_content_full_services,
            R.layout.fragment_content_news,
            R.layout.fragment_content_events,
            R.layout.fragment_content_personal_center
    };

    public ContentAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ContentFragment.newInstance(this.fragmentContent[position]);
    }

    @Override
    public int getCount() {
        return this.fragmentContent.length;
    }
}
