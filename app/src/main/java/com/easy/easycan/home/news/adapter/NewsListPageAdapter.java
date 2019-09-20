package com.easy.easycan.home.news.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.easy.easycan.home.bean.NewsTitleBean;
import com.easy.easycan.home.news.fragment.HomeNewsListFragment;
import com.easy.easycan.home.news.fragment.NewsListFragment;
import com.easy.easycan.util.CommonUtils;
import java.util.List;

/**
 * @auther mac
 * @Date 2019-09-17
 * @mail zy44638@gmail.com
 * @description
 */
public class NewsListPageAdapter extends FragmentPagerAdapter {
  private final static int PAGE_COUNT = 4;
  private List<NewsTitleBean> model;
  private boolean isHome;

  public NewsListPageAdapter(@NonNull FragmentManager fm, int behavior, List<NewsTitleBean> model,boolean isHome) {
    super(fm, behavior);
    this.model = model;
    this.isHome = isHome;
  }

  @NonNull
  @Override
  public Fragment getItem(int position) {
    if (isHome)
      return HomeNewsListFragment.newInstance(model.get(position).getCode(),
          model.get(position).getChannel().getCode(),isHome);
    return NewsListFragment.newInstance(model.get(position).getCode(),
        model.get(position).getChannel().getCode(),isHome);
  }

  @Override
  public int getCount() {
    return model == null ? 0 : model.size();
  }

  @Nullable
  @Override
  public CharSequence getPageTitle(int position) {
    return model.get(position).getName();
  }
}
