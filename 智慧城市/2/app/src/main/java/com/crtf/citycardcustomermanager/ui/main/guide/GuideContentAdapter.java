package com.crtf.citycardcustomermanager.ui.main.guide;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.crtf.citycardcustomermanager.R;

public class GuideContentAdapter extends FragmentPagerAdapter {
    private final int[] fragmentLayouts = {
            R.layout.fragment_guide_content,
            R.layout.fragment_guide_content,
            R.layout.fragment_guide_content,
            R.layout.fragment_guide_content,
            R.layout.fragment_guide_network_settings};
    private final TypedArray guidePageColor;

    private final Drawable defaultColor;

    public GuideContentAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.guidePageColor = context.getResources().obtainTypedArray(R.array.guide_page_color);
        this.defaultColor = context.getResources().getDrawable(R.color.guide_page_default);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
            fragment = GuideContentFragment.newInstance(this.guidePageColor.getDrawable(position), this.fragmentLayouts[position]);
        return fragment;
    }

    @Override
    public int getCount() {
        return this.guidePageColor.length();
    }


}
