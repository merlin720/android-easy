package com.easy.easycan.publish.nationalcar;

import android.os.Bundle;
import android.view.View;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseFragment;
import com.easy.easycan.home.news.fragment.HomeNewsListFragment;

/**
 * @author merlin720
 * @date 2019-09-16
 * @mail zy44638@gmail.com
 * @description 全国车源
 */
public class NationalCarSupplyFragment extends BaseFragment {

  public static HomeNewsListFragment newInstance(String id) {
    Bundle args = new Bundle();
    args.putString("id", id);
    HomeNewsListFragment fragment = new HomeNewsListFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  protected void initView(View view) {
    if (null != getArguments()) {
      String id = getArguments().getString("id");
    }
  }

  @Override protected int getLayoutId() {
    return R.layout.fragment_national_supply;
  }
}
