package com.easy.easycan.home;

import android.content.Intent;
import android.view.View;

import com.easy.easycan.R;
import com.easy.easycan.base.BaseFragment;
import com.easy.easycan.home.news.NewsListActivity;

/**
 * @author merlin720
 * @date 2019-09-16
 * @mail zy44638@gmail.com
 * @description 首页
 */
public class HomeFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;

    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.home_fragment_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NewsListActivity.class));
            }
        });
    }


}
