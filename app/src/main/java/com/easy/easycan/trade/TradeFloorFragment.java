package com.easy.easycan.trade;

import android.view.View;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseFragment;
import com.easy.easycan.trade.nationalcar.NationalCarSupplyFragment;
import com.easy.easycan.trade.nationalgoods.NationalSoureceSupplyFragment;
import com.easy.easycan.trade.subroute.SubscribeRouteFragment;
import com.flyco.tablayout.SlidingTabLayout;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.arch.QMUIFragmentPagerAdapter;

/**
 * @author merlin720
 * @date 2019-09-16
 * @mail zy44638@gmail.com
 * @description 交易大厅
 */
public class TradeFloorFragment extends BaseFragment {

  @BindView(R.id.contentViewPager) ViewPager mViewPager;
  @BindView(R.id.tl_10) SlidingTabLayout mTabSegment;

  @Override
  protected void initView(View view) {
    ButterKnife.bind(this, view);
    initPagers();
  }

  @Override protected int getLayoutId() {
    return R.layout.fragment_trade;
  }

  private void initPagers() {
    QMUIFragmentPagerAdapter pagerAdapter =
        new QMUIFragmentPagerAdapter(getChildFragmentManager()) {
          @Override
          public QMUIFragment createFragment(int position) {
            switch (position) {
              case 0:
                return new SubscribeRouteFragment();
              case 1:
                return new NationalSoureceSupplyFragment();
              case 2:
              default:
                return new NationalCarSupplyFragment();
            }
          }

          @Override
          public int getCount() {
            return 3;
          }

          @Override
          public CharSequence getPageTitle(int position) {
            switch (position) {
              case 0:
                return "订阅路线";
              case 1:
                return "全国货源";
              case 2:
                return "全国车源";
              default:
                return "test";
            }
          }
        };
    mViewPager.setAdapter(pagerAdapter);
    mTabSegment.setViewPager(mViewPager);
  }

  @Override
  protected boolean canDragBack() {
    return mViewPager.getCurrentItem() == 0;
  }
}
