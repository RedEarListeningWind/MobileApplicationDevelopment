package com.crtf.citycardcustomermanager.ui.main.guide;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crtf.citycardcustomermanager.R;
import com.crtf.citycardcustomermanager.data.details.InitializeData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GuideContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuideContentFragment extends Fragment {
    private int fragmentLayout;
    private final Drawable color;


    public GuideContentFragment(Drawable color, int fragmentLayout) {
        this.fragmentLayout = fragmentLayout;
        this.color = color;
    }

    public static GuideContentFragment newInstance(Drawable color, int fragmentLayout) {
        GuideContentFragment fragment = new GuideContentFragment(color, fragmentLayout);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(this.fragmentLayout, container, false);
//        root.findViewById(R.id.frame_layout_ui_main_content_root).setBackground(this.color);
        root.setBackground(this.color);

        switch (this.fragmentLayout) {
            case R.layout.fragment_guide_network_settings:
                FloatingActionButton floatingActionButton = root.findViewById(R.id.floating_action_button_ui_main_guide_network_setting);
                floatingActionButton.setOnClickListener(view -> {
                    View inflate = getLayoutInflater().inflate(R.layout.item_guide_network_settings, null);
                    new AlertDialog.Builder(getContext())
                            .setTitle(getResources().getString(R.string.network_setting_header))
                            .setView(inflate)
                            .setPositiveButton("确定",(dialog,which) -> {
                                complete();
                            }).setNegativeButton("取消",(dialog,which) -> {

                            }).create().show();
                });
                break;
            case R.layout.fragment_guide_content:
                break;
            default:
                break;
        }

        return root;
    }

    /**
     * 网络设置完成
     */
    private void complete() {
        InitializeData.getInstance(getContext()).getWriteData().putBoolean("GuidePage",true).apply();
        getActivity().finish();
    }
}