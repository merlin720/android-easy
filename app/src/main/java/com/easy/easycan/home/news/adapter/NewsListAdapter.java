package com.easy.easycan.home.news.adapter;

import android.text.Html;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.easy.easycan.R;
import com.easy.easycan.home.news.bean.NewsListBean;
import com.easy.easycan.util.img.EasyGlide;

/**
 * @author mac
 * date 2019-09-19
 * email:zy44638@gmail.com
 * desc
 */
public class NewsListAdapter extends BaseQuickAdapter<NewsListBean, BaseViewHolder> {

    public NewsListAdapter() {
        super(R.layout.news_list_item);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NewsListBean item) {
        ImageView imageView = helper.getView(R.id.news_list_item_img);
        EasyGlide.loadImage(mContext,item.getImage(),imageView);
        helper.setText(R.id.news_list_item_title,item.getTitle());
        helper.setText(R.id.news_list_item_content, Html.fromHtml(item.getContent()));
    }
}
