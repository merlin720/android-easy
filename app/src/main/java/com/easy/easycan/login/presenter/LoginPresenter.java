package com.easy.easycan.login.presenter;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.easy.easycan.base.BasePresenter;
import com.easy.easycan.login.bean.LoginBean;
import com.easy.easycan.login.view.LoginView;
import com.easy.easycan.me.setting.updateimg.bean.UploadBean;
import com.easy.easycan.network.NetHelper;
import com.easy.easycan.util.CommonUtils;
import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author merlin720
 * date 2019-10-10
 * email zy44638@gmail.com
 * description
 */
public class LoginPresenter extends BasePresenter {


    private LoginView loginView;

    public LoginPresenter(LoginView loginView){
        this.loginView = loginView;
    }

    public void login(String username,String password,String mode){
        HashMap<String,String> param = new HashMap<>();
        param.put("mode", mode);
        param.put("phone", username);
        param.put("password", password);
        NetHelper.post(CommonUtils.LOGIN, param, new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                String data = "";
                try {
                    int errorCode = response.getInt("code");
                    if (0 == errorCode) {
                        data = response.getString("data");
                        LoginBean bean = new Gson().fromJson(data, LoginBean.class);
                        loginView.loginSuccess(bean);
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
