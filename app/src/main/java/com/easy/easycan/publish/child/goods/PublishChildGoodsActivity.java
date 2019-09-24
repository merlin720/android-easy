package com.easy.easycan.publish.child.goods;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.goods.detail.model.ExcellentGoodsDetailBean;
import com.easy.easycan.goods.detail.view.ExceellentGoodsDetailView;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.widget.QMUITopBar;
import io.reactivex.functions.Consumer;
import java.util.concurrent.TimeUnit;

/**
 * @author merlin
 * date 2019年09月21日
 * desc 发布货源的发布页。
 */
public class PublishChildGoodsActivity extends BaseActivity implements
    ExceellentGoodsDetailView {
  private QMUITopBar mTopBar;

  @Override protected int setLayoutId() {
    return R.layout.activity_publish_child_goods;
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
    mTopBar.setTitle(R.string.publish_goods_title);
    Button button = mTopBar.addRightTextButton(R.string.publish_regular_source,R.id.publish_child_goods_title_right);
    RxView.clicks(button)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(new Consumer<Object>() {
          @Override public void accept(Object o) throws Exception {

          }
        });
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
