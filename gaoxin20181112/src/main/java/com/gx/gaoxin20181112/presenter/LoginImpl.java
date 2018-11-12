package com.gx.gaoxin20181112.presenter;

import com.gx.gaoxin20181112.view.LoginView;

public class LoginImpl implements LoginPersenter {

    //连接view层和modle层
    LoginView loginView;

    public LoginImpl(LoginView loginView){
        this.loginView=loginView;
    }

    @Override
    public void LoginRemeber(String name,String pwd) {
        //进行逻辑判断
        if (name.equals("Baway")&&pwd.equals("123")){
            loginView.Succss("登录成功");
        }
        else{
            loginView.Failer("用户名或密码错误的提示");
        }
    }
}
