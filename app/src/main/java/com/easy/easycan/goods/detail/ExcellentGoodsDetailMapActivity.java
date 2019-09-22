package com.easy.easycan.goods.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.TruckPath;
import com.amap.api.services.route.TruckRouteRestult;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.util.CommonUtils;
import com.easy.easycan.util.TruckRouteColorfulOverLay;
import com.hjq.toast.ToastUtils;
import com.qmuiteam.qmui.widget.QMUITopBar;

/**
 * @author merlin
 * date 2019年09月21日
 * desc 地图
 */
public class ExcellentGoodsDetailMapActivity extends BaseActivity  implements
    RouteSearch.OnTruckRouteSearchListener {
  private QMUITopBar mTopBar;

  private MapView mapView;
  private RouteSearch mRouteSearch;
  private TruckRouteRestult truckRouteResult;
  private LatLonPoint mStartPoint = new LatLonPoint(39.902896,116.42792);
  private LatLonPoint mEndPoint = new LatLonPoint(39.894914,116.322062);//终点，39.995576,116.481288


  public static void goActivity(Context context, String channelId, String id) {
    Intent intent = new Intent(context, ExcellentGoodsDetailMapActivity.class);
    intent.putExtra("channelId", channelId);
    intent.putExtra("id", id);
    context.startActivity(intent);
  }

  @Override protected int setLayoutId() {
    return R.layout.activity_goods_details_map;
  }
  //初始化地图控制器对象
  AMap aMap = null;
  @Override protected void initData(@Nullable Bundle savedInstanceState) {
    super.initData(savedInstanceState);
    mapView = findViewById(R.id.map);
    mapView.onCreate(savedInstanceState);// 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。


    init();
    setfromandtoMarker();
    searchRouteResult(RouteSearch.TRUCK_AVOID_CONGESTION);
  }

  /**
   * 初始化AMap对象
   */
  private void init() {
    if (aMap == null) {
      aMap = mapView.getMap();
    }
    mRouteSearch = new RouteSearch(this);
    mRouteSearch.setOnTruckRouteSearchListener(this);


    //隐藏顶部控件
  }

  /**
   * 开始搜索路径规划方案
   */
  public void searchRouteResult( int mode) {
    if (mStartPoint == null) {
      ToastUtils.show( "定位中，稍后再试...");
      return;
    }
    if (mEndPoint == null) {
      ToastUtils.show( "终点未设置");
    }
    //showProgressDialog();
    RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
        mStartPoint, mEndPoint);


    fromAndTo.setPlateNumber("A6BN05");
    fromAndTo.setPlateProvince("京");

    // 第一个参数表示路径规划的起点和终点，第二个参数表示计算路径的模式，第三个参数表示途经点，第四个参数货车大小 必填
    RouteSearch.TruckRouteQuery query = new RouteSearch.TruckRouteQuery(fromAndTo,mode, null, RouteSearch.TRUCK_SIZE_HEAVY);

    query.setTruckAxis(6);
    query.setTruckHeight(3.9f);
    query.setTruckWidth(3);
    query.setTruckLoad(45);
    query.setTruckWeight(50);

    //异步查询
    mRouteSearch.calculateTruckRouteAsyn(query);
  }
  @Override protected void initView() {
    initTopBar();

  }

  private void setfromandtoMarker() {
    aMap.addMarker(new MarkerOptions()
        .position(CommonUtils.convertToLatLng(mStartPoint))
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow_down_gray)));
    aMap.addMarker(new MarkerOptions()
        .position(CommonUtils.convertToLatLng(mEndPoint))
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow_right)));
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mapView.onDestroy();
  }

  @Override protected void onResume() {
    super.onResume();
    mapView.onResume();
  }

  @Override protected void onPause() {
    super.onPause();
    mapView.onPause();
  }
  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
    mapView.onSaveInstanceState(outState);
  }

  private void initTopBar() {
    mTopBar = findViewById(R.id.excellent_goods_detail_title);
    mTopBar.setVisibility(View.VISIBLE);
    mTopBar.setTitle(R.string.exc_goods_detail_title);
    mTopBar.addRightImageButton(R.drawable.fenxiang, R.id.topbar_right_about_button)
        .setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View view) {
            share();
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
  /**
   * 分享
   */
  private void share() {

  }

  @Override public void onTruckRouteSearched(TruckRouteRestult result, int rCode) {
    //建议通过TruckPath中getRestriction() 判断路线上是否存在限行
    /**
     * 限行结果
     * 0，未知（未输入完整/正确车牌号信息时候显示）
     * 1，已规避限行
     * 2，起点限行
     * 3，途径点在限行区域内（设置途径点才出现此报错）
     * 4，途径限行区域
     * 5，终点限行
     */

    if (rCode == 1000) {
      if (result != null && result.getPaths() != null
          && result.getPaths().size() > 0) {
        truckRouteResult = result;
        TruckPath path = truckRouteResult.getPaths().get(0);
        if(path == null) {
          return;
        }
        aMap.clear();// 清理地图上的所有覆盖物

        TruckRouteColorfulOverLay drivingRouteOverlay = new TruckRouteColorfulOverLay(
            this, aMap, path, truckRouteResult.getStartPos(),
            truckRouteResult.getTargetPos(), null);

        drivingRouteOverlay.removeFromMap();
        drivingRouteOverlay.setIsColorfulline(true);
        drivingRouteOverlay.addToMap();
        drivingRouteOverlay.zoomToSpan();
      } else {
        ToastUtils.show(   R.string.no_result);
      }
    } else if (rCode == 1904) {
      ToastUtils.show( R.string.error_network);
    } else if (rCode == 1002) {
      ToastUtils.show(  R.string.error_key);
    } else {
      ToastUtils.show(  "结果：" + rCode );
    }
  }
}
