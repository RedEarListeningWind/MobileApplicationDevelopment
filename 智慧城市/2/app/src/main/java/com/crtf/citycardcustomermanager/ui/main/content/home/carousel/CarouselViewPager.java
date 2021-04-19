package com.crtf.citycardcustomermanager.ui.main.content.home.carousel;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.crtf.citycardcustomermanager.util.Log;

/**
 * 与预想不符
 */
@Deprecated
public class CarouselViewPager extends ViewPager {


    private static final String TAG = "com.crtf.citycardcustomermanager.ui.main.content.home.carousel.CarouselViewPager";

    public CarouselViewPager(@NonNull Context context) {
        super(context);
        Log.i(TAG, "CarouselViewPager: 构造");
    }

    public CarouselViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "CarouselViewPager: 构造");
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
        int count = this.getAdapter().getCount();
        if (item == 0) {
            item = count - 2;
        } else if (item == count - 1) {
            item = 1;
        } else return;
        Log.i(TAG, "setCurrentItem: " + item);
        super.setCurrentItem(item,false);
    }

    @Deprecated
    void setCurrentItemInternal(int item, boolean smoothScroll, boolean always, int velocity){

    }
}
