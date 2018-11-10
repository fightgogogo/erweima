package com.gx.weektwo.base;

public interface BasePresenter {
    void login(String url,String tel,String pwd);
    void loginNull();
    void regis(String url,String tel,String pwd);
    void regisNull();
}
