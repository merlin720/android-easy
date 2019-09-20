package com.easy.easycan.home.news.presenter;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.easy.easycan.base.BasePresenter;
import com.easy.easycan.home.bean.NewsTitleBaseBean;
import com.easy.easycan.home.news.view.NewsListView;
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
public class NewsListPresenter extends BasePresenter {

  private NewsListView listView;

  public NewsListPresenter(NewsListView listView) {
    this.listView = listView;
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
            listView.showNewsTitle(bean.getItems());
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
