package com.mimi.Network;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wangxingjian on 2017/5/23.
 */

public abstract class SingleAPIHepler<T> extends CommonAPIHelper<T> {
    public SingleAPIHepler(Context context) {
        super(context);
    }

    public SingleAPIHepler() {
    }

    public void excuteRxJava(Observable<T> observable){
        excuteRxJava(observable, Schedulers.newThread(), AndroidSchedulers.mainThread());
    }

    public void excuteRxJava(Observable<T> observable, Scheduler Sub_scheduler, Scheduler Obs_scheduler){
        observable
                .subscribeOn(Sub_scheduler)
                .observeOn(Obs_scheduler)
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        doSubscribe(d);
                    }

                    @Override
                    public void onNext(T value) {
                        doNext(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        doError(e);
                    }

                    @Override
                    public void onComplete() {
                        doCompleted();
                    }
                });
    }
}
