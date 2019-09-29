package com.easy.easycan.me.managecar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.goods.model.ExcellentGoodsListBean;
import com.easy.easycan.me.managecar.adapter.CarManageAdapter;
import com.easy.easycan.util.LinearItemDecoration;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
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
 * desc 车辆管理
 */
public class CarManagementActivity extends BaseActivity {

  private CarManageAdapter adapter;
  private RecyclerView mRecyclerView;
  private QMUITopBar mTopBar;

  @Override protected int setLayoutId() {
    return R.layout.activity_car_manage;
  }

  @Override protected void initData(@Nullable Bundle savedInstanceState) {
    super.initData(savedInstanceState);
  }

  @Override protected void initView() {
    initTopBar();
    mRecyclerView =  findViewById(R.id.car_manage_recycler_view);
    LinearLayoutManager manager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(manager);
    adapter = new CarManageAdapter();
    mRecyclerView.setAdapter(adapter);

    List<ExcellentGoodsListBean> listBeans = new ArrayList<>();
    for (int i = 0; i < 15; i++) {
      listBeans.add(new ExcellentGoodsListBean());
    }
    adapter.setNewData(listBeans);
  }

  private void initTopBar() {
    mTopBar = findViewById(R.id.publish_child_car_title);
    mTopBar.setVisibility(View.VISIBLE);
    mTopBar.setTitle(R.string.manage_car);
    Button button = mTopBar.addRightTextButton(R.string.add_new_car,
        QMUIViewHelper.generateViewId());
    Disposable disposable = RxView.clicks(button)
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


}
