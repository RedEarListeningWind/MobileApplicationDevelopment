package com.crtf.citycardcustomermanager.ui.main.content.news;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crtf.citycardcustomermanager.R;

import java.util.Objects;

public class NewsCategoryRecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private View dividingLineRight;
    private View dividingLineBottom;

    public NewsCategoryRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        this.title = Objects.requireNonNull(this.itemView.findViewById(R.id.text_view_ui_main_content_news_news_category_title));
        this.dividingLineRight = Objects.requireNonNull(this.itemView.findViewById(R.id.view_ui_main_content_news_news_category_dividing_line_right));
        this.dividingLineBottom = Objects.requireNonNull(itemView.findViewById(R.id.view_ui_main_content_news_news_category_dividing_line_bottom));
    }
    public  void setNewsCategoryCheck(boolean check) {
        int titleColor;
        int viewVisibility;
        if (check) {
            titleColor = this.itemView.getContext().getResources().getColor(R.color.news_Category_title_check);
            viewVisibility = View.VISIBLE;
        } else {
            titleColor = this.itemView.getContext().getResources().getColor(R.color.news_Category_title_uncheck);
            viewVisibility = View.GONE;
        }
        this.getTitle().setTextColor(titleColor);
        this.getDividingLineBottom().setVisibility(viewVisibility);
    }
    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public View getDividingLineRight() {
        return dividingLineRight;
    }

    public void setDividingLineRight(View dividingLineRight) {
        this.dividingLineRight = dividingLineRight;
    }

    public View getDividingLineBottom() {
        return dividingLineBottom;
    }

    public void setDividingLineBottom(View dividingLineBottom) {
        this.dividingLineBottom = dividingLineBottom;
    }

}
