package com.crtf.citycardcustomermanager.ui.main.navigation;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crtf.citycardcustomermanager.R;

import java.util.Objects;

public class NavigationBarRecyclerViewHolder extends RecyclerView.ViewHolder {

    private ImageView image;
    private TextView title;

    public NavigationBarRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        this.image = Objects.requireNonNull(itemView.findViewById(R.id.image_view_item_navigation_bar_image));
        this.title = Objects.requireNonNull(itemView.findViewById(R.id.text_view_item_navigation_bar_title));
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getTitle() {
        return title;
    }
}
