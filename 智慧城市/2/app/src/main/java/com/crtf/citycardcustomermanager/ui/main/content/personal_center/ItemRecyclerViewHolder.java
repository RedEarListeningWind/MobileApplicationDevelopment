package com.crtf.citycardcustomermanager.ui.main.content.personal_center;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crtf.citycardcustomermanager.R;

import java.util.Objects;

public class ItemRecyclerViewHolder extends RecyclerView.ViewHolder {
    private ImageView image;
    private TextView title;

    public ItemRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        this.image = Objects.requireNonNull(itemView.findViewById(R.id.image_view_ui_main_content_personal_center_item));
        this.title = Objects.requireNonNull(itemView.findViewById(R.id.text_view_ui_main_content_personal_center_item));
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
