package com.gx.weektwo.modle;


import android.os.AsyncTask;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.gx.weektwo.bean.News;
import com.gx.weektwo.url.UrlConnction;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginModle {

    public void  Login(String url, String name, String tel, final CallBack callBack) {

        UrlConnction urlConnction=new UrlConnction();
        /*urlConnction.execute(url + name + tel);*/
        urlConnction.CallBackData(url+name+tel,new UrlConnction.CallBack() {
            @Override
            public void SetData(String s) {
                Gson gson=new Gson();
                News news = gson.fromJson(s, News.class);
                String msg = news.getMsg();
                callBack.Setdata(msg);
            }
        });
    }
    public  interface CallBack{
       void  Setdata(String s);
    }
}
