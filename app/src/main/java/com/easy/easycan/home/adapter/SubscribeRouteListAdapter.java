package com.easy.easycan.home.adapter;

import android.content.Context;

import com.easy.easycan.R;
import com.easy.easycan.home.bean.SubscribeBean;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * @author mac
 * date 2019-09-18
 * email:zy44638@gmail.com
 * desc
 */
public class SubscribeRouteListAdapter extends CommonAdapter<SubscribeBean> {

    public SubscribeRouteListAdapter(Context context, int layoutId, List<SubscribeBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, SubscribeBean item, int position) {
        viewHolder.setText(R.id.subscribe_route_item_from,item.getFrom());
    }
}
