package com.crtf.citycardcustomermanager.ui.main.guide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.crtf.citycardcustomermanager.R;

public class GuidePageActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout linearLayout;

    private View[] dotViews;

    private int[] dot = {R.drawable.dot_uncheck, R.drawable.dot_check};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_page);

        this.viewPager = findViewById(R.id.view_pager_ui_main_guide_page_view_pager);
        this.linearLayout = findViewById(R.id.linear_layout_ui_main_guide_page_indicator);
        this.dotViews = new View[]{
                findViewById(R.id.view_guide_page_dot_1),
                findViewById(R.id.view_guide_page_dot_2),
                findViewById(R.id.view_guide_page_dot_3),
                findViewById(R.id.view_guide_page_dot_4),
                findViewById(R.id.view_guide_page_dot_5)
        };

        GuideContentAdapter contentAdapter = new GuideContentAdapter(getSupportFragmentManager(), this);
        this.viewPager.setAdapter(contentAdapter);

        setCurDot(0);

        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //当前页面被滑动时调用
                // arg0 :当前页面，及你点击滑动的页面
                // arg1:当前页面偏移的百分比
                // arg2:当前页面偏移的像素位置
            }

            @Override
            public void onPageSelected(int position) {
                //当前新的页面被选中时调用
                setCurDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //当滑动状态改变时调用
                /*arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。*/
            }
        });

    }

    private void setCurDot(int position) {
        int temp = 0;
        for (int i = 0; i < this.dotViews.length; i++) {
            if (position == i)
                temp = this.dot[1];
            else temp = this.dot[0];

            this.dotViews[i].setBackgroundResource(temp);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        Toast.makeText(this,"单击了: " + keyCode + "\t" + event.toString(),Toast.LENGTH_SHORT).show();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}