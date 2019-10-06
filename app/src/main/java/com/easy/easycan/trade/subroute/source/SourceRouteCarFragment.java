package com.easy.easycan.trade.subroute.source;

import android.view.LayoutInflater;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseFragment;
import com.easy.easycan.trade.subroute.source.adapter.SourceRouteAdapter;
import com.easy.easycan.trade.subroute.source.adapter.SourceRouteEmptyAdapter;
import com.easy.easycan.trade.subroute.source.bean.SourceRouteCarBean;
import com.easy.easycan.trade.subroute.source.bean.SourceRouteEmptyBean;
import java.util.ArrayList;
import java.util.List;

/**
 * @author merlin720
 * date 2019-09-30
 * email zy44638@gmail.com
 * description 车源线路
 */
public class SourceRouteCarFragment extends BaseFragment {

  private RecyclerView recyclerView;
  private View header;
  private View mEmptyView;
  private LayoutInflater layoutInflater;
  private int[] img = {
      R.drawable.subscribe_message, R.drawable.subscribe_find_source,
      R.drawable.subscribe_all_route, R.drawable.subscribe_credit_level
  };

  private String[] contents = {"有新的货源信息第一时间声音提醒您","方便随时查找常跑货源","同时可添加50条常跑线路","精品货源货主信用等级随时了解"};
  private String[] titles = { "实时声音提醒", "快速找货", "多条线路", "信用等级一目了然" };
  @Override protected int getLayoutId() {
    return R.layout.fragment_source_route;
  }

  @Override protected void initView(View view) {
    layoutInflater = LayoutInflater.from(getActivity());
    recyclerView = view.findViewById(R.id.source_route_recycler_view);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(linearLayoutManager);
    initHeader();
    initEmptyView();
    SourceRouteAdapter adapter = new SourceRouteAdapter();
    recyclerView.setAdapter(adapter);
    adapter.setEmptyView(mEmptyView);
    adapter.setHeaderView(header);
    List<SourceRouteCarBean> list = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      SourceRouteCarBean bean = new SourceRouteCarBean();
      bean.setDep("东营/烟台/德州");
      bean.setDestination("西安");
      list.add(bean);
    }
    adapter.setNewData(list);
  }

  private void initHeader() {
    header = layoutInflater.inflate(R.layout.source_route_header, recyclerView,false);
  }

  private void initEmptyView() {
    mEmptyView = layoutInflater.inflate(R.layout.source_route_empty, recyclerView,false);
    RecyclerView emptyRecycler = mEmptyView.findViewById(R.id.source_route_item_recycler);
    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    emptyRecycler.setLayoutManager(layoutManager);
    SourceRouteEmptyAdapter adapter = new SourceRouteEmptyAdapter();
    emptyRecycler.setAdapter(adapter);
    List<SourceRouteEmptyBean> str = new ArrayList<>();
    for (int i = 0; i < img.length; i++) {
      SourceRouteEmptyBean bean = new SourceRouteEmptyBean();
      bean.setImg(img[i]);
      bean.setContent(contents[i]);
      bean.setTitle(titles[i]);
      str.add(bean);
    }
    adapter.setNewData(str);
  }
}
