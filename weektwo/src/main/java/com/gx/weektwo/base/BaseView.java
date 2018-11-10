package com.gx.weektwo.base;

//抽取基类
public interface BaseView {
    //输入为空则显示
    void Null(String Nullresult);
    //成功
    void Success(String Successresult);
    //手机号格式
    void TelFailer(String Telresult);
    //密码格式
    void PwdFailer(String Pwdresult);
    //失败
    void Failer(String Failerresult);
}
