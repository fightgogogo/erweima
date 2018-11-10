package com.gx.weektwo.exception;

import android.content.Context;
import android.os.Process;
import android.util.Log;

//	自定义全部异常捕获类
public class MyExcption implements Thread.UncaughtExceptionHandler{

    private Context context;
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    public static MyExcption myExcption=new MyExcption();

    public static MyExcption getInstance(){
        return myExcption;
    }

    public void init(Context context){
       this.context= context.getApplicationContext();
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void MyExcption(){

    }
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (defaultUncaughtExceptionHandler!=null){
            Log.e("错误是","---------------------------------"+e);
            defaultUncaughtExceptionHandler.uncaughtException(t,e);
        }
        else {
            Log.e("错误是","---------------------------------"+e);
            Process.killProcess(Process.myPid());
        }
    }
}
