package com.example.weilong.gankio.baseui;

import android.app.Application;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by weilong on 2017/1/3.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ShareSDK.initSDK(this,"1a75e5b01fd88");
    }


    //   appKey  1a75e5b01fd88   appScaret   e04d6b7d8cf28785a6574952766b063f
}
