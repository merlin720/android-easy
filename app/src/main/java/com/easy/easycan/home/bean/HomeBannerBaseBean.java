package com.easy.easycan.home.bean;

import com.easy.easycan.base.BaseModel;
import java.util.List;

/**
 * @author merlin720
 * @date 2019-09-20
 * @mail zy44638@gmail.com
 * @description
 */
public class HomeBannerBaseBean extends BaseModel {

    private List<HomeBannerBean> items;

    public List<HomeBannerBean> getItems() {
      return items;
    }

    public void setItems(List<HomeBannerBean> items) {
      this.items = items;

  }
}
