package com.gx.weektwo.view;

public interface RegisView {
    //注册成功
    void RegisSuccess(String Successresult);
    //已经有
    void RegisHave(String Haveresult);
    //不能为空
   void RegisNull(String Nullresult);
    //正确手机号
    void RegisTel(String Telresult);
    //密码格式有问题，不能少于6位数
    void RegisPwd(String Pwdresult);
}
