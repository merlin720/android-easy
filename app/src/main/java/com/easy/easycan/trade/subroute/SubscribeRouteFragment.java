package com.easy.easycan.trade.subroute;

import android.view.View;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseFragment;
import com.easy.easycan.base.TabEntity;
import com.easy.easycan.publish.PublishFragment;
import com.easy.easycan.trade.nationalcar.NationalCarSupplyFragment;
import com.easy.easycan.trade.nationalgoods.NationalSoureceSupplyFragment;
import com.easy.easycan.trade.subroute.source.SourceRouteFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.arch.QMUIFragmentPagerAdapter;
import java.util.ArrayList;

/**
 * @author merlin720
 * @date 2019-09-16
 * @mail zy44638@gmail.com
 * @description 订阅路线
 */
public class SubscribeRouteFragment extends BaseFragment {

  @BindView(R.id.contentViewPager) ViewPager mViewPager;
  @BindView(R.id.tl_10) CommonTabLayout mTabSegment;

  private String[] titles = { "货源线路", "车源线路" };
  private int[] selectImg = { R.drawable.subscribe_goods_route, R.drawable.subscribe_car_route };
  private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

  @Override
  protected void initView(View view) {
    ButterKnife.bind(this, view);
    initPagers();
  }

  @Override protected int getLayoutId() {
    return R.layout.fragment_subscribe_route_goods;
  }

  private void initPagers() {
    for (int i = 0; i < titles.length; i++) {
      mTabEntities.add(new TabEntity(titles[i], selectImg[i], selectImg[i]));
    }
    mTabSegment.setTabData(mTabEntities);
    mTabSegment.setOnTabSelectListener(new OnTabSelectListener() {
      @Override public void onTabSelect(int position) {
        mViewPager.setCurrentItem(position);
      }

      @Override public void onTabReselect(int position) {

      }
    });
    mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override public void onPageSelected(int position) {
        mTabSegment.setCurrentTab(position);
      }

      @Override public void onPageScrollStateChanged(int state) {

      }
    });

    QMUIFragmentPagerAdapter pagerAdapter =
        new QMUIFragmentPagerAdapter(getChildFragmentManager()) {
          @Override
          public QMUIFragment createFragment(int position) {
            switch (position) {
              case 0:
                return new SourceRouteFragment();
              case 1:
              default:
                return new SourceRouteFragment();
            }
          }

          @Override
          public int getCount() {
            return titles.length;
          }

          @Override
          public CharSequence getPageTitle(int position) {
            return titles[position];
          }
        };
    mViewPager.setAdapter(pagerAdapter);
    //mTabSegment.setViewPager(mViewPager);
  }

  @Override
  protected boolean canDragBack() {
    return mViewPager.getCurrentItem() == 0;
  }
}
