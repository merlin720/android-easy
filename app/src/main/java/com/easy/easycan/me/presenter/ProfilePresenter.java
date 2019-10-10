package com.easy.easycan.me.presenter;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.easy.easycan.base.BasePresenter;
import com.easy.easycan.me.model.ProfileBaseModel;
import com.easy.easycan.me.view.ProfileView;
import com.easy.easycan.network.NetHelper;
import com.easy.easycan.util.CommonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author merlin720
 * date 2019-10-10
 * email zy44638@gmail.com
 * description
 */
public class ProfilePresenter extends BasePresenter {

    private ProfileView mView;

    public ProfilePresenter(ProfileView view) {
        this.mView = view;
    }

    public void getData() {
        Map<String, String> param = new HashMap<>();
        param.put("expand", "company,authentication");
        NetHelper.get(CommonUtils.GET_PROFILE,param).getAsObject(ProfileBaseModel.class, new ParsedRequestListener<ProfileBaseModel>() {
            @Override
            public void onResponse(ProfileBaseModel response) {
                if (response.isSuccess()) {
                    mView.showData(response.getData());
                }else {

                }
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }
}
