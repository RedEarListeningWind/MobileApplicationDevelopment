package com.crtf.citycardcustomermanager.ui.main.content.home.recommended_service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.crtf.citycardcustomermanager.R;
import com.crtf.citycardcustomermanager.util.Log;

import java.util.Arrays;
import java.util.stream.Stream;

public class RecommendedServiceRecyclerAdapter extends RecyclerView.Adapter<RecommendedServiceRecyclerHolder> {
    private static final String TAG = "com.crtf.citycardcustomermanager.ui.main.content.home.recommended_service.RecommendedServiceRecyclerAdapter";
    // 推荐服务 image
    private int[] image = {
            R.mipmap.logo,
            R.mipmap.logo,
            R.mipmap.logo,
            R.mipmap.logo,
            R.mipmap.logo,
            R.mipmap.logo,
            R.mipmap.logo,
            R.mipmap.logo,
            R.mipmap.logo,
    };
    // 推荐服务 title
    private String[] titles;

    // 用到的布局文件
    @LayoutRes
    private int resource = R.layout.item_recommended_service;

    private Context context;

    public RecommendedServiceRecyclerAdapter(Context context) {
        this.context = context;
        String[] scr = this.context.getResources().getStringArray(R.array.recommended_service_titles);
        String[] target = new String[scr.length + 1];
        System.arraycopy(scr,0,target,0,scr.length);
        target[target.length - 1] = this.context.getResources().getString(R.string.recommended_service_title_more_service);
        this.titles = target;
    }

    @NonNull
    @Override
    public RecommendedServiceRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = (ConstraintLayout) LayoutInflater.from(this.context).inflate(this.resource, parent, false);
        return new RecommendedServiceRecyclerHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedServiceRecyclerHolder holder, int position) {
        holder.getImage().setImageResource(this.image[position]);
        holder.getTitle().setText(this.titles[position]);

        Log.i(TAG, "onBindViewHolder: " + holder.getImage().getHeight());
    }

    @Override
    public int getItemCount() {
        if (this.titles == null || this.image == null)
            return 0;
        return Stream.of(this.image.length, this.titles.length).min(Integer::compareTo).get();
    }
}
