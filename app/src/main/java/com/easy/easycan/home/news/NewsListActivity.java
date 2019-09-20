package com.easy.easycan.home.news;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.easy.easycan.R;
import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.home.bean.NewsTitleBean;
import com.easy.easycan.home.news.adapter.NewsListPageAdapter;
import com.easy.easycan.home.news.bean.NewsListBean;
import com.easy.easycan.home.news.presenter.NewsListPresenter;
import com.easy.easycan.home.news.view.NewsListView;
import com.google.android.material.tabs.TabLayout;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import java.util.List;

public class NewsListActivity extends BaseActivity implements NewsListView {

  private TabLayout mTabLayout;
  private ViewPager mViewPager;
  private QMUITopBarLayout mTopBar;

  private NewsListPresenter presenter;

  @Override
  protected int setLayoutId() {
    return R.layout.activity_news_list;
  }

  @Override
  protected void initView() {
    mTabLayout = findViewById(R.id.tl_tabs);
    mViewPager = findViewById(R.id.vp_content);

    initTopBar();
  }

  private void initTopBar() {
    mTopBar = findViewById(R.id.news_list_title);
    mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });

    mTopBar.setTitle("化工新闻");
  }

  @Override
  protected void initData(@Nullable Bundle savedInstanceState) {
    super.initData(savedInstanceState);
    initDate();
  }

  private void initDate() {
    presenter = new NewsListPresenter(this);
    presenter.requestNewsTitle();
  }

  @Override
  protected void setListener() {

  }

  @Override public void showNewsTitle(List<NewsTitleBean> model) {
    mViewPager.setAdapter(new NewsListPageAdapter(getSupportFragmentManager(), 0, model));
    mTabLayout.setupWithViewPager(mViewPager);
    mViewPager.setOffscreenPageLimit(3);
  }

  @Override public void showNewsList(NewsListBean model) {

  }
}
