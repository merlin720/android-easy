package com.easy.easycan.me.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.login.changepwd.ChangePassWordActivity;
import com.easy.easycan.me.setting.presenter.SettingPresenter;
import com.easy.easycan.me.setting.updateimg.UpdateHeadImgActivity;
import com.easy.easycan.me.setting.view.SettingView;
import com.easy.easycan.util.CommonUtils;
import com.easy.easycan.util.img.EasyGlide;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author merlin
 * date 2019年09月21日
 * desc 我要找货。
 */
public class SettingActivity extends BaseActivity implements SettingView {

    private QMUITopBar mTopBar;

    private TextView changePwdTv;

    private LinearLayout headLinear;

    private LinearLayout logoutLl;

    private ImageView head;

    private SettingPresenter presenter;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        presenter = new SettingPresenter(this);
    }

    @Override
    protected void initView() {
        initTopBar();
        changePwdTv = findViewById(R.id.setting_change_pwd_tv);
        headLinear = findViewById(R.id.setting_head_ll);
        logoutLl = findViewById(R.id.setting_logout);
        head = findViewById(R.id.setting_head_img);
        EasyGlide.loadImage(SettingActivity.this, SPUtils.getInstance().getString(CommonUtils.AVATAR),head);
    }

    private void initTopBar() {
        mTopBar = findViewById(R.id.setting_title);
        mTopBar.setVisibility(View.VISIBLE);
        mTopBar.setTitle(R.string.setting);

        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void setListener() {
        Disposable disposable = RxView.clicks(changePwdTv)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        startActivity(new Intent(SettingActivity.this, ChangePassWordActivity.class));
                    }
                });
        Disposable disposable1 = RxView.clicks(headLinear)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        startActivity(new Intent(SettingActivity.this, UpdateHeadImgActivity.class));
                    }
                });
        Disposable disposable2 = RxView.clicks(logoutLl)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        logout();
                    }
                });
    }

    private void logout() {
        presenter.logout();
    }

    @Override
    public void logoutSuccess() {
        SPUtils.getInstance().put(CommonUtils.accessToken, "");
        SPUtils.getInstance().put(CommonUtils.AVATAR, "");
        finish();
    }

    @Override
    public void logoutError(){
        SPUtils.getInstance().put(CommonUtils.accessToken, "");
        SPUtils.getInstance().put(CommonUtils.AVATAR, "");
        finish();
    }
}
