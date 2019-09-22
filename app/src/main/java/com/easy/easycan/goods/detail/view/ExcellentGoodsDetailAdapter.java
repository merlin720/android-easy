package com.easy.easycan.goods.detail.view;

import androidx.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.easy.easycan.R;
import com.easy.easycan.goods.detail.model.ExcellentGoodsDetailBean;
import com.easy.easycan.goods.model.ExcellentGoodsListBean;

/**
 * @author merlin720
 * @date 2019-09-21
 * @mail zy44638@gmail.com
 * @description
 */
public class ExcellentGoodsDetailAdapter
    extends BaseQuickAdapter<ExcellentGoodsDetailBean, BaseViewHolder> {

  public ExcellentGoodsDetailAdapter() {
    super(R.layout.excellent_goods_deital_item);
  }

  @Override protected void convert(@NonNull BaseViewHolder helper, ExcellentGoodsDetailBean item) {

  }
}
