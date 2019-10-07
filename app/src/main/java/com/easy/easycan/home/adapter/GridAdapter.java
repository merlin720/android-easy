package com.easy.easycan.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.easy.easycan.R;

import java.util.List;

/**
 * @auther mac
 * @Date 2019-09-17
 * @mail zy44638@gmail.com
 * @description
 */
public class GridAdapter extends BaseAdapter {

    private int[] imgs;
   private String[] titles;

    private Context mContext;

    public GridAdapter(Context context,String[] titles,int[] imgs) {
        this.mContext = context;
        this.titles = titles;
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int i) {
        return titles[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder = null;
        if (null == view) {
            holder = new Holder();
            view = LayoutInflater.from(mContext).inflate(R.layout.home_grid_item, viewGroup, false);
            holder.imageView = view.findViewById(R.id.home_grid_img);
            holder.textView = view.findViewById(R.id.home_grid_tv);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        holder.imageView.setImageResource(imgs[i]);
        holder.textView.setText(titles[i]);
        return view;
    }

    class Holder {
        ImageView imageView;
        TextView textView;
    }
}
