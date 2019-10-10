package com.easy.easycan.login.view;

import com.easy.easycan.base.BaseView;
import com.easy.easycan.login.bean.LoginBean;

/**
 * @author merlin720
 * date 2019-10-10
 * email zy44638@gmail.com
 * description
 */
public interface LoginView extends BaseView {
    void loginSuccess(LoginBean model);
}
