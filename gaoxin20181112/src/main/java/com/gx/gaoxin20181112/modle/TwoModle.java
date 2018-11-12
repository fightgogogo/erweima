package com.gx.gaoxin20181112.modle;

import android.util.Log;

import com.google.gson.Gson;
import com.gx.gaoxin20181112.bean.Nine;
import com.gx.gaoxin20181112.bean.Two;
import com.gx.gaoxin20181112.utils.UrlConnection;

import java.util.List;

public class TwoModle {
    public boolean flag=true;
    public void getdata(final String u, final CallBack callBack){
        UrlConnection urlConnection=new UrlConnection();
        urlConnection.Get(u, new UrlConnection.GetData() {
            @Override
            public void SetData(String s) {
                if (flag){
                    Gson gson=new Gson();
                    Nine nine = gson.fromJson(s, Nine.class);
                    List<Nine.DataBean> data = nine.getData();
                    callBack.SetData(data);
                    flag=false;
                }
                else{
                    Gson gson1=new Gson();
                    Two two = gson1.fromJson(s,Two.class);
                    List<Two.DataBean> status = two.getData();
                    Log.e("--------0",""+status);
                    callBack.SetTwoData(status);
                }
            }
        });
    }


    public interface CallBack{
        void SetData(List<Nine.DataBean> data);
        void SetTwoData(List<Two.DataBean> data);
    }
}
