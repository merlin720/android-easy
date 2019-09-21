package com.easy.easycan.goods.view;

import androidx.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.easy.easycan.R;
import com.easy.easycan.goods.model.ExcellentGoodsListBean;

/**
 * @author merlin720
 * @date 2019-09-21
 * @mail zy44638@gmail.com
 * @description
 */
public class ExcellentGoodsListAdapter
    extends BaseQuickAdapter<ExcellentGoodsListBean, BaseViewHolder> {

  public ExcellentGoodsListAdapter() {
    super(R.layout.excellent_list_goods_item);
  }

  @Override protected void convert(@NonNull BaseViewHolder helper, ExcellentGoodsListBean item) {

  }
}
