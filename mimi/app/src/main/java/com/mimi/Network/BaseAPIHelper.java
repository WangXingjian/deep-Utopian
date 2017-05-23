package com.mimi.Network;

import io.reactivex.disposables.Disposable;

/**
 * Created by wangxingjian on 2017/5/23.
 */

public interface BaseAPIHelper<T> {
    void doSubscribe(Disposable d);
    void doNext(T t);
    void doError(Throwable e);
    void doCompleted();
}
