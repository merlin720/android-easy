package com.easy.easycan.util;

import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/5/12.
 */

public class ClickUtils {

    /**
     * 防止重复点击
     * @param view
     * @param time  时间 秒
     * @param callback
     */
    public static Observable<Object> click(View view, int time, final ClickCallback callback){
         Observable<Object> observable =  RxView.clicks(view);
         observable.throttleFirst(time, TimeUnit.MILLISECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        callback.onClick();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
         return observable;
    }

    /**
     * 时间间隔
     */
    private static final int TIME_INTERVAL = 300;

    public static Observable<Object> click(View view, ClickCallback callback){
        return click(view,TIME_INTERVAL,callback);
    }



}
