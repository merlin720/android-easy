package com.easy.easycan.me.managecar.adapter;

import androidx.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.easy.easycan.R;
import com.easy.easycan.goods.model.ExcellentGoodsListBean;

/**
 * @author merlin720
 * date 2019-09-21
 * mail zy44638@gmail.com
 * description 车辆管理适配器
 */
public class CarManageAdapter
    extends BaseQuickAdapter<ExcellentGoodsListBean, BaseViewHolder> {

  public CarManageAdapter() {
    super(R.layout.car_manage_item);
  }

  @Override protected void convert(@NonNull BaseViewHolder helper, ExcellentGoodsListBean item) {

  }
}
