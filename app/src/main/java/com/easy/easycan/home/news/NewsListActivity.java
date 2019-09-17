package com.easy.easycan.home.news;

import android.os.Bundle;
import android.util.SparseArray;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.easy.easycan.R;
import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.goods.ExcellentGoodsFragment;
import com.easy.easycan.home.HomeFragment;
import com.easy.easycan.home.news.adapter.NewsListPageAdapter;
import com.easy.easycan.me.ProfileFragment;
import com.easy.easycan.publish.PublishFragment;
import com.easy.easycan.trade.TradeFloorFragment;
import com.google.android.material.tabs.TabLayout;

public class NewsListActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_news_list;
    }

    @Override
    protected void initView() {
        mTabLayout = findViewById(R.id.tl_tabs);
        mViewPager = findViewById(R.id.vp_content);
        mViewPager.setAdapter(new NewsListPageAdapter(getSupportFragmentManager(),0));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        initDate();
    }

    private void initDate() {

    }


    @Override
    protected void setListener() {

    }
}
