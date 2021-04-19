package com.crtf.citycardcustomermanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.crtf.citycardcustomermanager.data.details.InitializeData;
import com.crtf.citycardcustomermanager.ui.main.content.ContentAdapter;
import com.crtf.citycardcustomermanager.ui.main.guide.GuidePageActivity;
import com.crtf.citycardcustomermanager.ui.main.navigation.NavigationBarRecyclerAdapter;
import com.crtf.citycardcustomermanager.ui.main.navigation.NavigationBarRecyclerViewHolder;
import com.crtf.citycardcustomermanager.ui.util.ToolbarUtil;
import com.google.android.material.appbar.AppBarLayout;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "com.crtf.citycardcustomermanager.MainActivity";
    // 等待时间
    private long waitingTime = 0;
    private String[] toolbarTitles;

    private RecyclerView navigationBar;
    private Toolbar toolbar;
    private ViewPager content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(getResources().getColor(R.color.app_bar_color));

        // region 引导页
        boolean guidePage = InitializeData.getInstance(this).getReadData().getBoolean("GuidePage", false);
        if (!guidePage) {
            Intent intent = new Intent(this, GuidePageActivity.class);
            startActivity(intent);
        }
        // endregion

        // region 拿到 View
        this.navigationBar = findViewById(R.id.recycler_view_main_navigation_bar);
        this.toolbar = findViewById(R.id.tool_bar_main);
        this.content = findViewById(R.id.view_pager_ui_main_content_content);
        // endregion

        // region 加载资源
        this.toolbarTitles = getResources().getStringArray(R.array.toolbar_titles);
        // endregion

        // region 底部导航栏
        NavigationBarRecyclerAdapter recyclerAdapter = new NavigationBarRecyclerAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, recyclerAdapter.getItemCount(), LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }

            @Override
            public int getSpanGroupIndex(int adapterPosition, int spanCount) {
                return super.getSpanGroupIndex(adapterPosition, spanCount);
            }
        });
        this.navigationBar.setLayoutManager(gridLayoutManager);
        this.navigationBar.setAdapter(recyclerAdapter);
        // endregion

        // region app bar layout
        ToolbarUtil.setTitleCenter(this.toolbar);
        setSupportActionBar(this.toolbar);
        // endregion

        // region content(内容)
        ContentAdapter contentAdapter = new ContentAdapter(getSupportFragmentManager(), this);
        this.content.setAdapter(contentAdapter);
        // endregion

        // region 底部导航栏 app bar content 联动
//        setNavigationBarCheck(0);     // item 还未加载设置不成功
        this.content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setNavigationBarCheck(position);

                setToolbarTitle(position);

                content.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        this.navigationBar.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return new GestureDetectorCompat(rv.getContext(), new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public void onLongPress(MotionEvent e1) {
                        View childViewUnder = rv.findChildViewUnder(e1.getX(), e1.getY());
                        RecyclerView.ViewHolder childViewHolder = rv.getChildViewHolder(childViewUnder);
                        int position = childViewHolder.getAdapterPosition();
                        setNavigationBarCheck(position);

                        setToolbarTitle(position);

                        content.setCurrentItem(position);
                    }
                }).onTouchEvent(e);
            }
        });
        // endregion


    }

    /**
     * 设置通知栏的状态
     *
     * @param on
     */
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void setToolbarTitle(int position) {
        if (this.toolbarTitles.length >= position && position >= 0) {
            this.toolbar.setTitle(this.toolbarTitles[position]);
        }
    }

    private void setNavigationBarCheck(int position) {
        // region 这种方法只有最后一个可用，原因不明
//        RecyclerView.LayoutManager layoutManager = this.navigationBar.getLayoutManager();
//        int childCount = layoutManager.getItemCount();
//        for (int i = 0; i < childCount; i++) {
//            View childAt = layoutManager.getChildAt(position);
//            if (childAt != null) {
//                TextView title = childAt.findViewById(R.id.text_view_item_navigation_bar_title);
//                if (title != null) {
//                    int navigation_bar_color;
//                    if (i == position) {
//                        navigation_bar_color = getResources().getColor(R.color.navigation_bar_check);
//                    } else {
//                        navigation_bar_color = getResources().getColor(R.color.navigation_bar_uncheck);
//                    }
//                    title.setTextColor(navigation_bar_color);
//                }
//            }
//        }
        // endregion

        NavigationBarRecyclerAdapter adapter = (NavigationBarRecyclerAdapter) this.navigationBar.getAdapter();
        int itemCount = adapter.getItemCount();
        for (int i = 0; i < itemCount; i++) {
            RecyclerView.ViewHolder viewHolderForAdapterPosition = this.navigationBar.findViewHolderForAdapterPosition(i);
            if (viewHolderForAdapterPosition != null) {
                NavigationBarRecyclerViewHolder navigationBarRecyclerViewHolder = (NavigationBarRecyclerViewHolder) viewHolderForAdapterPosition;
                adapter.setView(navigationBarRecyclerViewHolder, i, position);
//                TextView title = viewHolderForAdapterPosition.itemView.findViewById(R.id.text_view_item_navigation_bar_title);
//                if (title != null) {
//                    int navigation_bar_color;
//                    if (i == position){
//                        navigation_bar_color = getResources().getColor(R.color.navigation_bar_check);
//                    }else {
//                        navigation_bar_color = getResources().getColor(R.color.navigation_bar_uncheck);
//                    }
//                    title.setTextColor(navigation_bar_color);
//                }
            }
        }
    }

    public static void intentStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);

        context.startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - this.waitingTime < 1000) {
                finish();
            }
            this.waitingTime = System.currentTimeMillis();
            Toast.makeText(this, "再按一下退出!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}