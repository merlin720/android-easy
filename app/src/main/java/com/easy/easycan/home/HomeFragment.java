package com.easy.easycan.home;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.easy.easycan.GlideImageLoader;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseFragment;
import com.easy.easycan.home.adapter.GridAdapter;
import com.easy.easycan.util.CommonUtils;
import com.easy.easycan.view.InnerGridView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author merlin720
 * @date 2019-09-16
 * @mail zy44638@gmail.com
 * @description 首页
 */
public class HomeFragment extends BaseFragment implements OnBannerListener {

    private String[] titles = {
            "我要找货", "精品货源", "运费计算", "易冠宝",
            "我要找车", "运单管理", "蒸罐信息", "运力图"
    };
    private List<String> strings;
    private String img = CommonUtils.IP + "/static/banner/20190412.png";

    private Banner banner;
    private InnerGridView gridView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        banner = view.findViewById(R.id.home_banner);

        initBanner();

        gridView = view.findViewById(R.id.home_grid_view);
        gridView.setAdapter(new GridAdapter(getActivity(), titles));
        gridView.setNumColumns(4);

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
        strings = new ArrayList<>();
        strings.add(img);
        strings.add(img);
        strings.add(img);
        strings.add(img);
        strings.add(img);

        AndroidNetworking.get(CommonUtils.BANNER_URL)
                .addPathParameter("name", "home")
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        banner.setImages(strings)
                                .start();
                    }

                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(getActivity(), "banner 获取失败", Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    protected void setListener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), titles[i], Toast.LENGTH_LONG).show();
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

}
