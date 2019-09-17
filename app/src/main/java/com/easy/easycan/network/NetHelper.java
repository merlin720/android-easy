package com.easy.easycan.network;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

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
    public static void get(String url, Map<String, String> param, JSONObjectRequestListener listener) {
        AndroidNetworking.get(url)
                .addPathParameter(param)
                .addQueryParameter("limit", "3")
                .addHeaders("token", "1234")
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(listener);
    }

    /**
     * "https://fierce-cove-29863.herokuapp.com/createAnUser"
     * "firstname", "Amit"
     *      .addBodyParameter("lastname", "Shekhar")
     * @param url
     * @param param
     * @param listener
     */
    public static void post(String url,Map<String,String> param,JSONObjectRequestListener listener){
        AndroidNetworking.post(url)
                .addBodyParameter(param)
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(listener);
    }
}
