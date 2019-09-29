package com.easy.easycan.publish.child.bulk;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.goods.detail.model.ExcellentGoodsDetailBean;
import com.easy.easycan.goods.detail.view.ExceellentGoodsDetailView;
import com.easy.easycan.publish.child.car.PublishChildCarActivity;
import com.easy.easycan.util.AreaPickerUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.widget.QMUITopBar;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author merlin
 * date 2019年09月21日
 * desc 批量发布的发布页。
 */
public class PublishChildBulkActivity extends BaseActivity implements
    ExceellentGoodsDetailView {

  LinearLayout inputLinear;

  private QMUITopBar mTopBar;
  /**
   * 装货地
   */
  private TextView mDepartureTv;
  private TextView mDestinationTv;
  private TextView mBulkCommitTv;
  private TextView carType;

  private LinearLayout intelligentEntryLl;

  private TextView intelligentClear;
  private TextView intelligentIdentify;
  private EditText intelligentInputEt;
  int height;

  @Override protected int setLayoutId() {
    return R.layout.activity_publish_child_bulk;
  }

  @Override protected void initData(@Nullable Bundle savedInstanceState) {
    super.initData(savedInstanceState);
  }

  @Override protected void initView() {
    initTopBar();
    initSelectData();
    inputLinear = findViewById(R.id.publish_bulk_input_ll);
    mDepartureTv = findViewById(R.id.publish_child_goods_dep);
    mDestinationTv = findViewById(R.id.publish_child_goods_destination);
    mBulkCommitTv = findViewById(R.id.publish_bulk_commit_btn);
    intelligentEntryLl = findViewById(R.id.bulk_intelligent_entry_ll);
    intelligentClear = findViewById(R.id.publish_bulk_intelligent_clear);
    intelligentInputEt = findViewById(R.id.publish_bulk_intelligent_input_et);
    carType = findViewById(R.id.publish_bulk_car_type_tv);
    AreaPickerUtils.getInstance().initJsonData(this);
    inputLinear.postDelayed(new Runnable() {
      @Override public void run() {
        height = inputLinear.getHeight();
      }
    }, 200);
  }

  private void initTopBar() {
    mTopBar = findViewById(R.id.publish_child_bulk_title);
    mTopBar.setVisibility(View.VISIBLE);
    mTopBar.setTitle(R.string.publish_bulk_source);

    mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }

  boolean upOrDown = false;

  @Override protected void setListener() {

    Disposable disposable = RxView.clicks(intelligentEntryLl)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(new Consumer<Object>() {
          @Override public void accept(Object o) throws Exception {
            upOrDown = !upOrDown;
            ObjectAnimator.ofFloat(inputLinear, "translationY", upOrDown ? -height : 0)
                .setDuration(1000)
                .start();
            ObjectAnimator.ofFloat(intelligentEntryLl, "translationY", upOrDown ? -height : 0)
                .setDuration(1000)
                .start();
          }
        });

    RxView.clicks(intelligentClear)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(new Consumer<Object>() {
          @Override public void accept(Object o) throws Exception {
            intelligentInputEt.getText().clear();
          }
        });

    Disposable disposable2 = RxView.clicks(mDestinationTv)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> AreaPickerUtils.getInstance().showPickerView(PublishChildBulkActivity.this,
            null, commitStr -> {
              mDestinationTv.setText(commitStr);
              mDestinationTv.setTextColor(
                  ContextCompat.getColor(PublishChildBulkActivity.this, R.color.text_color));
            }));

    Disposable disposable1 = RxView.clicks(mDepartureTv)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> AreaPickerUtils.getInstance().showPickerView(PublishChildBulkActivity.this,
            null, commitStr -> {
              mDepartureTv.setText(commitStr);
              mDepartureTv.setTextColor(
                  ContextCompat.getColor(PublishChildBulkActivity.this, R.color.text_color));
            }));
    Disposable disposable3 = RxView.clicks(carType)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> {
          OptionsPickerView optionsPickerView = new OptionsPickerBuilder(this,
              new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                  carType.setText(mTimeData.get(options1));
                }
              }).build();
          optionsPickerView.setPicker(mTimeData);
          optionsPickerView.setSelectOptions(0);
          optionsPickerView.show();
        });
  }

  @Override public void showData(ExcellentGoodsDetailBean model) {

  }

  private List<String> mTimeData = new ArrayList<>();

  private void initSelectData() {
    mTimeData.add("不限");
    mTimeData.add("罐车");
    mTimeData.add("不锈钢罐车");
    mTimeData.add("铝合金罐车");
    mTimeData.add("铁罐车");
    mTimeData.add("压力罐车");
    mTimeData.add("保温罐车");
    mTimeData.add("铝罐车");
    mTimeData.add("非罐车");
    mTimeData.add("微型车");
    mTimeData.add("平板车");
    mTimeData.add("高栏车");
    mTimeData.add("集装箱式车");
    mTimeData.add("飞翼车");
  }
}
