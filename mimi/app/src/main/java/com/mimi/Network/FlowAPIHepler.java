package com.mimi.Network;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Created by wangxingjian on 2017/5/23.
 */

public abstract class FlowAPIHepler<T,S> extends CommonAPIHelper<T> {

    public FlowAPIHepler() {
    }

    public FlowAPIHepler(Context context) {
        super(context);
    }

    public void excuteFlow(Observable<T> observable1, final Observable<S> observable2){
        observable1.flatMap(new Function<T, ObservableSource<S>>() {
            @Override
            public ObservableSource<S> apply(T t) throws Exception {
                return observable2;
            }
        }).subscribe(new Observer<S>() {
            @Override
            public void onSubscribe(Disposable d) {
                doSubscribe(d);
            }

            @Override
            public void onNext(S value) {

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
