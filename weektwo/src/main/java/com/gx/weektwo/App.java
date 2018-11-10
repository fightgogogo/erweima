package com.gx.weektwo;

import android.app.Application;

import com.gx.weektwo.exception.MyExcption;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //自定义全局捕获异常类
        MyExcption.getInstance().init(this);

    }
}
