package com.easy.easycan.network;

import java.util.concurrent.Exchanger;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * author : daiwenbo
 * e-mail : daiwwenb@163.com
 * date   : 2018/7/16
 * description   : 网络耗时 和 错误日志统计
 */

public class HttpRequestQueue {
    private LinkedBlockingDeque<ErrorBean> queue;

    private boolean isPutError;
    private boolean isPutHttpLog;
    private LinkedBlockingDeque<NetWorkTransaction> netWorkTransactions = new LinkedBlockingDeque<>(10);
    private Exchanger<LinkedBlockingDeque<NetWorkTransaction>> exchanger;

    private HttpRequestQueue() {
        isPutError = false;
        isPutHttpLog = false;
        queue = new LinkedBlockingDeque<>(20);
        netWorkTransactions.clear();
    }

    public static HttpRequestQueue getInstance() {
        return HttpRequestQueueLoader.INSTANCE;
    }

    public void setPutError(boolean putError) {
        isPutError = putError;
    }

    public void setPutHttpLog(boolean putHttpLog) {
        isPutHttpLog = putHttpLog;
    }

    public ErrorBean getErrorBean() {
        ErrorBean errorBean = null;
        try {
            errorBean = queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return errorBean;
    }

    public void putErrorBean(ErrorBean request) {
        if (!isPutError) {
            if (queue.size() > 0) {
                queue.clear();
            }
            return;
        }
        try {
            if (queue.size()>18) {
                queue.clear();
            }
            queue.put(request);
        } catch (InterruptedException e) {
            queue.clear();
            e.printStackTrace();
        }
    }

    public void getNetWorkTransaction() {
        try {
            if (null != exchanger) {
                netWorkTransactions = exchanger.exchange(netWorkTransactions);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void putNetWorkTransaction(NetWorkTransaction netWorkTransaction) {
        if (!isPutHttpLog) {
            if (netWorkTransactions.size() > 0) {
                netWorkTransactions.clear();
            }
            return;
        }

        if (netWorkTransactions.size() == 10) {
            try {
                if (null != exchanger) {
                    netWorkTransactions = exchanger.exchange(netWorkTransactions);
                }
                //Log.e("HttpRequestQueue", "putNetWorkTransaction(HttpRequestQueue.java:67)换回来:" + netWorkTransactions.size());
            } catch (InterruptedException e) {
                netWorkTransactions.clear();
                e.printStackTrace();
            }
        }
        netWorkTransactions.add(netWorkTransaction);
    }

    public void setExchanger(Exchanger<LinkedBlockingDeque<NetWorkTransaction>> exchanger) {
        this.exchanger = exchanger;
    }

    private static class HttpRequestQueueLoader {
        private static final HttpRequestQueue INSTANCE = new HttpRequestQueue();
    }
}
