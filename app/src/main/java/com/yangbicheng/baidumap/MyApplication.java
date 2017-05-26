package com.yangbicheng.baidumap;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;


/**
 * Created by YangBiCheng on 2017/5/25.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
    }
}
