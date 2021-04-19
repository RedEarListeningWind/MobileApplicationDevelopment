package com.crtf.citycardcustomermanager.ui.main.content.news;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crtf.citycardcustomermanager.R;

public class NewsFragment extends Fragment {
    public NewsFragment() {
    }
    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news, container, false);

        RecyclerView newsItems = root.findViewById(R.id.recycler_view_ui_main_content_news_items);

        NewsItemsRecyclerAdapter newsItemsRecyclerAdapter = new NewsItemsRecyclerAdapter(getContext());
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        newsItems.setLayoutManager(linearLayoutManager1);
        newsItems.setAdapter(newsItemsRecyclerAdapter);

        return root;
    }
}