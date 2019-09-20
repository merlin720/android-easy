package com.easy.easycan.home.presenter;

import android.widget.Toast;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.easy.easycan.base.BasePresenter;
import com.easy.easycan.home.bean.HomeBannerBaseBean;
import com.easy.easycan.home.bean.NewsTitleBaseBean;
import com.easy.easycan.home.bean.NewsTitleBean;
import com.easy.easycan.home.view.HomeView;
import com.easy.easycan.network.NetHelper;
import com.easy.easycan.util.CommonUtils;
import com.easy.easycan.util.LogUtils;
import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author merlin720
 * @date 2019-09-20
 * @mail zy44638@gmail.com
 * @description
 */
public class HomePresenter extends BasePresenter {

  private HomeView mHomeView;

  public HomePresenter(HomeView homeView) {
    this.mHomeView = homeView;
  }

  /**
   * 请求banner
   */
  public void requestBannerData() {
    AndroidNetworking.get(CommonUtils.BANNER_URL)
        .addPathParameter("name", "home")
        .setTag("test")
        .setPriority(Priority.MEDIUM)
        .build()
        .getAsJSONObject(new JSONObjectRequestListener() {
          @Override
          public void onResponse(JSONObject response) {

            try {
              String data = response.getString("data");
              Gson gson = new Gson();
              HomeBannerBaseBean bannerBaseBean = gson.fromJson(data, HomeBannerBaseBean.class);
              mHomeView.showBanner(bannerBaseBean.getItems());
            } catch (JSONException e) {
              e.printStackTrace();
            }
          }

          @Override
          public void onError(ANError error) {
            LogUtils.e(error.getErrorDetail());
            mHomeView.requestFaile();
          }
        });
  }

  public void requestNewsTitle() {
    Map<String, String> param = new HashMap<>();
    param.put("expand","channel");
    NetHelper.get(CommonUtils.NEWS_TITLE, param, new JSONObjectRequestListener() {
      @Override public void onResponse(JSONObject response) {
        String data = "";
        try {
          int errorCode = response.getInt("code");
          if (0 == errorCode) {
            data = response.getString("data");
            NewsTitleBaseBean bean = new Gson().fromJson(data, NewsTitleBaseBean.class);
            mHomeView.showNewsTitle(bean.getItems());
          } else {
            ToastUtils.show(response.getString("message"));
          }
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }

      @Override public void onError(ANError anError) {
        LogUtils.e(anError.getErrorDetail());
        ToastUtils.show("banner 获取失败");
      }
    });
  }
}