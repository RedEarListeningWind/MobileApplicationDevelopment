package com.crtf.citycardcustomermanager.ui.main.content.home.carousel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.crtf.citycardcustomermanager.R;

public class CarouselAdapter extends FragmentPagerAdapter {

    private Context context;
    private int[] images = {
            R.mipmap.carousel_1,
            R.mipmap.carousel_2,
            R.mipmap.carousel_3,
            R.mipmap.carousel_4,
    };
    public CarouselAdapter(@NonNull FragmentManager fm,Context context) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return CarouselFragment.newInstance(this.context,this.images[getActualPosition(position)]);
    }

    @Override
    public int getCount() {
        return images == null ?0:images.length + 2;
    }
    // 通过实际的position计算用户所理解的position
    private int getActualPosition(int position) {
        if (position == 0) {
            // 如果是头节点，返回最后一个数据的position
            // 因为头节点的数据和最后一个数据是一样的。
            return this.images.length - 1;
        } else if (position == getCount() - 1) {
            // 如果是尾节点，返回第一个数据的position
            // 因为尾节点的数据和第一个数据是一样的。
            return 0;
        } else {
            // 除了头尾以外，position节点的数据是mDatas中的第position - 1的下标的数据
            return position - 1;
        }
    }
}
