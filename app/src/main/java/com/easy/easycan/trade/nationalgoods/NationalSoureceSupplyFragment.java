package com.easy.easycan.trade.nationalgoods;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseFragment;
import com.easy.easycan.goods.model.ExcellentGoodsListBean;
import com.easy.easycan.goods.model.JsonBean;
import com.easy.easycan.trade.nationalgoods.adapter.NationalSourceSupplyAdapter;
import com.easy.easycan.util.GetJsonDataUtil;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopups;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;

/**
 * @author merlin720
 * @date 2019-09-16
 * @mail zy44638@gmail.com
 * @description 全国货源
 */
public class NationalSoureceSupplyFragment extends BaseFragment {

  private List<JsonBean> options1Items = new ArrayList<>();
  private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
  private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

  private String[] carType = {
      "不限", "罐车", "不锈钢罐车", "铝合金罐车", "铁罐车", "压力罐车", "保温罐车", "铝罐车", "非罐车", "微型车", "平板车", "高栏车",
      "集装箱式车", "飞翼车"
  };
  private String[] sourceType = {
      "一类", "二类", "三类", "四类", "五类", "六类", "七类", "八类", "九类", "普通货物"
  };

  private TextView mDepartureTv;
  private TextView mDestinationTv;
  private TextView mFiltrateTv;

  private TextView resetTv;
  private TextView submitTv;
  /**
   * 点筛选弹出的popup
   */
  private QMUIPopup mNormalPopup;
  /** 筛选里时间的radioGroup */
  private RadioGroup radioGroup;

  private TagAdapter<String> sourceCarAdapter;
  //货物类型的适配器
  private TagAdapter<String> sourceTypeAdapter;

  private Set<Integer> mCarDefaultSet = new HashSet<>();
  private Set<Integer> mSourceDefaultSet = new HashSet<>();
  private int defaultWidth;
  /**
   * 筛选框的高度
   */
  private int popupHeight;
  /**
   * 文字是蓝色还是黑色
   */
  private boolean isDepartureArrowUp = false;
  private boolean isDestinationArrowUp = false;
  private boolean isFiltrateArrowUp = false;

  private NationalSourceSupplyAdapter adapter;
  private RecyclerView mRecyclerView;

  @Override protected int getLayoutId() {
    return R.layout.fragment_national_supply;
  }

  private TagFlowLayout mFlowLayout;

  @Override
  protected void initView(View view) {
    defaultWidth = QMUIDisplayHelper.dp2px(getActivity(), 15);
    popupHeight =
        QMUIDisplayHelper.getScreenHeight(getActivity()) - QMUIDisplayHelper.dp2px(getActivity(),
            117);
    mDepartureTv = view.findViewById(R.id.excellent_goods_departure);
    mDestinationTv = view.findViewById(R.id.excellent_goods_destination);
    mFiltrateTv = view.findViewById(R.id.excellent_goods_filtrate);

    mRecyclerView = view.findViewById(R.id.excellent_goods_recycler_view);
    LinearLayoutManager manager = new LinearLayoutManager(getActivity());
    mRecyclerView.setLayoutManager(manager);
    adapter = new NationalSourceSupplyAdapter();
    mRecyclerView.setAdapter(adapter);
    List<ExcellentGoodsListBean> listBeans = new ArrayList<>();
    for (int i = 0; i < 15; i++) {
      listBeans.add(new ExcellentGoodsListBean());
    }
    adapter.setNewData(listBeans);
  }

  @Override protected void initData() {
    initJsonData();
  }

  @Override protected void setListener() {

    Disposable disposable = RxView.clicks(mDepartureTv)
        .throttleFirst(100, TimeUnit.MILLISECONDS)
        .subscribe(new Consumer<Object>() {
          @Override public void accept(Object o) throws Exception {
            changeDepartureStatus();
            showPickerView(1);
          }
        });

    Disposable disposable1 = RxView.clicks(mDestinationTv)
        .throttleFirst(100, TimeUnit.MILLISECONDS)
        .subscribe(new Consumer<Object>() {
          @Override public void accept(Object o) throws Exception {
            showPickerView(2);
            changeDestinationStatus();
          }
        });
    Disposable disposable2 = RxView.clicks(mFiltrateTv)
        .throttleFirst(100, TimeUnit.MILLISECONDS)
        .subscribe(new Consumer<Object>() {
          @Override public void accept(Object o) throws Exception {
            changeFiltrateStatus();
            showPop(mFiltrateTv);
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

  private void showPop(TextView textView) {
    View view = LayoutInflater.from(getActivity()).inflate(R.layout.national_goods_select, null);
    mFlowLayout = view.findViewById(R.id.national_goods_select_car_flow);
    List<String> list = Arrays.asList(carType);
    sourceCarAdapter = new TagAdapter<String>(list) {

      @Override
      public View getView(FlowLayout parent, int position, String s) {
        TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.tv,
            mFlowLayout, false);
        ViewGroup.LayoutParams layoutParams = tv.getLayoutParams();
        layoutParams.width = QMUIDisplayHelper.getScreenWidth(getActivity()) / 4 - defaultWidth;
        tv.setLayoutParams(layoutParams);
        tv.setText(s);
        return tv;
      }

      @Override
      public boolean setSelected(int position, String s) {
        return s.equals("Android");
      }

      @Override public void unSelected(int position, View view) {
        super.unSelected(position, view);
      }
    };
    mCarDefaultSet.add(0);
    sourceCarAdapter.setSelectedList(mCarDefaultSet);

    mFlowLayout.setAdapter(sourceCarAdapter);

    TagFlowLayout goodsFlowLayout = view.findViewById(R.id.national_goods_select_goods_flow);
    List<String> list1 = Arrays.asList(sourceType);
    sourceTypeAdapter = new TagAdapter<String>(list1) {

      @Override
      public View getView(FlowLayout parent, int position, String s) {
        TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.tv,
            goodsFlowLayout, false);
        ViewGroup.LayoutParams layoutParams = tv.getLayoutParams();
        layoutParams.width = QMUIDisplayHelper.getScreenWidth(getActivity()) / 4 - defaultWidth;
        tv.setLayoutParams(layoutParams);
        tv.setText(s);
        return tv;
      }

      @Override
      public boolean setSelected(int position, String s) {
        return s.equals("Android");
      }

      @Override public void unSelected(int position, View view) {
        super.unSelected(position, view);
      }
    };

    for (int i = 0; i < list1.size(); i++) {
      mSourceDefaultSet.add(i);
    }

    sourceTypeAdapter.setSelectedList(mSourceDefaultSet);
    goodsFlowLayout.setAdapter(sourceTypeAdapter);

    radioGroup = view.findViewById(R.id.radio_group_button);
    radioButton = view.findViewById(R.id.national_goods_select_time_one);

    resetTv = view.findViewById(R.id.national_goods_select_reset);
    submitTv = view.findViewById(R.id.national_goods_select_submit);
    RxView.clicks(resetTv)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(new Consumer<Object>() {
          @Override public void accept(Object o) throws Exception {
            resetParams();
          }
        });
    RxView.clicks(submitTv)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(new Consumer<Object>() {
          @Override public void accept(Object o) throws Exception {
            submit();
          }
        });

    mNormalPopup =
        QMUIPopups.popup(getActivity(), QMUIDisplayHelper.getScreenWidth(getContext()))
            .preferredDirection(QMUIPopup.DIRECTION_CENTER_IN_SCREEN)
            .view(view)
            .edgeProtection(0, QMUIDisplayHelper.dp2px(getActivity(), 110), 0, 0)
            .dimAmount(0.6f)
            .animStyle(QMUIPopup.ANIM_GROW_FROM_RIGHT)
            .onDismiss(new PopupWindow.OnDismissListener() {
              @Override
              public void onDismiss() {
                changeFiltrateStatus();
              }
            })
            .show(textView);
  }

  RadioButton radioButton;

  /**
   * 重置
   */
  private void resetParams() {
    sourceCarAdapter.setSelectedList(mCarDefaultSet);
    sourceCarAdapter.notifyDataChanged();
    sourceTypeAdapter.setSelectedList(mSourceDefaultSet);
    sourceTypeAdapter.notifyDataChanged();
    radioButton.setChecked(true);
  }

  /**
   * 确定
   */
  private void submit() {
    mNormalPopup.dismiss();
  }

  /**
   * 城市筛选的数据
   */
  private void initJsonData() {//解析数据

    /**
     * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
     * 关键逻辑在于循环体
     *
     * */
    String JsonData =
        new GetJsonDataUtil().getJson(getActivity(), "province.json");//获取assets目录下的json文件数据

    ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

    /**
     * 添加省份数据
     *
     * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
     * PickerView会通过getPickerViewText方法获取字符串显示出来。
     */
    options1Items = jsonBean;

    for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
      ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
      ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

      for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
        String cityName = jsonBean.get(i).getCityList().get(c).getName();
        cityList.add(cityName);//添加城市
        ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表

        //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }*/
        city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
        province_AreaList.add(city_AreaList);//添加该省所有地区数据
      }

      /**
       * 添加城市数据
       */
      options2Items.add(cityList);

      /**
       * 添加地区数据
       */
      options3Items.add(province_AreaList);
    }
  }

  public ArrayList<JsonBean> parseData(String result) {//Gson 解析
    ArrayList<JsonBean> detail = new ArrayList<>();
    try {
      JSONArray data = new JSONArray(result);
      Gson gson = new Gson();
      for (int i = 0; i < data.length(); i++) {
        JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
        detail.add(entity);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return detail;
  }

  private void showPickerView(int type) {// 弹出选择器

    OptionsPickerView pvOptions =
        new OptionsPickerBuilder(getActivity(), new OnOptionsSelectListener() {
          @Override
          public void onOptionsSelect(int options1, int options2, int options3, View v) {
            //返回的分别是三个级别的选中位置
            String opt1tx = options1Items.size() > 0 ?
                options1Items.get(options1).getPickerViewText() : "";

            String opt2tx = options2Items.size() > 0
                && options2Items.get(options1).size() > 0 ?
                options2Items.get(options1).get(options2) : "";

            String opt3tx = options2Items.size() > 0
                && options3Items.get(options1).size() > 0
                && options3Items.get(options1).get(options2).size() > 0 ?
                options3Items.get(options1).get(options2).get(options3) : "";

            String tx = opt1tx + opt2tx + opt3tx;
            switch (type) {
              case 1:
                mDepartureTv.setText(opt3tx);
                //changeDepartureStatus();
                break;
              case 2:
                mDestinationTv.setText(opt3tx);
                //changeDestinationStatus();
                break;
            }
          }
        })

            .setTitleText("城市选择")
            .setDividerColor(Color.BLACK)
            .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
            .setContentTextSize(20)
            .build();
    /**
     * 取消时改变状态
     */
    pvOptions.setOnDismissListener(new OnDismissListener() {
      @Override public void onDismiss(Object o) {
        switch (type) {
          case 1:
            changeDepartureStatus();
            break;
          case 2:
            changeDestinationStatus();
            break;
        }
      }
    });
    //pvOptions.setPicker(options1Items, options2Items);//二级选择器
    if (type == 1 || type == 2) {
      pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
    }

    pvOptions.show();
  }
}
