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
import com.easy.easycan.R;
import com.easy.easycan.base.BaseFragment;
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

    private int mType;

    private RecyclerView recyclerView;
    private NewsListAdapter adapter;


    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.news_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new NewsListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getActivity()).inflate(getLayoutId(),null);
        initView(view);
        initData();
        return view;
    }


    protected void initData() {

        AndroidNetworking.get(CommonUtils.HOME_NEWS_LIST)
                .addQueryParameter("category_code", "wuliu")
                .addQueryParameter("channel_code", "zixun")
                .addQueryParameter("limit", "5")
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String data = null;
                        try {
                            data = response.getString("data");
                            JSONObject jsonObject = new JSONObject(data);
                            String item =  jsonObject.getString("items");

                            Gson gson = new Gson();
                            Type type = new TypeToken<List<NewsListBean>>(){}.getType();
                            List<NewsListBean> list = gson.fromJson(item,type);

                            adapter.setNewData(list);
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


    protected int getLayoutId() {
        return R.layout.fragment_news_list;
    }

    public static NewsListFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(ARG_TIMELINE_TYPE, type);
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt(ARG_TIMELINE_TYPE);


//
    }


}
