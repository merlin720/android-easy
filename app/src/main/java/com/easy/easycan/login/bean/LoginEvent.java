package com.easy.easycan.login.bean;

/**
 * @author merlin720
 * date 2019-10-10
 * email zy44638@gmail.com
 * description
 */
public class LoginEvent {
    public LoginEvent(int code) {
        this.code = code;
    }

    public static int LOGIN_SUCCESS = 0;

    public static int LOGOUT_SUCCESS = 1;

    public int code;
    public String avatar;
}
