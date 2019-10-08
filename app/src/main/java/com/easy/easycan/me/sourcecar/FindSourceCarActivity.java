package com.easy.easycan.me.sourcecar;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.trade.nationalcar.NationalCarSupplyFragment;
import com.easy.easycan.trade.nationalgoods.NationalSoureceSupplyFragment;
import com.gyf.immersionbar.ImmersionBar;
import com.qmuiteam.qmui.widget.QMUITopBar;

/**
 * @author merlin
 * date 2019年09月21日
 * desc 我要找货。
 */
public class FindSourceCarActivity extends BaseActivity {

  private QMUITopBar mTopBar;

  @Override protected int setLayoutId() {
    return R.layout.activity_find_source_goods;
  }

  @Override protected void initData(@Nullable Bundle savedInstanceState) {
    super.initData(savedInstanceState);
  }
  /**
   * 初始化沉浸式
   */
  protected void initImmersionBar() {
    mImmersionBar = ImmersionBar.with(this);
    mImmersionBar
            .fitsSystemWindows(false)
            .statusBarDarkFont(true, 0.2f)
            .navigationBarWithKitkatEnable(false)
            .init();
  }
  @Override protected void initView() {
    initTopBar();
    NationalCarSupplyFragment fragment = new NationalCarSupplyFragment();
    getSupportFragmentManager().beginTransaction().replace(R.id.find_source_fl, fragment).commit();
  }

  private void initTopBar() {
    mTopBar = findViewById(R.id.find_source_title);
    mTopBar.setVisibility(View.VISIBLE);
    mTopBar.setTitle(R.string.find_car);

    mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }

  boolean upOrDown = false;

  @Override protected void setListener() {

  }
}
