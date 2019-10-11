package com.easy.easycan.network;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.blankj.utilcode.util.SPUtils;
import com.easy.easycan.util.CommonUtils;

import java.util.Map;

/**
 * @auther mac
 * @Date 2019-09-12
 * @mail zy44638@gmail.com
 * @description
 */
public class NetHelper {

    public static String TAG = "";

    /**
     * "https://fierce-cove-29863.herokuapp.com/getAllUsers/{pageNumber}"
     * "pageNumber", "0"
     *
     * @param url
     */
    public static void get(String url, Map<String, String> param, JSONObjectRequestListener jsonObjectRequestListener) {
        AndroidNetworking.get(url)
                .addQueryParameter(param)
                .addHeaders("x-api-key", SPUtils.getInstance().getString(CommonUtils.accessToken))
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(jsonObjectRequestListener);
    }

    public static ANRequest get(String url, Map<String, String> param) {
        return AndroidNetworking.get(url)
                .addQueryParameter(param)
                .addHeaders("x-api-key", SPUtils.getInstance().getString(CommonUtils.accessToken))
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build();

    }

    /**
     * "https://fierce-cove-29863.herokuapp.com/createAnUser"
     * "firstname", "Amit"
     * .addBodyParameter("lastname", "Shekhar")
     *
     * @param url
     * @param param
     * @param jsonObjectRequestListener
     */
    public static void post(String url, Map<String, String> param, JSONObjectRequestListener jsonObjectRequestListener) {
        AndroidNetworking.post(url)
                .addBodyParameter(param)
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(jsonObjectRequestListener);
    }
}
