package com.easy.easycan.publish.goods.adapter;

import androidx.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.easy.easycan.R;
import com.easy.easycan.goods.model.ExcellentGoodsListBean;

/**
 * @author merlin720
 * @date 2019-09-21
 * @mail zy44638@gmail.com
 * @description 常发货源适配器
 */
public class PublishChildGoodsAdapter
    extends BaseQuickAdapter<ExcellentGoodsListBean, BaseViewHolder> {

  public PublishChildGoodsAdapter() {
    super(R.layout.regular_source_shipping_item);
  }

  @Override protected void convert(@NonNull BaseViewHolder helper, ExcellentGoodsListBean item) {

  }
}
