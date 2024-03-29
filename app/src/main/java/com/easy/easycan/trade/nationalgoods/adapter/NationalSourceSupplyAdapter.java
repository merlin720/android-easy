package com.easy.easycan.trade.nationalgoods.adapter;

import androidx.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.easy.easycan.R;
import com.easy.easycan.goods.model.ExcellentGoodsListBean;

/**
 * @author merlin720
 * @date 2019-09-21
 * @mail zy44638@gmail.com
 * @description 全国货源适配器
 */
public class NationalSourceSupplyAdapter
    extends BaseQuickAdapter<ExcellentGoodsListBean, BaseViewHolder> {

  public NationalSourceSupplyAdapter() {
    super(R.layout.national_source_supply_item);
  }

  @Override protected void convert(@NonNull BaseViewHolder helper, ExcellentGoodsListBean item) {

  }
}
