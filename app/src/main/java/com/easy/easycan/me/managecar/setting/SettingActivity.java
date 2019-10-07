package com.easy.easycan.me.managecar.setting;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.easy.easycan.R;
import com.easy.easycan.base.BaseActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;

/**
 * @author merlin
 * date 2019年09月21日
 * desc 我要找货。
 */
public class SettingActivity extends BaseActivity {

  private QMUITopBar mTopBar;

  @Override protected int setLayoutId() {
    return R.layout.activity_setting;
  }

  @Override protected void initData(@Nullable Bundle savedInstanceState) {
    super.initData(savedInstanceState);
  }

  @Override protected void initView() {
    initTopBar();

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

  }
}
