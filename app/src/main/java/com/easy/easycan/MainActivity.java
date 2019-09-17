package com.easy.easycan;

import android.os.Bundle;
import android.util.SparseArray;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.goods.ExcellentGoodsFragment;
import com.easy.easycan.home.HomeFragment;
import com.easy.easycan.me.ProfileFragment;
import com.easy.easycan.publish.PublishFragment;
import com.easy.easycan.trade.TradeFloorFragment;

public class MainActivity extends BaseActivity {

    private SparseArray<Fragment> fragments;
    private HomeFragment homeFragment;
    private TradeFloorFragment tradeFloorFragment;
    private PublishFragment publishFragment;
    private ExcellentGoodsFragment excellentGoodsFragment;
    private ProfileFragment profileFragment;

    private RadioGroup mRadioGroup;

    private RadioButton radioButton;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initFragment();
        mRadioGroup = findViewById(R.id.radio_group_button);
        radioButton = findViewById(R.id.radio_button_home);

    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        initDate();
    }

    private void initDate() {

    }

    private void initFragment() {
        fragments = new SparseArray<>();
        homeFragment = new HomeFragment();
        tradeFloorFragment = new TradeFloorFragment();
        publishFragment = new PublishFragment();
        excellentGoodsFragment = new ExcellentGoodsFragment();
        profileFragment = new ProfileFragment();
        fragments.put(0, homeFragment);
        fragments.put(1, tradeFloorFragment);
        fragments.put(2, publishFragment);
        fragments.put(3, excellentGoodsFragment);
        fragments.put(4, profileFragment);
    }

    @Override
    protected void setListener() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            Fragment mFragment = null;

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio_button_home:
                        mFragment = fragments.get(0);
                        break;
                    case R.id.radio_button_discovery:
                        mFragment = fragments.get(1);
                        break;
                    case R.id.radio_button_attention:
                        mFragment = fragments.get(2);
                        break;
                    case R.id.radio_button_boutique_source:
                        mFragment = fragments.get(3);
                        break;
                    case R.id.radio_button_profile:
                        mFragment = fragments.get(4);
                        break;
                }
                if (fragments != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container, mFragment).commit();
                }

            }

        });
        // 保证第一次会回调OnCheckedChangeListener
        radioButton.setChecked(true);
    }
}
