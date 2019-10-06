package com.easy.easycan.trade.subroute.source.adapter;

import androidx.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.easy.easycan.R;
import com.easy.easycan.trade.subroute.source.bean.SourceRouteCarBean;

/**
 * @author merlin720
 * date 2019-10-05
 * email zy44638@gmail.com
 * description
 */
public class SourceRouteAdapter extends BaseQuickAdapter<SourceRouteCarBean, BaseViewHolder> {

  public SourceRouteAdapter() {
    super(R.layout.source_route_item);
  }

  @Override protected void convert(@NonNull BaseViewHolder helper, SourceRouteCarBean item) {
    helper.setText(R.id.source_route_item_destination, item.getDestination());
  }
}
