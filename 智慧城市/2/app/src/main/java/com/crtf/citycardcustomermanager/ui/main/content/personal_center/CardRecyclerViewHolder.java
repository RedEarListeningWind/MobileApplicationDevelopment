package com.crtf.citycardcustomermanager.ui.main.content.personal_center;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crtf.citycardcustomermanager.R;

import java.util.Objects;

public class CardRecyclerViewHolder extends RecyclerView.ViewHolder {

    private RecyclerView card;

    public CardRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        this.card = Objects.requireNonNull(itemView.findViewById(R.id.recycler_view_ui_main_content_personal_center_card));
    }

    public RecyclerView getCard() {
        return card;
    }

    public void setCard(RecyclerView card) {
        this.card = card;
    }
}
