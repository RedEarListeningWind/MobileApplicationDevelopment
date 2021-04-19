package com.crtf.citycardcustomermanager.ui.main.content.personal_center;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crtf.citycardcustomermanager.R;

import java.util.stream.Stream;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerViewHolder> {

    private Context context;

    private String[] itemTitles;

    private int[] images;

    public ItemRecyclerAdapter(Context context,String[] itemTitles,int[] images) {
        this.context = context;
        this.itemTitles = itemTitles;
        this.images = images;
    }

    @NonNull
    @Override
    public ItemRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_personal_center_item, parent, false);
        return new ItemRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRecyclerViewHolder holder, int position) {
        holder.getImage().setImageResource(this.images[position]);
        holder.getTitle().setText(this.itemTitles[position]);
    }

    @Override
    public int getItemCount() {
        if (this.itemTitles == null || this.images == null)
            return 0;
        return Stream.of(this.itemTitles.length,this.images.length).min(Integer::compareTo).get();
    }
}
