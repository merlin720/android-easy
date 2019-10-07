package com.easy.easycan.home;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.easy.easycan.MainActivity;
import com.easy.easycan.home.bean.HomeBannerBean;
import com.easy.easycan.home.bean.NewsTitleBean;
import com.easy.easycan.home.calculation.FreightCalculationActivity;
import com.easy.easycan.home.news.NewsListActivity;
import com.easy.easycan.home.news.bean.NewsListBean;
import com.easy.easycan.home.presenter.HomePresenter;
import com.easy.easycan.home.stramer.StreamerInformationActivity;
import com.easy.easycan.home.view.HomeView;
import com.easy.easycan.me.source.FindSourceActivity;
import com.easy.easycan.me.sourcecar.FindSourceCarActivity;
import com.easy.easycan.util.GlideImageLoader;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseFragment;
import com.easy.easycan.home.adapter.GridAdapter;
import com.easy.easycan.home.adapter.SubscribeRouteListAdapter;
import com.easy.easycan.home.bean.SubscribeBean;
import com.easy.easycan.home.news.adapter.NewsListPageAdapter;
import com.easy.easycan.util.CommonUtils;
import com.easy.easycan.view.InnerGridView;
import com.easy.easycan.view.InnerListView;
import com.easy.easycan.view.InnerViewPager;
import com.google.android.material.tabs.TabLayout;
import com.hjq.toast.ToastUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author merlin720
 * date 2019-09-16
 * mail zy44638@gmail.com
 * description 首页
 */
public class HomeFragment extends BaseFragment implements OnBannerListener, HomeView {

    private String[] titles = {
            "我要找货", "精品货源", "运费计算", "易冠宝",
            "我要找车", "运单管理", "蒸罐信息", "运力图"
    };
    private List<String> imgs;
    private List<String> bannerTitles;


    private Banner banner;
    /**
     * 8个tab
     */
    private InnerGridView gridView;


    /**
     * 订阅路线
     */
    private InnerListView mSubscribeRouteListView;
    private LinearLayout mSubscribeRoute;
    private TextView subscribe_route_content_go_sub;
    /**
     * 本地货源
     */
    private InnerListView mNewLocalSourceListView;
    private LinearLayout mNewLocalSourceLl;
    /**
     * 本地车源
     */
    private InnerListView mNewLocalSourceCarListView;
    private LinearLayout mNewLocalSourceCarLl;

    private LinearLayout news_list_ll;

    private TabLayout mTabLayout;

    private InnerViewPager innerViewPager;

    private HomePresenter presenter;

    private SmartRefreshLayout refreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        banner = view.findViewById(R.id.home_banner);

        initBanner();

        gridView = view.findViewById(R.id.home_grid_view);
        gridView.setVisibility(View.VISIBLE);
        gridView.setAdapter(new GridAdapter(getActivity(), titles));
        gridView.setNumColumns(4);

        mSubscribeRoute = view.findViewById(R.id.subscribe_route_title_ll);
        subscribe_route_content_go_sub = view.findViewById(R.id.subscribe_route_content_go_sub);
        mNewLocalSourceLl = view.findViewById(R.id.new_local_source_ll);
        mNewLocalSourceCarLl = view.findViewById(R.id.new_local_source_car_ll);

        mSubscribeRouteListView = view.findViewById(R.id.subscribe_route_list_view);
        mNewLocalSourceListView = view.findViewById(R.id.new_local_source_list_view);
        mNewLocalSourceCarListView = view.findViewById(R.id.new_local_source_car_list_view);

        news_list_ll = view.findViewById(R.id.news_list_ll);
        mTabLayout = view.findViewById(R.id.home_news_tl_tabs);
        innerViewPager = view.findViewById(R.id.home_news_vp_content);
        refreshLayout = view.findViewById(R.id.refreshLayout);
    }

    /**
     * 配置banner的一些参数
     */
    private void initBanner() {
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //轮播的动画效果
        banner.setBannerAnimation(Transformer.ZoomOutSlide);
        //设置轮播的时间间隔
        banner.setDelayTime(3 * 1000);
        //是否自动播放
        banner.isAutoPlay(true);
        banner.setImageLoader(new GlideImageLoader());
        banner.setOnBannerListener(HomeFragment.this);
    }

    @Override
    protected void initData() {
        presenter = new HomePresenter(this);
        presenter.requestBannerData();
        presenter.requestNewsTitle();
        imgs = new ArrayList<>();
        bannerTitles = new ArrayList<>();

        List<SubscribeBean> list = new ArrayList<>();
        SubscribeBean subscribeBean = new SubscribeBean();
        subscribeBean.setFrom("东营/烟台/德州");
        subscribeBean.setTo("西安");
        list.add(subscribeBean);
        list.add(subscribeBean);
        SubscribeRouteListAdapter adapter = new SubscribeRouteListAdapter(getActivity(), R.layout.subscribe_list_item, list);
        mSubscribeRouteListView.setAdapter(adapter);
        mNewLocalSourceListView.setAdapter(adapter);
        mNewLocalSourceCarListView.setAdapter(adapter);
    }

    @Override
    protected void setListener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), titles[i], Toast.LENGTH_LONG).show();
                switch (i) {
                    case 0:
                        startActivity(new Intent(getActivity(), FindSourceActivity.class));
                        break;
                    case 1:
                        ((MainActivity) Objects.requireNonNull(getActivity())).switchToFragment(3);
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), FreightCalculationActivity.class));

                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), FindSourceCarActivity.class));
                        break;
                    case 6:
                      startActivity(new Intent(getActivity(), StreamerInformationActivity.class));
                        break;
                }
            }
        });
        Disposable disposable = RxView.clicks(mSubscribeRoute)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ToastUtils.show("查看更多线路推荐");
                    }
                });

        Disposable disposable1 = RxView.clicks(subscribe_route_content_go_sub)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ToastUtils.show("去订阅跳转");
                    }
                });

        Disposable disposable2 = RxView.clicks(mNewLocalSourceLl)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ToastUtils.show("查看更多新线路资源");
                    }
                });
        Disposable disposable3 = RxView.clicks(mNewLocalSourceCarLl)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ToastUtils.show("查看更多新线路资源");
                    }
                });
        Disposable disposable4 = RxView.clicks(news_list_ll)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        startActivity(new Intent(getActivity(), NewsListActivity.class));
                    }
                });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.requestBannerData();
                presenter.requestNewsTitle();
            }
        });
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getActivity(), "this is the: " + position + "picture", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onStart() {
        super.onStart();
        //banner开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override public void showBanner(List<HomeBannerBean> model) {
        refreshLayout.finishRefresh();
        for (HomeBannerBean homeBannerBean : model) {
            if (!homeBannerBean.getImage().contains("http")){
                homeBannerBean.setImage(CommonUtils.IP+homeBannerBean.getImage());
            }
            imgs.add(homeBannerBean.getImage());
            bannerTitles.add(homeBannerBean.getDescription());
        }
        banner.setImages(imgs)
            .start();
    }

    @Override public void showNewsTitle(List<NewsTitleBean> model) {
        refreshLayout.finishRefresh();
        innerViewPager.setAdapter(new NewsListPageAdapter(getChildFragmentManager(), 0,model,true));
        mTabLayout.setupWithViewPager(innerViewPager);
        innerViewPager.setOffscreenPageLimit(3);
    }

    @Override public void showNewsList(NewsListBean model) {
        refreshLayout.finishRefresh();
    }

    @Override public void requestFaile() {
        refreshLayout.finishRefresh();
    }
}
