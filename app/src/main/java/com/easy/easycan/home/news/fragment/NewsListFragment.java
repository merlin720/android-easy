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
    NewsListAdapter adapter;


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
        ToastUtils.show("ssssssssssssss");
        initView(view);
        initData();
        return view;
    }


    protected void initData() {
        ToastUtils.show("initDatainitDatainitDatainitDatainitData");


            List<NewsListBean> list = new ArrayList<>();
            NewsListBean newsListBean = new NewsListBean();
            newsListBean.setTitle("交通丨陕西G210国道城区过境线，限行货运车辆！");
            newsListBean.setContent("<section>自8月11日起榆林市公安局交警支队对四轴（含）以上重中型货运车辆通行G210国道实施限行管理。</section><section><img src=\\\"http://yiguan-main.oss-cn-beijing.aliyuncs.com/2019-08/a68e697e/3.png\\\" style=\\\"max-width:100%;\\\"><br></section><section>限行路段：G210国道城区过境线K571+800M（北至运煤专线）至K600+500M段（南至榆绥高速榆林南收费站引线十字）。<br></section><section><strong>限行车辆：</strong><strong>四轴（含）以上重中型货运车辆</strong></section><section>绕行路线：由北向南途经限行路段的限行车辆从榆神高速金鸡滩收费站入口、榆绥高速牛家梁收费站入口进入高速绕行或经绕城快速干道西段通行。</section><section>由南向北途经限行路段的限行车辆从榆绥高速榆林南收费站入口进入高速绕行或经绕城快速干道西段通行。</section><section>限行路段东部进入限行路段的限行车辆，沿榆麻路从麻黄梁收费站入口进入榆佳高速或从常乐堡进入运煤专线，经郭家伙场从绕城快速干道西段、南段绕行。</section><p><br></p>");
            newsListBean.setImage("http://yiguan-main.oss-cn-beijing.aliyuncs.com/2019-08/5d57331c/2019_7_29.jpeg");

            list.add(newsListBean);
            list.add(newsListBean);
            list.add(newsListBean);
            list.add(newsListBean);
            list.add(newsListBean);
            list.add(newsListBean);
            list.add(newsListBean);
            list.add(newsListBean);
            list.add(newsListBean);
            adapter.setNewData(list);


//        http://www.huagongwuliu.com/api/news/index?channel_code=zixun&category_code=wuliu
        HashMap<String,String> map = new HashMap<>();
        map.put("channel_code","zixun");
        map.put("category_code","wuliu");
        NetHelper.get(CommonUtils.HOME_NEWS_LIST, map, new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                // do anything with response

            }

            @Override
            public void onError(ANError anError) {

            }
        });
//        AndroidNetworking.get(CommonUtils.HOME_NEWS_LIST)
//                .addPathParameter("channel_code", "zixun")
//                .addQueryParameter("category_code", "wuliu")
//                .setTag("test")
//                .setPriority(Priority.MEDIUM)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                    }
//
//                    @Override
//                    public void onError(ANError error) {
//                        // handle error
//                    }
//                });
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
