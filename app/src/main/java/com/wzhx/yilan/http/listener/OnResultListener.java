package com.wzhx.yilan.http.listener;

/**
 * Created by wzhx on 2017/6/17.
 */

public interface OnResultListener<T> {
    void onSuccess(T t);
    void onError(Throwable e);
    void onComplete();
}
