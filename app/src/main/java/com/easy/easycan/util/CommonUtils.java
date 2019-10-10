package com.easy.easycan.util;

import android.text.TextUtils;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.blankj.utilcode.util.SPUtils;
import com.easy.easycan.BuildConfig;


import java.text.DecimalFormat;

/**
 * @author merlin720
 * @date 2019-07-23
 * @mail zy44638@gmail.com
 * @description
 */
public class CommonUtils {
    public static final boolean isDebug = !BuildConfig.ISRELEASE;

    public static final String IP = "http://www.huagongwuliu.com";
    public static final String M_IP = "http://m.huagongwuliu.com";

    //banner
    public static final String BANNER_URL = IP + "/api/system-banner/index";

    public static final String NEWS_TITLE = IP + "/api/news/categories";
        //新闻列表
    public static final String HOME_NEWS_LIST = IP + "/api/news/index";

    public static final String HOME_NEWS_LIST_DETAIL = M_IP+"/app/news?channel_code=%s&id=%s";

    //蒸罐信息?page=1&pageSize=20
    public static final String HOME_STREAMER_INFORMATION = IP+"/api/zhengguan";

    public static final String CHANGE_PASSWORD = IP+"/api/user/password";

    public static final String LOGIN = IP+"/api/user/login";

    public static final String LOGOUT = IP+"/api/user/logout";

    public static final String GET_PROFILE = IP+"/api/user/index";

    //

    public static final String accessToken = "access_token";

    public static final int TYPE_TIMELINE_PUBLIC = 1;
    public static final int TYPE_TIMELINE_FRIEND = 2;
    public static final int TYPE_TIMELINE_MINE = 3;
    public static final int TYPE_Logistics_News = 4;

    public static String getTwoPoint(String num) {
        try {
            if (null == num || "".equals(num) || "null".equals(num)) {
                return "0.00";
            }
            //定义数据格式
            DecimalFormat myformat = new DecimalFormat("#####0.00");
            double a = Double.parseDouble(num);
            return myformat.format(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getOnePoint(String num) {
        try {
            if (null == num || "".equals(num) || "null".equals(num)) {
                return "0.00";
            }
            //定义数据格式
            DecimalFormat myformat = new DecimalFormat("#####0.0");
            double a = Double.parseDouble(num);
            return myformat.format(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 把LatLonPoint对象转化为LatLon对象
     */
    public static LatLng convertToLatLng(LatLonPoint latLonPoint) {
        return new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
    }

    public static boolean isLogin(){
        return !TextUtils.isEmpty(SPUtils.getInstance().getString(CommonUtils.accessToken));
    }
}
