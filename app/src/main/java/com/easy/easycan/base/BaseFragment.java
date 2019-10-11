package com.easy.easycan.base;

import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.easy.easycan.R;
import com.easy.easycan.home.HomeFragment;
import com.easy.easycan.view.CustomTitleBar;
import com.gyf.immersionbar.ImmersionBar;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import org.greenrobot.eventbus.EventBus;

/**
 * @author merlin720
 * @date 2019-09-16
 * @mail zy44638@gmail.com
 * @description
 */
public abstract class BaseFragment extends QMUIFragment {

    protected ImmersionBar mImmersionBar;
    /**
     * 是否注册EventBus
     * 默认不注册
     */
    protected boolean isRegisterEventBus() {
        return false;
    }
    /**
     * 是否在Activity使用沉浸式
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getActivity()).inflate(getLayoutId(), null);
        if (isRegisterEventBus()) {
            EventBus.getDefault().register(this);
        }
        initView(view);
        initData();
        setListener();
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
        return view;
    }

    protected abstract void initView(View view);

    protected abstract @LayoutRes
    int getLayoutId();

    protected void initData() {
    }

    protected void setListener() {
    }

    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(getActivity());
        mImmersionBar
                .fitsSystemWindows(true)
                .transparentStatusBar()
                .statusBarDarkFont(true, 0.2f)
                .navigationBarWithKitkatEnable(false)
                .init();
    }

    @Override
    protected int backViewInitOffset() {
        return QMUIDisplayHelper.dp2px(getContext(), 100);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public Object onLastFragmentFinish() {
        return new HomeFragment();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    protected void goToWebExplorer(@NonNull String url, @Nullable String title) {
        //Intent intent = QDMainActivity.createWebExplorerIntent(getContext(), url, title);
        //startActivity(intent);
    }


}
