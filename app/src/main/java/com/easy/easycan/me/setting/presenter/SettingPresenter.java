package com.easy.easycan.me.setting.presenter;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.blankj.utilcode.util.SPUtils;
import com.easy.easycan.base.BasePresenter;
import com.easy.easycan.me.setting.view.SettingView;
import com.easy.easycan.network.NetHelper;
import com.easy.easycan.util.CommonUtils;
import com.hjq.toast.ToastUtils;

import org.json.JSONException;
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
                String data = "";
                try {
                    int errorCode = response.getInt("code");
                    if (0 == errorCode) {
                        settingView.logoutSuccess();
                    } else {
                        ToastUtils.show(response.getString("message"));
                        settingView.logoutError();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {
                settingView.logoutError();
            }
        });
    }
}
