package com.easy.easycan.network;

import android.net.Uri;

import java.util.Locale;

/**
 * author : daiwenbo
 * e-mail : daiwwenb@163.com
 * date   : 2018/11/28
 * description   : 网络请求耗时统计
 */
public class NetWorkTransaction {
    /*统计耗时阀值*/
    public static int REQUEST_TIME = 0;

    public String request_id;
    /*请求url*/
    public String requestUri;
    /*开始时间*/
    public String startTime;
    /*结束时间*/
    public String endTime;
    /*耗时*/
    public String tookMs;

    public String netWorkType;

    /*系统*/
    public String sys = "2";
    /*城市id*/
    public String cityId;
    /*版本号*/
    public String lver;


    public static String formatByteCount(long bytes) {
        int unit = 1000;
        if (bytes < unit) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        char pre = "KMGTPE".charAt(exp - 1);
        return String.format(Locale.CANADA, "%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    public void setUrl(String url) {
        Uri uri = Uri.parse(url);
        requestUri = uri.getPath() + ((uri.getQuery() != null) ? "?" + uri.getQuery() : "");
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(request_id)
                .append(" ")
                .append(requestUri)
                .append(" ")
                .append(startTime)
                .append(" ")
                .append(endTime)
                .append(" ")
                .append(tookMs)
                .append(" ")
                .append(netWorkType)
                .append(" ")
                .append(sys)
                .append(" ")
                .append(cityId)
                .append(" ")
                .append(lver);
        return stringBuilder.toString();

    }
}
