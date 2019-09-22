package com.easy.easycan.goods.detail.presenter;

import com.easy.easycan.base.BasePresenter;
import com.easy.easycan.goods.detail.model.ExcellentGoodsDetailBean;
import com.easy.easycan.goods.detail.view.ExceellentGoodsDetailView;

/**
 * @author merlin720
 * @date 2019-09-22
 * @mail zy44638@gmail.com
 * @description
 */
public class ExcllentGoodsDetailPresenter extends BasePresenter {

  private ExceellentGoodsDetailView mView;

  public ExcllentGoodsDetailPresenter(ExceellentGoodsDetailView view){
    this.mView = view;
  }
  public void getData(){
    mView.showData(new ExcellentGoodsDetailBean());
  }
}
