package com.easy.easycan.home.news.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.easy.easycan.R;
import com.easy.easycan.base.BaseFragment;

/**
 * @auther mac
 * @Date 2019-09-17
 * @mail zy44638@gmail.com
 * @description
 */
public class NewsListFragment extends BaseFragment {
    private static final String ARG_TIMELINE_TYPE = "ARG_TIMELINE_TYPE";

    private int mType;

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_list;
    }

    public static NewsListFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(ARG_TIMELINE_TYPE, type);
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt(ARG_TIMELINE_TYPE);
    }
}
