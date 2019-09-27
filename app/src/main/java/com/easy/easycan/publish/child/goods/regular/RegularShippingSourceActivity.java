package com.easy.easycan.publish.child.goods.regular;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.goods.detail.model.ExcellentGoodsDetailBean;
import com.easy.easycan.goods.detail.view.ExceellentGoodsDetailView;
import com.easy.easycan.publish.goods.PublishChildGoodsFragment;
import com.qmuiteam.qmui.widget.QMUITopBar;

/**
 * @author merlin
 * date 2019年09月21日
 * desc 常发货源
 */
public class RegularShippingSourceActivity extends BaseActivity {
  private QMUITopBar mTopBar;

  @Override protected int setLayoutId() {
    return R.layout.activity_regular_shipping_source;
  }

  @Override protected void initData(@Nullable Bundle savedInstanceState) {
    super.initData(savedInstanceState);
  }

  @Override protected void initView() {
    initTopBar();
    PublishChildGoodsFragment pub = new PublishChildGoodsFragment();
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.regular_shipping_source_frame_layout, pub)
        .commit();
  }

  private void initTopBar() {
    mTopBar = findViewById(R.id.publish_child_car_title);
    mTopBar.setVisibility(View.VISIBLE);
    mTopBar.setTitle(R.string.publish_regular_source);

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
