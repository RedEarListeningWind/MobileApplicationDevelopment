package com.crtf.citycardcustomermanager.ui.main.content.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crtf.citycardcustomermanager.R;

public class NewsCategoryRecyclerAdapter extends RecyclerView.Adapter<NewsCategoryRecyclerViewHolder> {
    @LayoutRes
    private int resource = R.layout.item_news_category;
    private Context context;
    private String[] titles;

    public NewsCategoryRecyclerAdapter(Context context) {
        this.context = context;
        this.titles = this.context.getResources().getStringArray(R.array.news_category_titles);
    }

    @NonNull
    @Override
    public NewsCategoryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(this.context).inflate(this.resource, parent, false);

        return new NewsCategoryRecyclerViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsCategoryRecyclerViewHolder holder, int position) {
        holder.getTitle().setText(this.titles[position]);
        if (position == 0) {
            holder.setNewsCategoryCheck(true);
        }else {
            holder.setNewsCategoryCheck(false);
        }
        if (position == getItemCount() - 1) {
            holder.getDividingLineRight().setVisibility(View.GONE);
        } else {
            holder.getDividingLineRight().setVisibility(View.VISIBLE);
        }

    }
    @Override
    public int getItemCount() {
        return this.titles == null ? 0 : this.titles.length;
    }
}
