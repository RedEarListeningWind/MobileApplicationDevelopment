package com.crtf.citycardcustomermanager.ui.main.content.home.recommended_service;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crtf.citycardcustomermanager.R;

import java.util.Objects;

public class RecommendedServiceRecyclerHolder extends RecyclerView.ViewHolder {
    private ImageView image;
    private TextView title;

    public RecommendedServiceRecyclerHolder(@NonNull View itemView) {
        super(itemView);
        this.image = Objects.requireNonNull(itemView.findViewById(R.id.image_view_item_navigation_bar_image));
        this.title = Objects.requireNonNull(itemView.findViewById(R.id.text_view_item_navigation_bar_title));
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }
}
