package com.easy.easycan.me;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.easy.easycan.MainActivity;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseFragment;
import com.easy.easycan.home.adapter.GridAdapter;
import com.easy.easycan.home.calculation.FreightCalculationActivity;
import com.easy.easycan.home.stramer.StreamerInformationActivity;
import com.easy.easycan.me.setting.SettingActivity;
import com.easy.easycan.me.source.FindSourceActivity;
import com.easy.easycan.me.sourcecar.FindSourceCarActivity;
import com.easy.easycan.view.InnerGridView;
import com.gyf.immersionbar.ImmersionBar;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author merlin720
 * date 2019-09-16
 * mail zy44638@gmail.com
 * description 我的
 */
public class ProfileFragment extends BaseFragment {
    private QMUITopBar mTopBar;
    protected ImmersionBar mImmersionBar;
    /**
     * 8个tab
     */
    private InnerGridView gridView;
    private ImageView headerImg;
    private TextView checkInfoTv;
    private TextView bottomSettingTv;
    private String[] titles = {
            "我的货源", "我的车源", "车辆管理", "我的足迹",
            "报价历史", "抢单历史", "蒸罐查询", "运费计算"
    };
    private int[] imgs = {
            R.drawable.wodehuoyuan_profile, R.drawable.wodecheyuan_profile, R.drawable.cheliangguanli_profile, R.drawable.wdeozhuji_profile,
            R.drawable.wodebaojia_profile, R.drawable.wodeqiangdan_profile, R.drawable.zhengguanchaxun_profile, R.drawable.yunweijisuan_profile
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initView(View view) {
        initTopBar(view);


        gridView = view.findViewById(R.id.profile_grid_view);
        gridView.setVisibility(View.VISIBLE);
        gridView.setAdapter(new GridAdapter(getActivity(), titles, imgs));
        gridView.setNumColumns(4);
        headerImg = view.findViewById(R.id.profile_head_img);
        checkInfoTv = view.findViewById(R.id.profile_head_check_info);
        bottomSettingTv = view.findViewById(R.id.profile_bottom_setting);
    }

    private void initTopBar(View view) {
        mTopBar = view.findViewById(R.id.profile_title);
        mTopBar.setVisibility(View.VISIBLE);
        mTopBar.setTitle(R.string.profile);
        mTopBar.setBackgroundDividerEnabled(false);
        QMUIAlphaImageButton imageButton =
                mTopBar.addRightImageButton(R.drawable.profile_setting, QMUIViewHelper.generateViewId());
        QMUIAlphaImageButton imageButton2 =
                mTopBar.addRightImageButton(R.drawable.profile_setting, QMUIViewHelper.generateViewId());
        Disposable disposable = RxView.clicks(imageButton)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        startActivity(new Intent(getActivity(), SettingActivity.class));
                    }
                });
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(getActivity());
        mImmersionBar
                .fitsSystemWindows(true)
                .statusBarColor(R.color.standard_blue)
                .statusBarDarkFont(false, 0.2f)
                .navigationBarWithKitkatEnable(false)
                .init();
    }

    @Override
    protected void setListener() {
        Disposable disposable = RxView.clicks(headerImg)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        startActivity(new Intent(getActivity(), SettingActivity.class));
                    }
                });
        Disposable disposable1 = RxView.clicks(checkInfoTv)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        startActivity(new Intent(getActivity(), SettingActivity.class));
                    }
                });
        Disposable disposable2 = RxView.clicks(bottomSettingTv)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        startActivity(new Intent(getActivity(), SettingActivity.class));
                    }
                });
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
                    case 7:
                        startActivity(new Intent(getActivity(), FreightCalculationActivity.class));
                }
            }
        });
    }
}
