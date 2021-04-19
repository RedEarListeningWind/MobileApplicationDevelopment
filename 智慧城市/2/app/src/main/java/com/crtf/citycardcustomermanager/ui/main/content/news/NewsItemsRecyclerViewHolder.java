package com.crtf.citycardcustomermanager.ui.main.content.news;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crtf.citycardcustomermanager.R;

import java.util.Objects;

public class NewsItemsRecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView desc;
    private TextView watchNumber;
    private ImageView image;
    public NewsItemsRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        this.title = Objects.requireNonNull(itemView.findViewById(R.id.text_view_ui_main_content_news_news_items_title));
        this.desc = Objects.requireNonNull(itemView.findViewById(R.id.text_view_ui_main_content_news_news_items_desc));
        this.watchNumber = Objects.requireNonNull(itemView.findViewById(R.id.text_view_ui_main_content_news_news_items_watch_number));
        this.image = Objects.requireNonNull(itemView.findViewById(R.id.image_view_ui_main_content_news_news_items_image));

    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getDesc() {
        return desc;
    }

    public void setDesc(TextView desc) {
        this.desc = desc;
    }

    public TextView getWatchNumber() {
        return watchNumber;
    }

    public void setWatchNumber(TextView watchNumber) {
        this.watchNumber = watchNumber;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}
