package com.wzhx.yilan;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.wzhx.yilan.common.utils.LogUtils;
import com.wzhx.yilan.news.model.News;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private LinearLayout mFragmentContainer;
    private BottomNavigationBar mNavigationBar;
    private FragmentManager mFragmentManager;
    private NewsFragment mNewsFragment;
    private LocationFragment mLocationFragment;
    private MusicFragment mMusicFragment;
    private ReadFragment mReadFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        mFragmentContainer = (LinearLayout) findViewById(R.id.ll_fragment_container);
        mNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        LogUtils.debug("mFragmentContainer:" + mFragmentContainer + "   mNavigationBar:" + mNavigationBar);

        mNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        mNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_home_white_24dp, "新闻"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_music_note_white_24dp, "音乐"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_location_on_white_24dp, "位置"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp, "读书"))
                .setFirstSelectedPosition(0)
                .initialise();


        mNavigationBar.setTabSelectedListener(this);

        initFragment();

        setDefaultFragment();
    }

    private void initFragment() {

        mNewsFragment = new NewsFragment();
        mLocationFragment = new LocationFragment();
        mMusicFragment = new MusicFragment();
        mReadFragment = new ReadFragment();

    }

    private void setDefaultFragment() {
        mFragmentManager = getSupportFragmentManager();
        NewsFragment newsFragment = new NewsFragment();
        mFragmentManager.beginTransaction().replace(R.id.ll_fragment_container, newsFragment).commit();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        switch (position) {
            case 0:
                fragmentTransaction.replace(R.id.ll_fragment_container, mNewsFragment);
                break;
            case 1:
                fragmentTransaction.replace(R.id.ll_fragment_container, mMusicFragment);
                break;
            case 2:
                fragmentTransaction.replace(R.id.ll_fragment_container, mLocationFragment);
                break;
            case 3:
                fragmentTransaction.replace(R.id.ll_fragment_container, mReadFragment);
                break;
            default:
                fragmentTransaction.replace(R.id.ll_fragment_container, mNewsFragment);
                break;
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
