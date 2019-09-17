package com.easy.easycan.home.news.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.easy.easycan.home.news.fragment.NewsListFragment;
import com.easy.easycan.util.CommonUtils;

/**
 * @auther mac
 * @Date 2019-09-17
 * @mail zy44638@gmail.com
 * @description
 */
public class NewsListPageAdapter extends FragmentPagerAdapter {
    private final static int PAGE_COUNT = 4;

    public NewsListPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        int type;
        switch (position) {
            case 0:
                type = CommonUtils.TYPE_TIMELINE_PUBLIC;
                break;
            case 1:
                type = CommonUtils.TYPE_TIMELINE_FRIEND;
                break;
            case 2:
                type = CommonUtils.TYPE_TIMELINE_MINE;
                break;
            case 3:
                type = CommonUtils.TYPE_Logistics_News;
                break;
            default:
                type = 0;
                break;
        }
        return NewsListFragment.newInstance(type);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "政策法规";
            case 1:
                return "安全知识";
            case 2:
                return "化工资讯";
            case 3:
                return "物流新闻";
            default:
                return "微博";

        }
    }
}
