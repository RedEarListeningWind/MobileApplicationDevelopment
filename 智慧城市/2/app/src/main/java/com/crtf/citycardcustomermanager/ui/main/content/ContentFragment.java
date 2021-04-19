package com.crtf.citycardcustomermanager.ui.main.content;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;

import com.crtf.citycardcustomermanager.R;
import com.crtf.citycardcustomermanager.ui.main.content.home.carousel.CarouselAdapter;
import com.crtf.citycardcustomermanager.ui.main.content.home.carousel.CarouselFragment;
import com.crtf.citycardcustomermanager.ui.main.content.home.carousel.ViewPagerScroller;
import com.crtf.citycardcustomermanager.ui.main.content.home.recommended_service.RecommendedServiceRecyclerAdapter;
import com.crtf.citycardcustomermanager.ui.main.content.news.NewsAdapter;
import com.crtf.citycardcustomermanager.ui.main.content.news.NewsCategoryRecyclerAdapter;
import com.crtf.citycardcustomermanager.ui.main.content.news.NewsCategoryRecyclerViewHolder;
import com.crtf.citycardcustomermanager.ui.main.content.news.NewsItemsRecyclerAdapter;
import com.crtf.citycardcustomermanager.ui.main.content.personal_center.CardRecyclerAdapter;
import com.crtf.citycardcustomermanager.util.Log;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentFragment extends Fragment {

    private static final String TAG = "com.crtf.citycardcustomermanager.ui.main.content.ContentFragment";

    public static ContentFragment newInstance(int resource) {
        return new ContentFragment(resource);
    }

    @LayoutRes
    private int resource;

    private ContentFragment(int resource) {
        this.resource = resource;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(this.resource, container, false);
        switch (this.resource) {
            case R.layout.fragment_content_home_page:
                this.carouselViewPager = root.findViewById(R.id.view_pager_ui_main_content_home_page_carousel);
                RecyclerView recyclerView = root.findViewById(R.id.recycler_view_ui_main_content_home_page_recommended_service);
                // 轮播
                CarouselAdapter carouselAdapter = new CarouselAdapter(getChildFragmentManager(), getContext());
                this.carouselViewPager.setAdapter(carouselAdapter);

                setCarouselOnPageChangeListener(carouselAdapter);
                // 推荐服务
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 5, LinearLayoutManager.VERTICAL, false);
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
                recyclerView.setLayoutManager(gridLayoutManager);
                RecommendedServiceRecyclerAdapter recommendedServiceRecyclerAdapter = new RecommendedServiceRecyclerAdapter(getContext());
                recyclerView.setAdapter(recommendedServiceRecyclerAdapter);

                // 新闻
                loadNewsPage(root);

                root.getViewTreeObserver().addOnPreDrawListener(
                        new ViewTreeObserver.OnPreDrawListener() {

                            @Override
                            public boolean onPreDraw() {
                                root.getViewTreeObserver().removeOnPreDrawListener(this);
                                int width = root.getWidth();// 获取宽度
                                int height = root.getHeight();// 获取高度
                                Log.i(TAG, "onCreateView: " + width + "," + height);
                                View viewById = root.findViewById(R.id.include_ui_main_content_home_page_news);
                                viewById.getLayoutParams().height = height;
//                                root.getViewTreeObserver().removeOnPreDrawListener(this);
                                return true;
                            }
                        }
                );

                break;
            case R.layout.fragment_content_full_services:
                break;
            case R.layout.fragment_content_news:
                loadNewsPage(root);
                break;
            case R.layout.fragment_content_events:
                break;
            case R.layout.fragment_content_personal_center:
                RecyclerView personalCenterRecycler  = root.findViewById(R.id.recycler_view_ui_main_content_personal_center_all);

                CardRecyclerAdapter cardRecyclerAdapter = new CardRecyclerAdapter(getContext());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                personalCenterRecycler.setLayoutManager(linearLayoutManager);
                personalCenterRecycler.setAdapter(cardRecyclerAdapter);

                break;
            default:
                break;
        }
        return root;
    }

    /**
     * 加载新闻页面
     *
     * @param root
     */
    private void loadNewsPage(View root) {
        RecyclerView newsCategory = root.findViewById(R.id.recycler_view_ui_main_content_news_category);
        ViewPager newsFragment = root.findViewById(R.id.view_pager_ui_main_content_news_items);

        NewsCategoryRecyclerAdapter newsCategoryRecyclerAdapter = new NewsCategoryRecyclerAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        newsCategory.setLayoutManager(linearLayoutManager);
        newsCategory.setAdapter(newsCategoryRecyclerAdapter);

        NewsAdapter newsAdapter = new NewsAdapter(getChildFragmentManager(), Objects.requireNonNull(newsCategory.getAdapter()).getItemCount());
        newsFragment.setAdapter(newsAdapter);

        newsCategory.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return new GestureDetectorCompat(rv.getContext(), new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public void onLongPress(MotionEvent e1) {
                        View childViewUnder = rv.findChildViewUnder(e1.getX(), e1.getY());
                        NewsCategoryRecyclerViewHolder childViewHolder = (NewsCategoryRecyclerViewHolder) rv.getChildViewHolder(childViewUnder);
                        int position = childViewHolder.getAdapterPosition();
                        Log.i(TAG, "onLongPress: position: " + position);

                        newsFragment.setCurrentItem(position);
                        setNewsCategory(position, newsCategory);

                    }
                }).onTouchEvent(e);
            }
        });
        newsFragment.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                setNewsCategory(position, newsCategory);
            }
        });
    }

    private void setNewsCategory(int position, RecyclerView newsCategory) {
        // newsCategory.getAdapter().notifyDataSetChanged();
        int itemCount = newsCategory.getAdapter().getItemCount();
        for (int i = 0; i < itemCount; i++) {
            RecyclerView.ViewHolder holder = newsCategory.findViewHolderForAdapterPosition(i);
            if (holder instanceof NewsCategoryRecyclerViewHolder) {
                NewsCategoryRecyclerViewHolder newsCategoryRecyclerViewHolder = (NewsCategoryRecyclerViewHolder) holder;
                if (i == position) {
                    newsCategoryRecyclerViewHolder.setNewsCategoryCheck(true);
                } else {
                    newsCategoryRecyclerViewHolder.setNewsCategoryCheck(false);
                }
                Log.i(TAG, "onLongPress: position: " + position);
            }
        }
    }
    // region 轮播 相关配置

    /**
     * 绑定 轮播 页面改变事件监听器
     *
     * @param carouselAdapter
     */
    private void setCarouselOnPageChangeListener(CarouselAdapter carouselAdapter) {
        if (this.carouselViewPager.getAdapter().getCount() > 1) {
            this.carouselViewPager.setCurrentItem(1);
        }
        if (!this.isTurning) {
            startTurning(8000);
//                    setScrollDuration(carouselViewPager,5000);
        }
        this.carouselViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            private int newPosition = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset == 0.0) {
                    int count = carouselAdapter.getCount();
                    if (this.newPosition == 0) {
                        position = count - 2;
                    } else if (this.newPosition == count - 1) {
                        position = 1;
                    } else return;
                    ViewPagerScroller viewPagerScroller = replaceViewPagerScroll(carouselViewPager);
                    Log.i(TAG, "onPageScrolled: position: " + position +
                            "\tpositionOffset: " + positionOffset +
                            "\tpositionOffsetPixels: " + positionOffsetPixels);
                    if (viewPagerScroller == null) {
                        android.util.Log.e(TAG, "onPageScrolled: viewPagerScroller 为空");
                        return;
                    }
                    viewPagerScroller.setSudden(true);
                    carouselViewPager.setCurrentItem(position, true);
                    viewPagerScroller.setSudden(false);
                }
            }

            @Override
            public void onPageSelected(int position) {
                this.newPosition = position;
            }
        });
    }

    /*
    //                carousel.addOnPageChangeListener();           //页面改变监听器
//                carousel.addOnAdapterChangeListener();        //适配器更改侦听器
//                carousel.addOnAttachStateChangeListener();    //附加状态改变监听器
//                carousel.addOnLayoutChangeListener();         //布局改变监听器
//                carousel.addOnUnhandledKeyEventListener();    //未处理的键事件监听器
                carousel.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    private int newPosition = 0;
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        if (positionOffset == 0) {
                            int count = carouselAdapter.getCount();
                            if (this.newPosition == 0) {
                                position = count - 2;
                            } else if (this.newPosition == count - 1) {
                                position = 1;
                            } else return;
                            ViewPagerScroller viewPagerScroller = replaceViewPagerScroll(carousel);
                            assert viewPagerScroller != null;
//                            viewPagerScroller.setSudden(true);
                            carousel.setCurrentItem(this.newPosition);
                            carousel.page
//                            viewPagerScroller.setSudden(false);
                            Log.i(TAG, "onPageScrolled: position: " + position + " -> " + this.newPosition + "\tcount: " + count);
                        }
                        Log.i(TAG, "onPageScrolled: position: " + position + " -> " + this.newPosition + "\t positionOffset: " + positionOffset);
                    }
                    @Override
                    public void onPageSelected(int position) {
                        this.newPosition = position;
                    }
                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });
     */

    //通过反射替换掉mBannerViewPager的mScroller属性
    private ViewPagerScroller replaceViewPagerScroll(ViewPager viewPager) {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            Object object = field.get(viewPager);
            if (object instanceof ViewPagerScroller) {
                return (ViewPagerScroller) object;
            } else {
                ViewPagerScroller mScroller = new ViewPagerScroller(getContext(),
                        new AccelerateInterpolator());
                field.set(viewPager, mScroller);
                return mScroller;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置轮播图的滚动速度
     *
     * @param scrollDuration
     */
    public void setScrollDuration(ViewPager viewPager, int scrollDuration) {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            Object object = field.get(viewPager);
            if (object instanceof ViewPagerScroller) {
                ((ViewPagerScroller) object).setScrollDuration(scrollDuration);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private ViewPager carouselViewPager;
    private boolean isTurning = false;
    // 轮播间隔时间
    private long mIntervalTime = 8000;
    private Handler mTimeHandler = new Handler();
    private Runnable mTurningTask = new Runnable() {
        @Override
        public void run() {
            if (isTurning && carouselViewPager != null) {
                int page = carouselViewPager.getCurrentItem() + 1;
                carouselViewPager.setCurrentItem(page);
                mTimeHandler.postDelayed(mTurningTask, mIntervalTime);
            }
        }
    };

    /**
     * 启动轮播
     *
     * @param intervalTime 轮播间隔时间
     * @return
     */
    public void startTurning(long intervalTime) {
        isTurning = true;
        mIntervalTime = intervalTime;
        mTimeHandler.postDelayed(mTurningTask, mIntervalTime);
    }
    // endregion
}