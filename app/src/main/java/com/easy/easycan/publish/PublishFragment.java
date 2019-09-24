package com.easy.easycan.publish;

import android.view.View;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseFragment;
import com.easy.easycan.publish.car.PublishChildCarFragment;
import com.easy.easycan.publish.child.PublishChildFragment;
import com.easy.easycan.publish.goods.PublishChildGoodsFragment;
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
 * @description 发布
 */
public class PublishFragment extends BaseFragment {
  @BindView(R.id.publish_view_pager) ViewPager mViewPager;
  @BindView(R.id.publish_tab) SlidingTabLayout mTabSegment;
  @Override
  protected void initView(View view) {
    ButterKnife.bind(this, view);
    initPagers();
  }

  @Override protected int getLayoutId() {
    return R.layout.fragment_publish;
  }

  private void initPagers() {
    QMUIFragmentPagerAdapter pagerAdapter =
        new QMUIFragmentPagerAdapter(getChildFragmentManager()) {
          @Override
          public QMUIFragment createFragment(int position) {
            switch (position) {
              case 0:
                return new PublishChildFragment();
              case 1:
                return new PublishChildGoodsFragment();
              case 2:
              default:
                return new PublishChildCarFragment();
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
                return "发布";
              case 1:
                return "常发货源";
              case 2:
                return "常发车源";
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
