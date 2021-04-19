package com.crtf.citycardcustomermanager.ui.main.navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crtf.citycardcustomermanager.R;
import com.crtf.citycardcustomermanager.util.Log;

import java.util.stream.Stream;

public class NavigationBarRecyclerAdapter extends RecyclerView.Adapter<NavigationBarRecyclerViewHolder> {

    private static final String TAG = "com.crtf.citycardcustomermanager.ui.main.navigation.NavigationBarRecyclerAdapter";
    private String[] titles;

    private int[] uncheckTabs = {
            R.mipmap.tab_1_n,
            R.mipmap.tab_2_n,
            R.mipmap.tab_3_n,
            R.mipmap.tab_4_n,
            R.mipmap.tab_5_n,
    };
    private int[] checkTabs = {
            R.mipmap.tab_1_h,
            R.mipmap.tab_2_h,
            R.mipmap.tab_3_h,
            R.mipmap.tab_4_h,
            R.mipmap.tab_5_h,
    };

    private Context context;
    private LayoutInflater mInflater;

    public NavigationBarRecyclerAdapter(Context context) {
        this.context = context;
        this.titles = context.getResources().getStringArray(R.array.item_navigation_bar_titles);
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NavigationBarRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = this.mInflater.inflate(R.layout.item_navigation_bar, parent, false);
        return new NavigationBarRecyclerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull NavigationBarRecyclerViewHolder holder, int position) {
        holder.getTitle().setText(this.titles[position]);

        setView(holder, position,0);
    }

    public void setView(@NonNull NavigationBarRecyclerViewHolder holder, int position,int checkPosition) {
        int itemCount = getItemCount();
        if (position < itemCount && checkPosition < itemCount) {
            int tab;
            int navigation_bar_color;
            if (position == checkPosition) {
                tab = this.checkTabs[position];
                navigation_bar_color = this.context.getResources().getColor(R.color.navigation_bar_check);
            } else {
                tab = this.uncheckTabs[position];
                navigation_bar_color = this.context.getResources().getColor(R.color.navigation_bar_uncheck);
            }
            holder.getImage().setImageResource(tab);
            holder.getTitle().setTextColor(navigation_bar_color);
        }else {
            Log.e(TAG, "setView: {position(" + position + ") || checkPosition(" + checkPosition + ")} >= getItemCount(" + getItemCount() + ")");
        }
    }


    @Override
    public int getItemCount() {
        return Stream.of(this.titles.length, this.checkTabs.length,this.uncheckTabs.length).min(Integer::compareTo).get();
    }
}
