package com.gx.weektwo.url;

import android.os.AsyncTask;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.gx.weektwo.bean.News;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlConnction {

    public void CallBackData(final String url, final CallBack callBack){

        new AsyncTask<String,Void,String>(){
            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url1=new URL(url);
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url1.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.connect();
                    int code = httpURLConnection.getResponseCode();
                    if (code==200){
                        InputStream inputStream = httpURLConnection.getInputStream();
                        String string = CharStreams.toString(new InputStreamReader(inputStream));
                        return string;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(String s) {
                callBack.SetData(s);
            }
        }.execute();

    }

    public interface CallBack{
        void SetData(String s);
    }
}
