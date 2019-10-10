package com.easy.easycan.me.setting.presenter;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.easy.easycan.base.BasePresenter;
import com.easy.easycan.me.setting.view.SettingView;
import com.easy.easycan.network.NetHelper;
import com.easy.easycan.util.CommonUtils;

import org.json.JSONObject;

/**
 * @author merlin720
 * date 2019-10-10
 * email zy44638@gmail.com
 * description
 */
public class SettingPresenter extends BasePresenter {
    private SettingView settingView;

    public SettingPresenter(SettingView settingView) {
        this.settingView = settingView;
    }

    public void logout() {
        NetHelper.post(CommonUtils.LOGOUT, null, new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {

            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }
}
