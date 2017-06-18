package com.wzhx.yilan.http.observer;

import android.content.Context;
import android.widget.Toast;

import com.wzhx.yilan.common.utils.ToastUtils;
import com.wzhx.yilan.http.listener.OnResultListener;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by wzhx on 2017/6/17.
 */

public class DataObserver<T> implements Observer<T> {

    private Context mContext;

    private OnResultListener mOnResultListener;

    public DataObserver(Context context, OnResultListener onResultListener) {
        this.mContext = context;
        this.mOnResultListener = onResultListener;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
        mOnResultListener.onSuccess(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        mOnResultListener.onError(e);
        handlerError(e);
    }

    @Override
    public void onComplete() {
        mOnResultListener.onComplete();
    }

    private void handlerError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            ToastUtils.showToast(mContext, "网络中断", Toast.LENGTH_SHORT);
        } else if (e instanceof ConnectException) {
            ToastUtils.showToast(mContext, "网络连接超时", Toast.LENGTH_SHORT);
        } else {
            ToastUtils.showToast(mContext, "网络错误", Toast.LENGTH_SHORT);
        }
    }
}
