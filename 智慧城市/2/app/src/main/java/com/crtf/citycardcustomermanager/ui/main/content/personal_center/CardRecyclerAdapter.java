package com.crtf.citycardcustomermanager.ui.main.content.personal_center;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crtf.citycardcustomermanager.R;

import java.util.stream.Stream;

public class CardRecyclerAdapter extends RecyclerView.Adapter<CardRecyclerViewHolder> {
    private static final int SIZE = 3;

    private Context context;

    private String[] itemTitles;

    private int[] images = {
            R.mipmap.my_cardbag_flag,
            R.mipmap.my_personalinformation_flag,
            R.mipmap.my_accountsecurity_flag,
            R.mipmap.my_suggestion_flag,
            R.mipmap.my_vip_flag,
            R.mipmap.my_about_flag,
    };



    public CardRecyclerAdapter(Context context) {
        this.context = context;
        this.itemTitles = this.context.getResources().getStringArray(R.array.personal_center_item_titles);
    }

    @NonNull
    @Override
    public CardRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_personal_center_card, parent, false);
        return new CardRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardRecyclerViewHolder holder, int position) {
        String[] itemTitles = new String[SIZE];
        int[] images = new int[SIZE];
        System.arraycopy(this.images,position*SIZE,images,0,SIZE);
        System.arraycopy(this.itemTitles,position*SIZE,itemTitles,0,SIZE);

        ItemRecyclerAdapter itemRecyclerAdapter = new ItemRecyclerAdapter(this.context, itemTitles, images);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.getCard().setLayoutManager(linearLayoutManager);
        holder.getCard().setAdapter(itemRecyclerAdapter);
    }

    @Override
    public int getItemCount() {
        if (this.images == null || this.itemTitles == null)
            return 0;
        Integer count = Stream.of(this.images.length, this.itemTitles.length).min(Integer::compareTo).get();
        return count / SIZE + ((count % SIZE == 0) ? 0 : 1);
    }
}
