package com.easy.easycan.trade.subroute.source.adapter;

import androidx.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.easy.easycan.R;
import com.easy.easycan.trade.subroute.source.bean.SourceRouteEmptyBean;

/**
 * @author merlin720
 * date 2019-10-05
 * email zy44638@gmail.com
 * description
 */
public class SourceRouteEmptyAdapter extends BaseQuickAdapter<SourceRouteEmptyBean, BaseViewHolder> {

  public SourceRouteEmptyAdapter() {
    super(R.layout.source_route_empty_item);
  }

  @Override protected void convert(@NonNull BaseViewHolder helper, SourceRouteEmptyBean item) {
      helper.setImageResource(R.id.source_route_empty_img, item.getImg());
      helper.setText(R.id.source_route_empty_title, item.getTitle());
      helper.setText(R.id.source_route_empty_content, item.getContent());
  }
}
