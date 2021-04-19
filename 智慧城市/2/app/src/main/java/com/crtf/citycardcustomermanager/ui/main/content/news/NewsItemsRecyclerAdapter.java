package com.crtf.citycardcustomermanager.ui.main.content.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crtf.citycardcustomermanager.R;

import java.util.Map;
import java.util.stream.Stream;

public class NewsItemsRecyclerAdapter extends RecyclerView.Adapter<NewsItemsRecyclerViewHolder> {
    @LayoutRes
    private  int resource = R.layout.item_news_items;
    private Context context;

    private static Map<Integer,Map<Integer,String>> titleMap;
    private static Map<Integer,Map<Integer,String>> descMap;
    private static Map<Integer,Map<Integer,String>> watchNumberMap;

    private int[] images;

    public NewsItemsRecyclerAdapter(Context context) {
        this.context = context;
//        if (titleMap == null || descMap == null || watchNumberMap == null) {
//            String[] titles = this.context.getResources().getStringArray(R.array.news_items_titles);
//            String[] desc = this.context.getResources().getStringArray(R.array.news_items_desc);
//            String[] watchNumbers = this.context.getResources().getStringArray(R.array.news_items_watch_numbers);
////            titleMap = Stream.of(titles).map(m -> m)
//        }
    }

    @NonNull
    @Override
    public NewsItemsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(this.context).inflate(this.resource, parent, false);
        return new NewsItemsRecyclerViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsItemsRecyclerViewHolder holder, int position) {
        holder.getTitle().setText("2021年4月19日");
        holder.getDesc().setText("赤耳听风•04-16 1:01");
        holder.getWatchNumber().setText("12");
        holder.getImage().setImageResource(R.mipmap.carousel_1);
    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
