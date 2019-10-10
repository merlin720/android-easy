package com.easy.easycan.me.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.easy.easycan.R;
import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.login.changepwd.ChangePassWordActivity;
import com.easy.easycan.me.setting.updateimg.UpdateHeadImgActivity;
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
public class SettingActivity extends BaseActivity {

  private QMUITopBar mTopBar;

  private TextView changePwdTv;

  private LinearLayout headLinear;


  @Override protected int setLayoutId() {
    return R.layout.activity_setting;
  }

  @Override protected void initData(@Nullable Bundle savedInstanceState) {
    super.initData(savedInstanceState);
  }

  @Override protected void initView() {
    initTopBar();
    changePwdTv = findViewById(R.id.setting_change_pwd_tv);
    headLinear = findViewById(R.id.setting_head_ll);
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



  @Override protected void setListener() {
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
  }
}
