package com.easy.easycan.home.news.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseFragment;
import com.easy.easycan.home.news.NewsDetailActivity;
import com.easy.easycan.home.news.adapter.NewsListAdapter;
import com.easy.easycan.home.news.bean.NewsListBean;
import com.easy.easycan.network.NetHelper;
import com.easy.easycan.network.NetworkUtils;
import com.easy.easycan.util.CommonUtils;
import com.easy.easycan.util.LogUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.hjq.toast.ToastUtils;
import com.qmuiteam.qmui.arch.QMUIFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author mac
 * @Date 2019-09-17
 * @mail zy44638@gmail.com
 * @description
 */
public class NewsListFragment extends QMUIFragment {
  private static final String ARG_TIMELINE_TYPE = "ARG_TIMELINE_TYPE";

  private static final String CATEGORY_CODE = "category_code";

  private static final String CHANNEL_CODE = "channel_code";
  private static final String IS_HOME = "is_home";

  private String categoryCode;
  private String mChannelCode;

  private boolean isHome;

  private RecyclerView recyclerView;
  private NewsListAdapter adapter;

  protected int getLayoutId() {
    return R.layout.fragment_news_list;
  }

  public static NewsListFragment newInstance(String category_code, String channel_code,
      boolean isHome) {
    Bundle args = new Bundle();
    args.putString(CATEGORY_CODE, category_code);
    args.putString(CHANNEL_CODE, channel_code);
    args.putBoolean(IS_HOME, isHome);
    NewsListFragment fragment = new NewsListFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    categoryCode = getArguments().getString(CATEGORY_CODE);
    mChannelCode = getArguments().getString(CHANNEL_CODE);
    isHome = getArguments().getBoolean(IS_HOME);
    //
  }

  protected void initView(View view) {
    recyclerView = view.findViewById(R.id.news_list_recycler_view);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    adapter = new NewsListAdapter();
    recyclerView.setAdapter(adapter);
    recyclerView.setNestedScrollingEnabled(false);
    setListener();
  }

  private void setListener() {

    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
      @Override public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        NewsListBean model = (NewsListBean) adapter.getData().get(position);
        NewsDetailActivity.goActivity(getActivity(), model.getChannel_code(), model.getId());
      }
    });
  }

  @Override
  protected View onCreateView() {
    View view = LayoutInflater.from(getActivity()).inflate(getLayoutId(), null);
    initView(view);
    initData();
    return view;
  }

  private List<NewsListBean> mContentData;

  protected void initData() {
    String size = isHome ? "5" : "10";
    AndroidNetworking.get(CommonUtils.HOME_NEWS_LIST)
        .addQueryParameter(CATEGORY_CODE, categoryCode)
        .addQueryParameter(CHANNEL_CODE, mChannelCode)
        .addQueryParameter("pageSize", size)
        .setTag("test")
        .setPriority(Priority.MEDIUM)
        .build()
        .getAsJSONObject(new JSONObjectRequestListener() {
          @Override
          public void onResponse(JSONObject response) {
            String data = null;
            try {
              int errorCode = response.getInt("code");
              if (0 == errorCode) {
                data = response.getString("data");
                JSONObject jsonObject = new JSONObject(data);
                String item = jsonObject.getString("items");

                Gson gson = new Gson();
                Type type = new TypeToken<List<NewsListBean>>() {
                }.getType();
                List<NewsListBean> list = gson.fromJson(item, type);

                adapter.setNewData(list);
              } else {
                ToastUtils.show(response.getString("message"));
              }
            } catch (JSONException e) {
              e.printStackTrace();
            }
          }

          @Override
          public void onError(ANError error) {
            // handle error
          }
        });
  }
}
