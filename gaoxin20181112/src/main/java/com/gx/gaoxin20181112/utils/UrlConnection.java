package com.gx.gaoxin20181112.utils;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.google.common.io.CharStreams;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlConnection {

    @SuppressLint("StaticFieldLeak")
    public void Get(final String ur1l, final GetData getData){
        new AsyncTask<String,Integer,String>(){
            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url=new URL(ur1l);
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.connect();
                    int code = httpURLConnection.getResponseCode();
                    if (code==200){
                        InputStream inputStream = httpURLConnection.getInputStream();
                        String s = CharStreams.toString(new InputStreamReader(inputStream));
                        return s;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                getData.SetData(s);
            }
        }.execute();
    }

    public interface GetData{
        void SetData(String s);
    }
}
