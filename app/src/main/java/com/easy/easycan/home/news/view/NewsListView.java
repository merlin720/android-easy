package com.easy.easycan.home.news.view;

import com.easy.easycan.base.BaseView;
import com.easy.easycan.home.bean.NewsTitleBean;
import com.easy.easycan.home.news.bean.NewsListBean;
import java.util.List;

/**
 * @author merlin720
 * @date 2019-09-20
 * @mail zy44638@gmail.com
 * @description
 */
public interface NewsListView extends BaseView {

  /**
   * 新闻title
   */
  void showNewsTitle(List<NewsTitleBean> model);

  void showNewsList(NewsListBean model);
}
