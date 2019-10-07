package com.easy.easycan.home.calculation;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.goods.model.ExcellentGoodsListBean;
import com.easy.easycan.me.managecar.adapter.CarManageAdapter;
import com.easy.easycan.publish.child.goods.PublishChildGoodsActivity;
import com.easy.easycan.util.AreaPickerUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author merlin
 * date 2019年09月21日
 * desc 运费计算
 */
public class FreightCalculationActivity extends BaseActivity {

  /**
   * 装货地
   */
  private TextView mDepartureTv;
  private TextView mDestinationTv;
  private QMUITopBar mTopBar;
  private LinearLayout resultLl;
  private TextView commitTv;


  @Override protected int setLayoutId() {
    return R.layout.activity_freight_calculation;
  }

  @Override protected void initData(@Nullable Bundle savedInstanceState) {
    super.initData(savedInstanceState);
  }

  @Override protected void initView() {
    initTopBar();
    AreaPickerUtils.getInstance().initJsonData(this);
    mDepartureTv = findViewById(R.id.publish_child_goods_dep);
    mDestinationTv = findViewById(R.id.publish_child_goods_destination);
    resultLl = findViewById(R.id.freight_calculation_result_ll);
    commitTv = findViewById(R.id.freight_commit);
  }

  private void initTopBar() {
    mTopBar = findViewById(R.id.publish_child_car_title);
    mTopBar.setVisibility(View.VISIBLE);
    mTopBar.setTitle(R.string.freight_calculation);

    mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }

  @Override protected void setListener() {
    Disposable disposable = RxView.clicks(mDestinationTv)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> AreaPickerUtils.getInstance().showPickerView(FreightCalculationActivity.this,
            null, commitStr -> {
              mDestinationTv.setText(commitStr);
              mDestinationTv.setTextColor(
                  ContextCompat.getColor(FreightCalculationActivity.this, R.color.text_color));
            }));

    Disposable disposable1 = RxView.clicks(mDepartureTv)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> AreaPickerUtils.getInstance().showPickerView(FreightCalculationActivity.this,
            null, commitStr -> {
              mDepartureTv.setText(commitStr);
              mDepartureTv.setTextColor(
                  ContextCompat.getColor(FreightCalculationActivity.this, R.color.text_color));
            }));

    Disposable disposable2 = RxView.clicks(commitTv)
        .throttleFirst(500,TimeUnit.MILLISECONDS)
        .subscribe(new Consumer<Object>() {
          @Override public void accept(Object o) throws Exception {
            if (0 == resultLl.getAlpha()) {
              ObjectAnimator objectAnimator =
                  ObjectAnimator.ofFloat(resultLl, "alpha", new float[] {
                      0f, 1f
                  });
              objectAnimator.setDuration(1000);
              objectAnimator.start();
            }

          }
        });
  }


}
