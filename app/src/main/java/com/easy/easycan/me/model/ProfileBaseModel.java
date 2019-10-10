package com.easy.easycan.me.model;

import com.easy.easycan.base.BaseModel;

/**
 * @author merlin720
 * date 2019-10-10
 * email zy44638@gmail.com
 * description
 */
public class ProfileBaseModel extends BaseModel {
    private ProfileModel data;

    public ProfileModel getData() {
        return data;
    }

    public void setData(ProfileModel data) {
        this.data = data;
    }
}
