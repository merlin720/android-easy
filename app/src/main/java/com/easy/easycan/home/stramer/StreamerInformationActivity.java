package com.easy.easycan.home.stramer;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.goods.model.ExcellentGoodsListBean;
import com.easy.easycan.home.bean.HomeBannerBaseBean;
import com.easy.easycan.home.stramer.adapter.StreamerInfoAdapter;
import com.easy.easycan.home.stramer.bean.StreamerInfoBaseBean;
import com.easy.easycan.me.managecar.adapter.CarManageAdapter;
import com.easy.easycan.network.NetHelper;
import com.easy.easycan.util.CommonUtils;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author merlin
 * date 2019年09月21日
 * desc 蒸罐信息
 */
public class StreamerInformationActivity extends BaseActivity {

  private StreamerInfoAdapter adapter;
  private RecyclerView mRecyclerView;
  private QMUITopBar mTopBar;
  private SmartRefreshLayout refreshLayout;
  private EditText inputEt;
  private int index = 1;

  @Override protected int setLayoutId() {
    return R.layout.activity_streamer_information;
  }

  @Override protected void initData(@Nullable Bundle savedInstanceState) {
    super.initData(savedInstanceState);
    initData(index,"");
  }

  @Override protected void initView() {
    initTopBar();
    mRecyclerView = findViewById(R.id.car_manage_recycler_view);
    refreshLayout = findViewById(R.id.refreshLayout);
    LinearLayoutManager manager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(manager);
    adapter = new StreamerInfoAdapter();
    mRecyclerView.setAdapter(adapter);
    inputEt = findViewById(R.id.streamer_info_input_et);
    //List<ExcellentGoodsListBean> listBeans = new ArrayList<>();
    //for (int i = 0; i < 15; i++) {
    //  listBeans.add(new ExcellentGoodsListBean());
    //}
    //adapter.setNewData(listBeans);
  }

  private void initTopBar() {
    mTopBar = findViewById(R.id.streamer_information_title);
    mTopBar.setVisibility(View.VISIBLE);
    mTopBar.setTitle(R.string.streamer_info);

    mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }

  @Override protected void setListener() {

    refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
      @Override public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        index++;
        initData(index,"");
      }

      @Override public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        index = 1;
        initData(index,"");
      }
    });
    inputEt.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override public void afterTextChanged(Editable s) {
          initData(index
              ,s.toString());
      }
    });
  }

  private void initData(int index,String str) {

    Map<String, String> param = new HashMap<>();
    param.put("page", String.valueOf(index));
    param.put("pageSize", "20");
    param.put("keyword", str);
    NetHelper.get(CommonUtils.HOME_STREAMER_INFORMATION, param, new JSONObjectRequestListener() {
      @Override public void onResponse(JSONObject response) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        try {
          int code = response.getInt("code");
          if (code == 0) {
            String data = response.getString("data");
            Gson gson = new Gson();
            StreamerInfoBaseBean bannerBaseBean = gson.fromJson(data, StreamerInfoBaseBean.class);
            if (index == 1) {
              adapter.setNewData(bannerBaseBean.getItems());
            }else {
              adapter.addData(bannerBaseBean.getItems());
            }
          }
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }

      @Override public void onError(ANError anError) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
      }
    });
  }
}
