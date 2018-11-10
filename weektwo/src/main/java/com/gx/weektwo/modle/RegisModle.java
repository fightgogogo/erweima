package com.gx.weektwo.modle;

import com.google.gson.Gson;
import com.gx.weektwo.bean.News;
import com.gx.weektwo.bean.Regis;
import com.gx.weektwo.url.UrlConnction;

public class RegisModle {
    public void  Regis(String url, String name, String tel, final CallBack callBack) {

        UrlConnction urlConnction=new UrlConnction();
        /*urlConnction.execute(url + name + tel);*/
        urlConnction.CallBackData(url+name+tel,new UrlConnction.CallBack() {
            @Override
            public void SetData(String s) {
                Gson gson=new Gson();
                Regis regis = gson.fromJson(s, Regis.class);
                String msg = regis.getMsg();
                callBack.Setdata(msg);
            }
        });
    }
    public  interface CallBack{
        void  Setdata(String s);
    }
}
