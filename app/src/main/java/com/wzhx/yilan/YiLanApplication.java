package com.wzhx.yilan;

import android.app.Application;
import android.content.Context;

/**
 * Created by wzhx on 2017/6/17.
 */

public class YiLanApplication extends Application {
    private static YiLanApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static YiLanApplication getApplication() {
        return mContext;
    }
}
