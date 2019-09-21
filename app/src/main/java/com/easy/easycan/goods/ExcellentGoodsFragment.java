package com.easy.easycan.goods;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseFragment;
import com.easy.easycan.goods.model.ExcellentGoodsListBean;
import com.easy.easycan.goods.model.JsonBean;
import com.easy.easycan.goods.view.ExcellentGoodsListAdapter;
import com.easy.easycan.util.GetJsonDataUtil;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.widget.QMUITopBar;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableContainer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;

/**
 * @author merlin720
 * @date 2019-09-16
 * @mail zy44638@gmail.com
 * @description 精品货源
 */
public class ExcellentGoodsFragment extends BaseFragment {
  private List<JsonBean> options1Items = new ArrayList<>();
  private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
  private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

  private List<String> mFiltrateData;
  private List<String> mTimeData;

  private QMUITopBar mTopBar;
  private TextView mDepartureTv;
  private TextView mDestinationTv;
  private TextView mFiltrateTv;
  private TextView mTimeTv;
  /**
   * 文字是蓝色还是黑色
   */
  private boolean isDepartureArrowUp = false;
  private boolean isDestinationArrowUp = false;
  private boolean isFiltrateArrowUp = false;
  private boolean isTimeArrowUp = false;

  private ExcellentGoodsListAdapter adapter;
  private RecyclerView mRecyclerView;

  @Override protected int getLayoutId() {
    return R.layout.fragment_excellent_goods;
  }

  @Override
  protected void initView(View view) {
    mTopBar = view.findViewById(R.id.excellent_goods_title);
    mTopBar.setTitle("精品货源");
    mTopBar.setBackgroundDividerEnabled(true);

    mDepartureTv = view.findViewById(R.id.excellent_goods_departure);
    mDestinationTv = view.findViewById(R.id.excellent_goods_destination);
    mFiltrateTv = view.findViewById(R.id.excellent_goods_filtrate);
    mTimeTv = view.findViewById(R.id.excellent_goods_time);

    mRecyclerView = view.findViewById(R.id.excellent_goods_recycler_view);
    LinearLayoutManager manager = new LinearLayoutManager(getActivity());
    mRecyclerView.setLayoutManager(manager);
    adapter = new ExcellentGoodsListAdapter();
    mRecyclerView.setAdapter(adapter);
    List<ExcellentGoodsListBean> listBeans = new ArrayList<>();
    for (int i = 0; i < 15; i++) {
      listBeans.add(new ExcellentGoodsListBean());
    }
    adapter.setNewData(listBeans);
  }

  @Override protected void initData() {
    initJsonData();
    initFiltrateData();
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
            showPickerView(3);
          }
        });
    Disposable disposable4 = RxView.clicks(mTimeTv)
        .throttleFirst(100, TimeUnit.MILLISECONDS)
        .subscribe(new Consumer<Object>() {
          @Override public void accept(Object o) throws Exception {
            changeTimeStatus();
            showPickerView(4);
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

  private void changeTimeStatus() {
    isTimeArrowUp = !isTimeArrowUp;
    mTimeTv.setTextColor(getActivity().getResources()
        .getColor(isTimeArrowUp ? R.color.standard_blue : R.color.text_color));
    mTimeTv.setCompoundDrawablesWithIntrinsicBounds(0, 0,
        isTimeArrowUp ? R.drawable.arrow_up_blue : R.drawable.arrow_down_gray, 0);
  }

  /**
   * 筛选的数据
   */
  private void initFiltrateData() {
    mFiltrateData = new ArrayList<>();
    mFiltrateData.add("全部");
    mFiltrateData.add("抢单");
    mFiltrateData.add("竞价");

    mTimeData = new ArrayList<>();
    mTimeData.add("全部");
    mTimeData.add("1天内");
    mTimeData.add("3天内");
    mTimeData.add("7天内");
    mTimeData.add("本月");
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
                changeDepartureStatus();
                break;
              case 2:
                mDestinationTv.setText(opt3tx);
                changeDestinationStatus();
                break;
              case 3:
                mFiltrateTv.setText(mFiltrateData.get(options1));
                changeFiltrateStatus();
                break;
              case 4:
                mTimeTv.setText(mTimeData.get(options1));
                changeTimeStatus();
                break;
            }
          }
        })

            .setTitleText("城市选择")
            .setDividerColor(Color.BLACK)
            .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
            .setContentTextSize(20)
            .build();

    //pvOptions.setPicker(options1Items, options2Items);//二级选择器
    if (type == 1 || type == 2) {
      pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
    } else if (type == 3) {
      pvOptions.setPicker(mFiltrateData);//一级选择器
    } else {
      pvOptions.setPicker(mTimeData);
    }

    pvOptions.show();
  }
}
