package com.easy.easycan.publish.child.car;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.goods.detail.model.ExcellentGoodsDetailBean;
import com.easy.easycan.goods.detail.view.ExceellentGoodsDetailView;
import com.qmuiteam.qmui.widget.QMUITopBar;

/**
 * @author merlin
 * date 2019年09月21日
 * desc 发布货源的发布页。
 */
public class PublishChildCarActivity extends BaseActivity implements
    ExceellentGoodsDetailView {
  private QMUITopBar mTopBar;

  @Override protected int setLayoutId() {
    return R.layout.activity_publish_child_car;
  }

  @Override protected void initData(@Nullable Bundle savedInstanceState) {
    super.initData(savedInstanceState);
  }

  @Override protected void initView() {
    initTopBar();
  }

  private void initTopBar() {
    mTopBar = findViewById(R.id.excellent_goods_detail_title);
    mTopBar.setVisibility(View.VISIBLE);
    mTopBar.setTitle(R.string.publish_car_title);

    mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }

  @Override protected void setListener() {

  }

  @Override public void showData(ExcellentGoodsDetailBean model) {

  }
}
