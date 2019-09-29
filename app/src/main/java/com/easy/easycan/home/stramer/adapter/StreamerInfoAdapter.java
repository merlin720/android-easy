package com.easy.easycan.home.stramer.adapter;

import androidx.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.easy.easycan.R;
import com.easy.easycan.goods.model.ExcellentGoodsListBean;
import com.easy.easycan.home.stramer.bean.StreamerInfoBean;
import java.util.List;

/**
 * @author merlin720
 * date 2019-09-21
 * mail zy44638@gmail.com
 * description 蒸罐信息
 */
public class StreamerInfoAdapter
    extends BaseQuickAdapter<StreamerInfoBean, BaseViewHolder> {

  public StreamerInfoAdapter() {
    super(R.layout.car_manage_item);
  }

  @Override protected void convert(@NonNull BaseViewHolder helper, StreamerInfoBean item) {

  }
}
