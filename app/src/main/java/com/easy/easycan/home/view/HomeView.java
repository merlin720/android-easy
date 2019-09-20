package com.easy.easycan.home.view;

import com.easy.easycan.base.BaseView;
import com.easy.easycan.home.bean.HomeBannerBean;
import com.easy.easycan.home.bean.HomeNewsTitle;
import com.easy.easycan.home.news.bean.NewsListBean;
import java.util.List;

/**
 * @author merlin720
 * @date 2019-09-20
 * @mail zy44638@gmail.com
 * @description
 */
public interface HomeView extends BaseView {
  /**
   * 显示banner
   */
  void showBanner(List<HomeBannerBean> model);

  /**
   * 新闻title
   */
  void showNewsTitle(HomeNewsTitle model);

  void showNewsList(NewsListBean model);

  /**
   * 请求失败
   */
  void requestFaile();
}
