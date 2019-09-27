package com.easy.easycan.publish.car;

import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseFragment;
import com.easy.easycan.goods.model.ExcellentGoodsListBean;
import com.easy.easycan.publish.goods.adapter.PublishChildGoodsAdapter;
import com.easy.easycan.util.AreaPickerUtils;
import com.easy.easycan.util.LinearItemDecoration;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author merlin720
 * date 2019-09-16
 * mail zy44638@gmail.com
 * description 发布-常发车源
 */
public class PublishChildCarFragment extends BaseFragment {
  private TextView mDepartureTv;
  private TextView mDestinationTv;
  private TextView mFiltrateTv;
  /**
   * 文字是蓝色还是黑色
   */
  private boolean isDepartureArrowUp = false;
  private boolean isDestinationArrowUp = false;
  private boolean isFiltrateArrowUp = false;

  private PublishChildGoodsAdapter adapter;
  private RecyclerView mRecyclerView;


  @Override protected int getLayoutId() {
    return R.layout.fragment_publish_car_child;
  }

  @Override
  protected void initView(View view) {
    AreaPickerUtils.getInstance().initJsonData(getActivity());
    mDepartureTv = view.findViewById(R.id.excellent_goods_departure);
    mDestinationTv = view.findViewById(R.id.excellent_goods_destination);
    mFiltrateTv = view.findViewById(R.id.excellent_goods_filtrate);

    mRecyclerView = view.findViewById(R.id.excellent_goods_recycler_view);
    LinearLayoutManager manager = new LinearLayoutManager(getActivity());
    mRecyclerView.setLayoutManager(manager);
    adapter = new PublishChildGoodsAdapter();
    mRecyclerView.setAdapter(adapter);
    LinearItemDecoration
        itemDecoration = new LinearItemDecoration(QMUIDisplayHelper.dp2px(getActivity(), 10));
    //mRecyclerView.addItemDecoration(itemDecoration);
    List<ExcellentGoodsListBean> listBeans = new ArrayList<>();
    for (int i = 0; i < 15; i++) {
      listBeans.add(new ExcellentGoodsListBean());
    }
    adapter.setNewData(listBeans);
  }



  @Override protected void setListener() {
    Disposable disposable = RxView.clicks(mDepartureTv)
        .throttleFirst(100, TimeUnit.MILLISECONDS)
        .subscribe(new Consumer<Object>() {
          @Override public void accept(Object o) throws Exception {
            changeDepartureStatus();
            AreaPickerUtils.getInstance().showPickerView(getActivity(), new OnDismissListener() {
              @Override public void onDismiss(Object o) {
                changeDepartureStatus();
              }
            }, commitStr -> {
              mDepartureTv.setText(commitStr);
              mDepartureTv.setTextColor(
                  ContextCompat.getColor(getContext(), R.color.text_color));;
            });
          }
        });

    Disposable disposable1 = RxView.clicks(mDestinationTv)
        .throttleFirst(100, TimeUnit.MILLISECONDS)
        .subscribe(new Consumer<Object>() {
          @Override public void accept(Object o) throws Exception {

            changeDestinationStatus();
            AreaPickerUtils.getInstance().showPickerView(getActivity(), new OnDismissListener() {
              @Override public void onDismiss(Object o) {
                changeDestinationStatus();
              }
            }, commitStr -> {
              mDestinationTv.setText(commitStr);
              mDestinationTv.setTextColor(
                  ContextCompat.getColor(getContext(), R.color.text_color));;
            });
          }
        });
    Disposable disposable2 = RxView.clicks(mFiltrateTv)
        .throttleFirst(100, TimeUnit.MILLISECONDS)
        .subscribe(new Consumer<Object>() {
          @Override public void accept(Object o) throws Exception {
            changeFiltrateStatus();
            AreaPickerUtils.getInstance().showPickerView(getActivity(), new OnDismissListener() {
              @Override public void onDismiss(Object o) {
                changeFiltrateStatus();
              }
            }, commitStr -> {
              mFiltrateTv.setText(commitStr);
              mFiltrateTv.setTextColor(
                  ContextCompat.getColor(getContext(), R.color.text_color));;
            });
          }
        });
  }

  /**
   * 修改出发地的文字状态
   */
  private void changeDepartureStatus() {
    isDepartureArrowUp = !isDepartureArrowUp;
    mDepartureTv.setTextColor(getActivity().getResources()
        .getColor(isDepartureArrowUp ? R.color.standard_blue : R.color.text_color));
    mDepartureTv.setCompoundDrawablesWithIntrinsicBounds(0, 0,
        isDepartureArrowUp ? R.drawable.arrow_up_blue : R.drawable.arrow_down_gray, 0);
  }

  private void changeDestinationStatus() {
    isDestinationArrowUp = !isDestinationArrowUp;
    mDestinationTv.setTextColor(getActivity().getResources()
        .getColor(isDestinationArrowUp ? R.color.standard_blue : R.color.text_color));
    mDestinationTv.setCompoundDrawablesWithIntrinsicBounds(0, 0,
        isDestinationArrowUp ? R.drawable.arrow_up_blue : R.drawable.arrow_down_gray, 0);
  }

  private void changeFiltrateStatus() {
    isFiltrateArrowUp = !isFiltrateArrowUp;
    mFiltrateTv.setTextColor(getActivity().getResources()
        .getColor(isFiltrateArrowUp ? R.color.standard_blue : R.color.text_color));
    mFiltrateTv.setCompoundDrawablesWithIntrinsicBounds(0, 0,
        isFiltrateArrowUp ? R.drawable.arrow_up_blue : R.drawable.arrow_down_gray, 0);
  }
}
