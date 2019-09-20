package com.easy.easycan.home.presenter;

import android.widget.Toast;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.easy.easycan.base.BasePresenter;
import com.easy.easycan.home.bean.HomeBannerBaseBean;
import com.easy.easycan.home.view.HomeView;
import com.easy.easycan.util.CommonUtils;
import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
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


  public void requestBannerData(){
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
            ToastUtils.show("banner 获取失败");
          }
        });

  }
}
