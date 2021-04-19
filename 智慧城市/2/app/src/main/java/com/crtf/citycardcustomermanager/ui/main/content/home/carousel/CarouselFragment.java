package com.crtf.citycardcustomermanager.ui.main.content.home.carousel;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.RawRes;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.crtf.citycardcustomermanager.R;

/**
 * 轮播
 */
public class CarouselFragment extends Fragment {
    private static final String TAG = "com.crtf.citycardcustomermanager.ui.main.content.home.carousel.CarouselFragment";
    private Context context;
    private int image;

    public CarouselFragment(Context context, int image) {
        this.context = context;
        this.image = image;
    }

    public static CarouselFragment newInstance(Context context, int image) {
        return new CarouselFragment(context, image);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_carousel, container, false);
        ImageView imageView = inflate.findViewById(R.id.image_view_ui_main_content_carousel);
        imageView.setImageResource(this.image);
        return inflate;
    }

}