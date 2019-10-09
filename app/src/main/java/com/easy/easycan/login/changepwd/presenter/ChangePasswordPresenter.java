package com.easy.easycan.login.changepwd.presenter;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.easy.easycan.base.BasePresenter;
import com.easy.easycan.home.bean.NewsTitleBaseBean;
import com.easy.easycan.login.changepwd.view.ChangePasswordView;
import com.easy.easycan.network.NetHelper;
import com.easy.easycan.util.CommonUtils;
import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author merlin720
 * date 2019-10-09
 * email zy44638@gmail.com
 * description
 */
public class ChangePasswordPresenter extends BasePresenter {

    private ChangePasswordView view;

    public ChangePasswordPresenter(ChangePasswordView view) {
        this.view = view;
    }

    public void commit(String password, String new_password, String re_password) {
        Map<String,String> params = new HashMap<>();
        params.put("password", password);
        params.put("new_password", new_password);
        params.put("re_password", re_password);
        NetHelper.get(CommonUtils.CHANGE_PASSWORD, params, new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                String data = "";
                try {
                    int errorCode = response.getInt("code");
                    if (0 == errorCode) {
                        data = response.getString("data");
                        view.showSuccess( );
                    } else {
                        ToastUtils.show(response.getString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }
}
