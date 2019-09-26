package com.easy.easycan.publish.child;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseFragment;
import com.easy.easycan.publish.child.bulk.PublishChildBulkActivity;
import com.easy.easycan.publish.child.car.PublishChildCarActivity;
import com.easy.easycan.publish.child.goods.PublishChildGoodsActivity;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.functions.Consumer;
import java.util.concurrent.TimeUnit;

/**
 * @author merlin720
 * date 2019-09-16
 * mail zy44638@gmail.com
 * description 发布-
 */
public class PublishChildFragment extends BaseFragment {

  @BindView(R.id.publish_child_publish_goods) RelativeLayout publishGoods;
  @BindView(R.id.publish_child_publish_car) RelativeLayout publishCar;
  @BindView(R.id.publish_child_publish_bulk) RelativeLayout publishBulk;

  @Override protected int getLayoutId() {
    return R.layout.publish_child_fragment;
  }

  @Override
  protected void initView(View view) {
    ButterKnife.bind(this, view);
  }

  @Override protected void setListener() {

    RxView.clicks(publishGoods)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(new Consumer<Object>() {
          @Override public void accept(Object o) throws Exception {
            goPublishGoods();
          }
        });
    RxView.clicks(publishCar)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(new Consumer<Object>() {
          @Override public void accept(Object o) throws Exception {
            goPublishCar();
          }
        });
    RxView.clicks(publishBulk)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(new Consumer<Object>() {
          @Override public void accept(Object o) throws Exception {
            goPublishBulk();
          }
        });
  }

  /**
   * 跳转发布货源
   */
  private void goPublishGoods() {
    startActivity(new Intent(getActivity(), PublishChildGoodsActivity.class));
  }
  /**
   * 跳转发布车源
   */
  private void goPublishCar() {
    startActivity(new Intent(getActivity(), PublishChildCarActivity.class));
  }

  /**
   * 跳转批量发布
   */
  private void goPublishBulk() {
    startActivity(new Intent(getActivity(), PublishChildBulkActivity.class));
  }
}
