package com.easy.easycan.util;

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

    public static final String IP = "http://192.168.1.234:8080/";

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
}
