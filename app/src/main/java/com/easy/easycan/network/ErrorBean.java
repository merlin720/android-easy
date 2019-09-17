package com.easy.easycan.network;

/**
 * author : daiwenbo
 * e-mail : daiwwenb@163.com
 * date   : 2018/7/16
 * description   : 错误日志实体类
 */

public class ErrorBean {
    /**
     * uid : 0
     * time : 1531304772
     * ip : 192.168.190.34
     * lat : 0.000000
     * lng : 0.000000
     * city_id : 1
     * error_type : 1
     * request_url : http:\/\/test
     * description : 测试一下
     * stack_trace : xxx
     */
    /*错误时间*/
    private long time;
    /*错误发生时的用户IP*/
    private String ip;
    /*纬度*/
    private String lat;
    /*经度*/
    private String lng;
    /*城市id*/
    private String city_id;
    /*错误类型*/
    private int error_type = 1;
    /*请求url*/
    private String request_url;
    /*错误描述*/
    private String description;
    /*堆栈信息*/
    private String stack_trace;
    /*错误名称*/
    private String ex_name;


    public String getEx_name() {
        return ex_name;
    }

    public void setEx_name(String ex_name) {
        this.ex_name = ex_name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time / 1000;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public int getError_type() {
        return error_type;
    }

    public void setError_type(int error_type) {
        this.error_type = error_type;
    }

    public String getRequest_url() {
        return request_url;
    }

    public void setRequest_url(String request_url) {
        this.request_url = request_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStack_trace() {
        return stack_trace;
    }

    public void setStack_trace(String stack_trace) {
        this.stack_trace = stack_trace;
    }
}
